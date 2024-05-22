package com.posthub.di.repositoryes

import com.posthub.supabase.models.Post

interface SupabaseRepository {
    suspend fun getPosts(): List<Post>
}