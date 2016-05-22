package io.github.codeocean_scraper

import org.jsoup.Connection
import org.jsoup.Jsoup
import java.net.URL

private fun login(baseUrl: URL, email: String, password: String): String {
    val loginUrl = URL(baseUrl, "/sign_in")
    val pageResponse = Jsoup.connect(loginUrl.toString())
                            .method(Connection.Method.GET)
                            .execute()
    val doc = pageResponse.parse()
    val token = doc.select("input[name=\"authenticity_token\"]").first().`val`()
    val formData = mapOf(
            "utf-8" to "✓",
            "authenticity_token" to token,
            "email" to email,
            "password" to password,
            "commit" to "Sign+In"
    )
    val loginResponse = Jsoup.connect(URL(baseUrl, "/sessions").toString())
                             .method(Connection.Method.POST)
                             .data(formData)
                             .cookies(pageResponse.cookies())
                             .execute()
    return loginResponse.cookie("_hands-on-programming_session")
}

fun interactiveLogin(baseUrl: URL): String {
    val console = System.console()
    if (console == null) {
        System.err.println("Can't get console object to read email and password. Aborting!")
        System.exit(1)
    }

    val email = console.readLine("Email: ")
    val password = String(console.readPassword("Password: "))
    return login(baseUrl, email, password)
}
