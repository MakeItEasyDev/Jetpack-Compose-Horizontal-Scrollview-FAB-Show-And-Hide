package com.jetpack.horizontalscrollview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetpack.horizontalscrollview.ui.theme.HorizontalScrollviewTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HorizontalScrollviewTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = {
                                    Text(
                                        text = "Floating Button Show/Hide",
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Center
                                    )
                                }
                            )
                        }
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            HorizontalScrollView()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HorizontalScrollView() {
    val coroutineScope = rememberCoroutineScope()
    val scrollState: LazyListState = rememberLazyListState()
    val firstItemVisible by remember {
        derivedStateOf { scrollState.firstVisibleItemIndex != 0 }
    }

    Box {
        LazyRow(
            content = {
                items(20) {
                    Card(
                        modifier = Modifier
                            .padding(10.dp),
                        elevation = 10.dp,
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.padding(10.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.cat),
                                contentDescription = "Image",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(65.dp)
                                    .padding(8.dp)
                                    .clip(CircleShape)
                            )

                            Text(
                                text = "Make it Easy",
                                style = MaterialTheme.typography.h6.copy(fontSize = 16.sp),
                                color = MaterialTheme.colors.onSurface,
                                modifier = Modifier.padding(top = 5.dp)
                            )
                        }
                    }
                }
            },
            state = scrollState,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        )

        if (firstItemVisible) {
            FloatingActionButton(
                onClick = {
                    coroutineScope.launch {
                        scrollState.animateScrollToItem(0)
                    }
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 10.dp),
                backgroundColor = Color(0xFFE53935)
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Icons",
                    tint = Color.White
                )
            }
        }
    }
}


















