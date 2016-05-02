package io.github.codeocean_scraper.parsing

import com.fasterxml.jackson.annotation.JsonProperty

enum class SubmissionCause {
    @JsonProperty("autosave")
    AUTOSAVE,

    @JsonProperty("run")
    RUN,

    @JsonProperty("test")
    TEST,

    @JsonProperty("download")
    DOWNLOAD,

    @JsonProperty("assess")
    ASSESS,

    @JsonProperty("submit")
    SUBMIT
}
