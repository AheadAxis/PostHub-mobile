package com.posthub.supabase.models

import kotlinx.serialization.Serializable

@Serializable
data class Post (
    val id: String?,
    val created_at: String?,
    val updated_at: String?,
    val author_id: String?,
    val title: String?,
    val content: String?,
    val count_view: Int?,
    val image_path: String?,
    val age_rating_id: Int?,
)