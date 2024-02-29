package com.posthub.actyvitys

import android.os.Bundle
import android.text.Html
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.posthub.di.repositoryes.SupabaseRepository
import com.posthub.supabase.models.Post
import com.posthub.ui.theme.PostHubTheme
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val repository: SupabaseRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PostHubTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    PostList(repository)
                }
            }
        }
    }
}

@Composable
fun PostList(repository:SupabaseRepository) {
    val posts = remember {
        mutableStateOf(emptyList<Post>())
    }

    LaunchedEffect(Unit){
        posts.value = repository.getPosts()
    }

    LazyColumn(contentPadding = PaddingValues(20.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        items(posts.value) { post ->
            Card() {
                Column(Modifier.padding(20.dp)) {
                    post.title?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.headlineSmall,
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    post.content?.let { text ->
                        AndroidView(
                            factory = {
                                TextView(it)
                            },
                            update = {
                                it.text = Html.fromHtml(text)
                            }
                        )
                    }
                }
            }
        }
    }
}