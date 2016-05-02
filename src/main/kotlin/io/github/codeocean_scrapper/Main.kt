package io.github.codeocean_scrapper

import io.github.codeocean_scrapper.analysis.analyseAndSave
import io.github.codeocean_scrapper.analysis.findStudentSubmissionPages
import java.nio.file.Paths

private val baseUrl = "https://codeocean.openhpi.de"
private val statUrl = "https://codeocean.openhpi.de/exercises/154/statistics"
private val sessionKey = "bmJOT1FPLzcwWG84WDdBcmM1QkNTVDloYnQ4NTJ4bWdqVFZhelhtTjlkSEJ4NEtnMzRsWEVMOERxdVR2OStCcXIxOHVwNW1lY0JiNE1nSWFPdEEzL1ljRnRocFdUS05sbUY4bWhqK2wzanlmRi9pYVNDZmZYVk5vOTZXWkpvNkdWSmRmUWY2V2lwaW5sclhVODRaNDVmekNUTlJabTdhSWQ1T1JNcHIyL0lzPS0tQVAzcFd4eHFIU21MdDNOT2sveCs2Zz09--7b8494ea3cdca414dea39239654a44597ce7a93b"
private val targetDirectory = Paths.get("/Users/david/Desktop/Pt2Submissions")

fun main(args: Array<String>) {
    val fetcher = PageFetcher(sessionKey)
    val statDoc = fetcher.fetch(statUrl)
    val studentSubmissionPages = findStudentSubmissionPages(statDoc, baseUrl)
    for ((name, url) in studentSubmissionPages) {
        analyseAndSave(url, fetcher, name, targetDirectory)
    }
}

