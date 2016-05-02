package io.github.codeocean_scraper

import io.github.codeocean_scraper.analysis.analyseAndSave
import io.github.codeocean_scraper.analysis.findStudentSubmissionPages
import java.net.URL
import java.nio.file.Paths


fun printUsage() {
    println("Usage: codeocean_scraper targetDirectory statisticsUrl sessionKey")
    System.exit(1)
}

fun main(args: Array<String>) {
    if (args.size != 3) {
        printUsage()
    }

    val (targetDirectory, statsUrlString, sessionKey) = args
    val statsUrl = URL(statsUrlString)
    val baseUrl = "${statsUrl.protocol}://${statsUrl.authority}"

    val fetcher = PageFetcher(sessionKey)
    println("Fetching statistics page...")
    val statDoc = fetcher.fetch(statsUrlString)
    val studentSubmissionPages = findStudentSubmissionPages(statDoc, baseUrl)
    for ((name, url) in studentSubmissionPages) {
        println("Fetching submission(s) for $name...")
        analyseAndSave(url, fetcher, name, Paths.get(targetDirectory))
    }
}

