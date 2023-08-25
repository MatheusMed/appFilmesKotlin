package com.mmdvs.appfilmescompose.view

import android.annotation.SuppressLint
import android.util.Log
import android.widget.AdapterView.OnItemClickListener
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.mmdvs.appfilmescompose.R
import com.mmdvs.appfilmescompose.api.HttpHelper
import com.mmdvs.appfilmescompose.models.FilmesModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaCustom() {
  var items by remember { mutableStateOf(emptyList<FilmesModel>()) }
  var isLoading by remember { mutableStateOf(true) }
  val coroutineScope = rememberCoroutineScope()

  val apiService by lazy { HttpHelper.apiFilmes}

  LaunchedEffect(Unit) {
    coroutineScope.launch {
      try {
        val fetchedItems = apiService.getFilmesPopulares()
        items = fetchedItems.body()?.results!!
      } catch (e: Exception) {
        e.printStackTrace()
        Log.e("Erro", e.message.toString())
      } finally {
        isLoading = false
      }
    }
  }



  Scaffold(
    topBar = {

      Surface(shadowElevation = 3.dp) {
        TopAppBar(
          title = {
            Text(text = stringResource(id = R.string.tituloAppBar))
          },

          actions = {
            IconButton(onClick = {}) {
              Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = null
              )
            }
          }

        )
      }
    },
    content = {
      if (isLoading) {
        CircularProgressIndicator(
          modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .wrapContentSize(Alignment.Center)
        )
      } else {
        LazyColumn(
          modifier = Modifier.fillMaxSize()
        ) {
          items(items) { item ->
            ListItem(item = item)
          }
        }
      }
    }
  )
}




@Composable
fun ListItem(item: FilmesModel) {
  val image = HttpHelper.BASE_ULR_IMAGE + "w1280" +item.backdrop_path

  val coroutineScope = rememberCoroutineScope()
  val context = LocalContext.current


  Column(
    Modifier.clickable {

    }
  ) {

    Text(
      text = item.title,
      modifier = Modifier.padding(16.dp)
    )
    SubcomposeAsyncImage(
      model = image,
      loading = {
        CircularProgressIndicator(
          modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .wrapContentSize(Alignment.Center)
        )
      },
      modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
      filterQuality = FilterQuality.High,
      contentDescription = stringResource(R.string.description)
    )



  }
}
