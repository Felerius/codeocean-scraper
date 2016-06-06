package io.github.codeocean_scraper.parsing

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.OffsetDateTime

@JsonIgnoreProperties(ignoreUnknown = true)
data class Submission(
        val cause: SubmissionCause,
        val id: Int,
        val score: Double?,

        @JsonProperty("updated_at")
        val updatedAt: OffsetDateTime
)
