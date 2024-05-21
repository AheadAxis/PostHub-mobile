package com.posthub

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.posthub.modules.appModule
import com.posthub.ui.theme.PostHubTheme
import com.posthub.viewModels.PostsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PostHubTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    PostList()
                }
            }
        }
        // Start Koin
        startKoin {
            androidContext(this@MainActivity)
            modules(appModule)
        }
    }
}

@Composable
fun PostList(viewModel: PostsViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    LazyColumn(contentPadding = PaddingValues(20.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        items(viewModel.states.value.posts) { post ->
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
//                        Text(
//                            text = Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY),
//                            maxLines = 5,
//                            overflow = TextOverflow.Ellipsis
//                        )
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