package com.maveric.imdb.di

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.module.AppGlideModule
import com.maveric.imdb.config.CacheConfig

@GlideModule
class GlideModule : AppGlideModule() {
    override fun applyOptions(context: Context, builder: GlideBuilder) {
        val cacheSize = get<CacheConfig>().imageCacheSizeBytes
        val factory = InternalCacheDiskCacheFactory(context, "glide", cacheSize)
        builder.setDiskCache(factory)
    }
}