package com.posthub.di.repositoryes

import com.posthub.supabase.models.Post
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from

interface SupabaseRepository {
    suspend fun getPosts(): List<Post>
}

class SupabaseRepositoryImpl : SupabaseRepository {

    private val supabase: SupabaseClient = createSupabaseClient(
        supabaseUrl = "https://uqgggijqjezqffbgytie.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InVxZ2dnaWpxamV6cWZmYmd5dGllIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MDA0Nzk4MTcsImV4cCI6MjAxNjA1NTgxN30.48KSE1m_uk68kecgbDQM2F9BjJ9FVczSWJYTfPz-OAs"
    ) {
        install(Postgrest)
    }

    private var posts = emptyList<Post>()

    override suspend fun getPosts(): List<Post> {
        posts = supabase.from("posts").select().decodeList<Post>()
        return posts
    }
}