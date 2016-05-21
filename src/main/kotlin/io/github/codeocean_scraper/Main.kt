package io.github.codeocean_scraper

import io.github.codeocean_scraper.analysis.analyseAndSave
import io.github.codeocean_scraper.analysis.findStudentSubmissionPages
import java.net.URL
import java.nio.file.Paths


fun printUsage() {
    println("Usage: codeocean_scraper targetDirectory statisticsUrl")
    System.exit(1)
}

fun main(args: Array<String>) {
    if (args.size != 2) {
        printUsage()
    }

    val cookie = System.getenv("CO_COOKIE")

    if (cookie == null) {
        println("Please set the CO_COOKIE environment variable to your session cookie")
        System.exit(1)
    }

    val (targetDirectory, statsUrlString) = args
    val statsUrl = URL(statsUrlString)
    val baseUrl = "${statsUrl.protocol}://${statsUrl.authority}"

    val fetcher = PageFetcher(cookie)
    println("Fetching statistics page...")
    val statDoc = fetcher.fetch(statsUrlString)
    val studentSubmissionPages = findStudentSubmissionPages(statDoc, baseUrl)
    for ((name, url) in studentSubmissionPages.sortedBy { it.first }) {
        println("Fetching submission(s) for $name...")
        analyseAndSave(url, fetcher, name, Paths.get(targetDirectory))
    }
}

