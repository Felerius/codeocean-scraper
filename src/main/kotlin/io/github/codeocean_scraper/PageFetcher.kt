package io.github.codeocean_scraper

import org.jsoup.Jsoup

class PageFetcher(val sessionKey: String) {
    fun fetch(url: String)
        = Jsoup.connect(url)
               .cookie("_hands-on-programming_session", sessionKey)
               .timeout(0)
               .get()
}
