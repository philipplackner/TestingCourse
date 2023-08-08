package com.plcoding.testingcourse.part11.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plcoding.testingcourse.core.domain.Post
import com.plcoding.testingcourse.core.domain.Profile
import com.plcoding.testingcourse.core.domain.ProfileState
import com.plcoding.testingcourse.core.domain.User
import com.plcoding.testingcourse.ui.theme.TestingCourseTheme

@Composable
fun ProfileScreen(
    state: ProfileState
) {
    if(state.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            state.profile?.user?.let { user ->
                Text(
                    text = user.username,
                    fontSize = 24.sp
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = "Posts:")
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(state.profile?.posts ?: emptyList()) { post ->
                    PostUi(
                        post = post,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
private fun PostUi(
    post: Post,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .shadow(4.dp)
            .padding(16.dp)
    ) {
        Text(
            text = post.title,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = post.body)
    }
}

fun previewProfileState() = ProfileState(
    profile = Profile(
        user = User(
            id = "test-user",
            username = "Test username"
        ),
        posts = (1..10).map {
            Post(
                id = it.toString(),
                userId = "test-user",
                title = "Title$it",
                body = "Body$it"
            )
        }
    ),
    isLoading = false
)

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    TestingCourseTheme {
        ProfileScreen(
            state = previewProfileState()
        )
    }
}