package com.posthub

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.posthub.ui.theme.PostHubTheme
import com.posthub.viewModels.MainViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import java.sql.Timestamp

var supabase: SupabaseClient = createSupabaseClient(
    supabaseUrl = "https://uqgggijqjezqffbgytie.supabase.co",
    supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InVxZ2dnaWpxamV6cWZmYmd5dGllIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MDA0Nzk4MTcsImV4cCI6MjAxNjA1NTgxN30.48KSE1m_uk68kecgbDQM2F9BjJ9FVczSWJYTfPz-OAs"
) {
    install(Postgrest)
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PostHubTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    PostList()
                }
            }
        }
    }
}

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

@Composable
fun PostList() {
    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            val result = supabase.from("posts").select().decodeList<Post>()
            for (post in result) {
                Log.d("supabase", post.title.toString())
            }
        }

    }
}