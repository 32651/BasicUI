package tw.edu.pu.csim.aaaaaaaaa.basicui

import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import tw.edu.pu.csim.aaaaaaaaa.basicui.ui.theme.BasicUITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BasicUITheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Main(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Main(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        LazyColumnExample(modifier)
        ColumnExample(modifier)
        TextFieldExample(modifier)

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 整合影片切換與播放畫面
            VideoSwitcherScreen()

            Spacer(modifier = Modifier.height(10.dp))

            // 橫向動物捲動元件
            LazyRowExample()
        }
    }
}

@Composable
fun LazyColumnExample(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        item {
            Text(text = "標題列")
        }

        items(50) { index ->
            Text(text = "$index")
        }

        item {
            Text(text = "結尾列")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BasicUITheme {
        // 如果 Greeting 尚未實作可先用其他預覽代替
    }
}

@Composable
fun ColumnExample(modifier: Modifier = Modifier) {
    var message by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xffE0BBE4))
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { message = "短按" },
                    onDoubleTap = { message = "連按兩下" },
                    onLongPress = { message = "長按" }
                )
            },
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "行動應用軟體開發：基本元件")
        Text(text = "作者：王景陽")
        Text(text = message)

        Spacer(modifier = Modifier.height(8.dp))
        RowExample()
    }
}

@Composable
fun RowExample(modifier: Modifier = Modifier) {
    Row {
        Image(
            painter = painterResource(id = R.drawable.android),
            contentDescription = "Android icon",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(Color.Yellow),
            alpha = 0.6f,
        )

        Spacer(modifier = Modifier.width(20.dp))

        Image(
            painter = painterResource(id = R.drawable.compose),
            contentDescription = "Compose icon",
            modifier = Modifier.size(100.dp)
        )
    }
}

@Composable
fun TextFieldExample(modifier: Modifier = Modifier) {
    var inputText by remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        OutlinedTextField(
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text("請輸入文字") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(0.8f)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = if (inputText.isBlank()) "尚未輸入內容" else "您輸入的是：$inputText"
        )
    }
}

data class Animal(
    val name: String,
    @DrawableRes val imageRes: Int
)

@Composable
fun LazyRowExample(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    val animals = listOf(
        Animal("鴨子", R.drawable.animal0),
        Animal("企鵝", R.drawable.animal1),
        Animal("青蛙", R.drawable.animal2),
        Animal("貓頭鷹", R.drawable.animal3),
        Animal("海豚", R.drawable.animal4),
        Animal("牛", R.drawable.animal5),
        Animal("無尾熊", R.drawable.animal6),
        Animal("獅子", R.drawable.animal7),
        Animal("狐狸", R.drawable.animal8),
        Animal("小雞", R.drawable.animal9)
    )

    LazyRow(
        modifier = Modifier.height(260.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        itemsIndexed(animals) { index, animal ->
            Card(
                modifier = Modifier
                    .width(200.dp)
                    .height(240.dp)
                    .padding(horizontal = 4.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFD0BCFF)
                )
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Image(
                        painter = painterResource(id = animal.imageRes),
                        contentDescription = animal.name,
                        modifier = Modifier
                            .size(130.dp)
                            .clickable {
                                Toast.makeText(
                                    context,
                                    "您點選的是 ${animal.name}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "$index ${animal.name}"
                    )
                }
            }
        }
    }
}

@Composable
fun VideoSwitcherScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    // 使用 remember 記錄目前是否為 love 影片
    var isLoveVideo by remember { mutableStateOf(true) }

    // 根據狀態選擇對應的 raw 資源 ID（請確保 res/raw 底下有 love.mp4 與 handpan.mp4）
    val currentVideoRes = if (isLoveVideo) {
        R.raw.love
    } else {
        R.raw.handpan
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // 使用 AndroidView 嵌入 VideoView 播放影片
        AndroidView(
            factory = { ctx ->
                VideoView(ctx).apply {
                    setMediaController(MediaController(ctx))
                }
            },
            update = { videoView ->
                val uri = Uri.parse("android.resource://${context.packageName}/$currentVideoRes")
                videoView.setVideoURI(uri)
                videoView.start()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // 切換按鈕
        Button(onClick = {
            isLoveVideo = !isLoveVideo
        }) {
            Text(text = if (isLoveVideo) "切換至手碟影片" else "切換至Love影片")
        }
    }
}