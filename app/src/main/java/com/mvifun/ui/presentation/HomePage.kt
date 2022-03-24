package com.mvifun.ui.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mvifun.model.Post
import com.mvifun.ui.HomeViewModel

@Composable
fun HomePage() {
    var homeViewModel: HomeViewModel = viewModel()
    val homeState = homeViewModel.homeState

    Column()
    {
        Text(homeState.status)
        Box(modifier = Modifier.fillMaxSize())
        {
            LazyColumn()
            {
                itemsIndexed(homeState.posts)
                { index, post ->
                    PostItem(
                        index = index,
                        post = post,
                        onPress =  { homeViewModel.handleEvent(HomeEvent.OnPress(post.id)) },
                        onLongPress =  { homeViewModel.handleEvent(HomeEvent.OnLongPress(post.id)) },
                        onDeleteClick =  { homeViewModel.handleEvent(HomeEvent.OnDeleteClick(post.id))}
                    )
                }
            }
            if (homeState.error.isNotEmpty() && homeState.posts.size == 0) {
                Text(homeState.error, modifier = Modifier.align(Alignment.Center))
            }
            if (homeState.loading == true) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}

@Composable
fun PostItem(index: Int, post: Post, onPress: () -> Unit, onLongPress: () -> Unit, onDeleteClick:() -> Unit) {
    Card(
        elevation = 5.dp,
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = { onPress() },
                    onLongPress = { onLongPress() }
                )
            }) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Row(modifier = Modifier.fillMaxWidth(.8f), verticalAlignment = Alignment.CenterVertically)
            {
                Column(modifier = Modifier.padding(7.dp))
                {
                    Text(post.id.toString(), style = MaterialTheme.typography.h2)
                }
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(post.title, style = MaterialTheme.typography.body1)
                    Text(post.body, style = MaterialTheme.typography.body2)
                }
            }
            Row(modifier = Modifier.padding(7.dp))
            {
                Column()
                {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        modifier = Modifier.clickable {
                            onDeleteClick()
                        }
                    )
                }
            }
        }
    }
}

