package com.posthub.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.posthub.di.repositoryes.SupabaseRepository
import com.posthub.supabase.models.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

data class PostsViewModelStates(
    val posts: List<Post> = emptyList()
)

class PostsViewModel() : ViewModel() {
    private val _states = mutableStateOf(PostsViewModelStates())
    val states: State<PostsViewModelStates> = _states

    init {
        getPosts()
    }

    private fun getPosts() {
        viewModelScope.launch(Dispatchers.IO) {
//            _states.value = states.value.copy(posts = repository.getPosts())
        }
    }
}