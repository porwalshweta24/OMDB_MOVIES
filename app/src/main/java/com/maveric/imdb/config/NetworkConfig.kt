package com.maveric.imdb.config

class NetworkConfig {
    val maxRequests = 72
    val maxRequestPerHost = 8
    val connectTimeoutSeconds = 20L
    val readTimeoutSeconds = 20L
    val writeTimeoutSeconds = 20L

    val omdbKeys =  arrayOf("b9bd48a6")
    val newsKeys = arrayOf("adf66c7597d64873a6b730861708f45f", "c9f0afe90887488595f227ffa2279c4c")
}