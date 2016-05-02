package io.github.codeocean_scraper.parsing

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Submission(
        val cause: SubmissionCause,
        val id: Int,
        val score: Double?
)
