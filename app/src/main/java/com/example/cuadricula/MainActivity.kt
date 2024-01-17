package com.example.cuadricula

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cuadricula.data.DataSource
import com.example.cuadricula.model.Topic
import com.example.cuadricula.ui.theme.CuadriculaTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CuadriculaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CuadriculaApp("Android")
                }
            }
        }
    }
}

@Composable
fun CuadriculaApp(name: String, modifier: Modifier = Modifier) {
    CuadriculaList(cuadriculaList = DataSource.topics)
}

@Composable
fun CuadriculaCard(topic: Topic, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier) {
        Row (
            modifier = Modifier
        ){
            Image(
                painter = painterResource(topic.imageResourceId),
                contentDescription = stringResource(topic.stringResourceId),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .size(height = 68.dp, width = 68.dp),
                contentScale = ContentScale.Crop,
            )
            Column(
                modifier = Modifier
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                    .align(Alignment.CenterVertically),
            ) {
                Text(
                    text = LocalContext.current.getString(topic.stringResourceId),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.bodyMedium
                )
                Row(
                    modifier = Modifier
                        .align(Alignment.Start),
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_grain),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.CenterVertically),
                    )
                    Text(
                        text = topic.numeroTemas.toString(),
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .align(Alignment.CenterVertically),
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CuadroCuadriculaPreview() {
    //CuadriculaCard(Topic(R.string.photography, 321, R.drawable.photography),)
    CuadriculaApp("Android")
}

@Composable
fun CuadriculaList(cuadriculaList: List<Topic>, modifier: Modifier = Modifier){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier,
        content = {
            items(cuadriculaList) { topic ->
                CuadriculaCard(
                    topic = topic,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    )
}
