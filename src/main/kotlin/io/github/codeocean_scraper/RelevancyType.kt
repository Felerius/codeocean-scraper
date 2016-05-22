package io.github.codeocean_scraper

enum class RelevancyType {
    SUBMITTED,
    ASSESSED_AFTER_SUBMIT,
    ASSESSED_ONLY,
    NO_ASSESS_OR_SUBMIT,
    TOP_SCORE;

    fun toFileString() = when(this) {
        RelevancyType.SUBMITTED -> "SUBMIT"
        RelevancyType.ASSESSED_AFTER_SUBMIT -> "ASSESSED AFTER SUBMIT"
        RelevancyType.ASSESSED_ONLY -> "ASSESSED ONLY"
        RelevancyType.NO_ASSESS_OR_SUBMIT -> "NO ASSESS OR SUBMIT"
        RelevancyType.TOP_SCORE -> "TOP SCORE"
    }

    fun toFilePostfix() = when(this) {
        RelevancyType.SUBMITTED -> ""
        else -> " - ${this.toFileString()}"
    }
}
