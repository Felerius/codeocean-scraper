package io.github.codeocean_scraper

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.github.codeocean_scraper.analysis.analyseAndSave
import io.github.codeocean_scraper.analysis.findStudentSubmissionPages
import java.net.URL
import java.nio.file.Paths
import java.time.OffsetDateTime


private fun printUsage() {
    println("Usage: codeocean_scraper exerciseUrl targetDirectory deadline")
    System.exit(1)
}

private fun URL.ensureHttps() = when (protocol) {
    "https" -> this
    "http" -> URL(toString().replaceFirst("http", "https"))
    else -> throw IllegalArgumentException("Cannot convert protocol $protocol to https")
}

fun main(args: Array<String>) {
    if (args.size != 3) {
        printUsage()
    }

    val (exerciseUrlStr, targetDirectory, rawDeadline) = args
    val exerciseUrl = URL(exerciseUrlStr).ensureHttps()
    val baseUrl = URL(exerciseUrl.protocol, exerciseUrl.host, exerciseUrl.port, "")
    val baseUrlStr = baseUrl.toString()
    val mapper = jacksonObjectMapper()
    mapper.registerModule(JavaTimeModule());

    val deadline = OffsetDateTime.parse(rawDeadline)

    val cache = loadCache(mapper)
    if (baseUrlStr !in cache.sessionKeys) {
        println("No saved session key found, signing in")
        cache.sessionKeys[baseUrlStr] = interactiveLogin(baseUrl)
        cache.save(mapper)
    } else if (!isValidSessionKey(baseUrl, cache.sessionKeys[baseUrlStr]!!)) {
        println("Saved session key out of date, signing in")
        cache.sessionKeys[baseUrlStr] = interactiveLogin(baseUrl)
        cache.save(mapper)
    }

    val fetcher = PageFetcher(cache.sessionKeys[baseUrlStr]!!)
    println("Fetching statistics page")
    val statDoc = fetcher.fetch(exerciseUrlStr)
    val studentSubmissionPages = findStudentSubmissionPages(statDoc, baseUrlStr)

    for ((name, url) in studentSubmissionPages.sortedBy { it.first }) {
        println("Fetching submission(s) for $name")
        analyseAndSave(url, fetcher, name, deadline, Paths.get(targetDirectory), mapper)
    }
}

