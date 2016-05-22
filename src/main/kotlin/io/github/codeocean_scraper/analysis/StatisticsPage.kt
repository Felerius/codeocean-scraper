package io.github.codeocean_scraper.analysis

import org.jsoup.nodes.Document

fun findStudentSubmissionPages(
        doc: Document,
        baseUrl: String
): List<Pair<String, String>> {
    val table = doc.select("strong:contains(External Users) ~ div > table").first()
    val links = table.select("tbody > tr > td > a")
    return links.map { it.text() to (baseUrl + it.attr("href")) }
}
