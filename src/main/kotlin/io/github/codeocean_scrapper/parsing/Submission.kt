package io.github.codeocean_scrapper.parsing

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Submission(
        val cause: SubmissionCause,
        val id: Int,
        val score: Double?
)
