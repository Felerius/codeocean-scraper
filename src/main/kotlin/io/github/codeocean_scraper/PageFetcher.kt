package io.github.codeocean_scraper

import org.jsoup.Jsoup

class PageFetcher(val sessionKey: String) {
    fun fetch(url: String)
        = Jsoup.connect(url)
               .cookie("_hands-on-programming_session", sessionKey)
               .maxBodySize(128 * 1024 * 1024)
               .timeout(0)
               .get()
}
