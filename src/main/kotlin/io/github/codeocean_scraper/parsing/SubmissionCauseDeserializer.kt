package io.github.codeocean_scraper.parsing

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer

class SubmissionCauseDeserializer : JsonDeserializer<SubmissionCause>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext)
        = when(p.valueAsString) {
            "submit" -> SubmissionCause.SUBMIT
            "assess" -> SubmissionCause.ASSESS
            else -> SubmissionCause.OTHER
        }
}
