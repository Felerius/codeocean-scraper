package io.github.codeocean_scraper

import org.jsoup.Jsoup
import java.net.URL

private val cookieName = "_hands-on-programming_session"

class PageFetcher(val sessionKey: String) {
    fun fetch(url: String)
        = Jsoup.connect(url)
               .cookie(cookieName, sessionKey)
               .maxBodySize(128 * 1024 * 1024)
               .timeout(0)
               .get()
}

fun isValidSessionKey(baseUrl: URL, sessionKey: String): Boolean {
    // Sign in page redirects to homepage if session key is valid
    val signInUrl = URL(baseUrl, "/sign_in")
    val response = Jsoup.connect(signInUrl.toString())
                        .cookie(cookieName, sessionKey)
                        .execute()
    return response.url() != signInUrl
}
