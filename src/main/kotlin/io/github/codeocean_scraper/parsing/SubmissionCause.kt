package io.github.codeocean_scraper.parsing

import com.fasterxml.jackson.databind.annotation.JsonDeserialize

@JsonDeserialize(using = SubmissionCauseDeserializer::class)
enum class SubmissionCause {
    ASSESS,
    SUBMIT,
    OTHER
}
