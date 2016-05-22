package io.github.codeocean_scraper

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.io.File

private val cacheFile = File(System.getProperty("user.home"), ".codeocean-scraper.json")

class Cache(val sessionKeys: MutableMap<String, String>) {
    fun save(mapper: ObjectMapper) {
        mapper.writeValue(cacheFile, this)
    }
}

fun loadCache(mapper: ObjectMapper): Cache {
    return when {
        cacheFile.exists() -> mapper.readValue<Cache>(cacheFile)
        else -> Cache(mutableMapOf())
    }
}
