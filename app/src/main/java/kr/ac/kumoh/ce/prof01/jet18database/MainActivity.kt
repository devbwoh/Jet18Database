package kr.ac.kumoh.ce.prof01.jet18database

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kr.ac.kumoh.ce.prof01.jet18database.ui.theme.Jet18DatabaseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Jet18DatabaseTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        SongList()
    }
}

@Composable
fun SongList() {
    val viewModel: SongViewModel = viewModel()

    LazyColumn {
        items(
            items = viewModel.songs,
            key = { song -> song.id }
        ) {
            Text(it.toString(), fontSize = 20.sp)
        }
    }
}
