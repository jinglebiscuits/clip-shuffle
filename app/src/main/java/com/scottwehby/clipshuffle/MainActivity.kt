package com.scottwehby.clipshuffle

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.scottwehby.clipshuffle.ui.theme.ClipShuffleTheme

class MainActivity : ComponentActivity() {

    private val clips = listOf(
        R.raw.he_aint_heavy_chorus_1,
        R.raw.he_aint_heavy_chorus_2,
        R.raw.he_aint_heavy_chorus_3
    )

    private lateinit var mediaPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mediaPlayer = MediaPlayer.create(this, clips.random())
        setContent {
            ClipShuffleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Play clip") {
                        mediaPlayer.reset()
                        mediaPlayer.setDataSource(
                            this,
                            Uri.parse("android.resource://$packageName/${clips.random()}")
                        )
                        mediaPlayer.prepare()
                        mediaPlayer.start()
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(description: String, function: () -> Unit) {
    Button(onClick = function) {
        Text(text = description, color = Color.White)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ClipShuffleTheme {
        Greeting("Android") {}
    }
}