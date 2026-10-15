package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.example.myapplication.InfoItem
import androidx.compose.foundation.layout.asPaddingValues

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                ProfileScreen()
            }
        }
    }
}

@Composable
fun InfoItem(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.fillMaxWidth()) {
        Column(
            Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(label, style = MaterialTheme.typography.headlineMedium)
            Text(value, style = MaterialTheme.typography.bodyLarge)
        }
    }
}
@Preview(showBackground = true)
@Composable
fun InfoItemPreview() {
    MyApplicationTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 48.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            InfoItem(
                "About",
                "Komunitas Gaming IF UAI merupakan komunitas untuk mahasiswa IF UAI yang hobi dengan main game. Di komunitas ini, kami mempunyai aktivitas main bareng, serta juga sharing session di mana kami sharing sudah main apa saja. Komunitas ini open untuk pemain genre apa saja, mulai dari action, RPG, fighting game, sports, dan seterusnya."
            )
            InfoItem(
                "Aktivitas",
                """
                    Aktivitas yang kami lakukan adalah:
                    - Main co-op bareng
                    - 1v1 di fighting game
                    - Sharing session
                    - Kumpul bareng di event game/serupanya
                    - dan seterusnya!
                    """.trimIndent()
            )
        }
    }
}

@Composable
fun PageHeader() {
    Box(modifier = Modifier
        .fillMaxWidth()
        ) {
        Image(
            painter = painterResource(R.drawable.header_background),
            contentDescription = "Image of The World Ends With You",
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
                .blur(radius = 8.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 48.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                "Komunitas Gaming IF UAI",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onPrimary,
                textAlign = TextAlign.Center
            )
            Text(
                "Selamat datang! Di sini anda bisa menemukan berbagai informasi tentang komunitas kami, serta juga bisa mendaftarkan diri di sini!",
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSecondary
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PageHeaderPreview() {
    MyApplicationTheme {
        PageHeader()
    }
}

@Composable
fun ActionCard () {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Tunggu apa lagi? Daftar sekarang di sini!", style = MaterialTheme.typography.headlineMedium, textAlign = TextAlign.Center)
            Button(onClick = { /* */ }, content = {Text("Daftar Sekarang!")})
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ActionCardPreview() {
    MyApplicationTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 48.dp),
        ) {
            ActionCard()
        }
    }
}

@Composable
fun ProfileScreen() {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .verticalScroll(scrollState)
    ) {
        PageHeader()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            InfoItem(
                "About",
                "Komunitas Gaming IF UAI merupakan komunitas untuk mahasiswa IF UAI yang hobi dengan main game. Di komunitas ini, kami mempunyai aktivitas main bareng, serta juga sharing session di mana kami sharing sudah main apa saja. Komunitas ini open untuk pemain genre apa saja, mulai dari action, RPG, fighting game, sports, dan seterusnya."
            )
            InfoItem(
                "Aktivitas",
                """
                Aktivitas yang kami lakukan adalah:
                - Main co-op bareng
                - 1v1 di fighting game
                - Sharing session
                - Kumpul bareng di event game/serupanya
                - dan seterusnya!
            """.trimIndent()
            )
            ActionCard()
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    MyApplicationTheme {
        ProfileScreen()
    }
}