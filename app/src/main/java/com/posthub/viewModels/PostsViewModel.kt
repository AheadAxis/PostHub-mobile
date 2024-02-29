package com.posthub.viewModels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.posthub.supabase.models.Post
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

data class PostsViewModelStates(
    val posts: List<Post> = emptyList()
)

class PostsViewModel : ViewModel() {
    private val _states = mutableStateOf(PostsViewModelStates())
    val states: State<PostsViewModelStates> = _states

    private val supabase: SupabaseClient = createSupabaseClient(
        supabaseUrl = "https://uqgggijqjezqffbgytie.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InVxZ2dnaWpxamV6cWZmYmd5dGllIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MDA0Nzk4MTcsImV4cCI6MjAxNjA1NTgxN30.48KSE1m_uk68kecgbDQM2F9BjJ9FVczSWJYTfPz-OAs"
    ) {
        install(Postgrest)
    }

    init {
        getPosts()
    }

    private fun getPosts(){
        viewModelScope.launch(Dispatchers.IO) {
            val result = supabase.from("posts").select().decodeList<Post>()
            _states.value = states.value.copy(posts = result)
        }
    }
}