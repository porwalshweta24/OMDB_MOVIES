package com.maveric.imdb.utils.diff

interface DiffItem {
    fun id(): Any? = hashCode()
}