package io.github.codeocean_scraper.parsing

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class SubmissionFile(
        val content: String,
        val name: String,

        @JsonProperty("context_id")
        val contextId: Int
)
