package com.posthub.supabase.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val uid: String?,
    val created_at: String?,
    val email: String?,
    val name: String?,
    val avatar_url: String?,
    val gender_id: Int,
)