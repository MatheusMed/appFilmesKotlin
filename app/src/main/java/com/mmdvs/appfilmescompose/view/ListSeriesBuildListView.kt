package com.mmdvs.appfilmescompose.view

import android.annotation.SuppressLint
import android.util.Log

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.mmdvs.appfilmescompose.R
import com.mmdvs.appfilmescompose.api.HttpHelper
import com.mmdvs.appfilmescompose.apiData.ISeriesApi
import com.mmdvs.appfilmescompose.models.FilmesModels.DetalheFilme
import com.mmdvs.appfilmescompose.models.FilmesModels.FilmesModel
import com.mmdvs.appfilmescompose.models.SeriesModels.SerieResultModel
import com.mmdvs.appfilmescompose.models.SeriesModels.SerieResultTvModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Response


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun ListSeriesBuildListView(
  navController: NavController
) {
  var items by remember { mutableStateOf(emptyList<SerieResultModel>()) }
  var isLoading by remember { mutableStateOf(true) }
  val coroutineScope = rememberCoroutineScope()

  val context = LocalContext.current


  val apiService by lazy { HttpHelper.apiSeries}


  LaunchedEffect(Unit){
    coroutineScope.launch {
      try {
        val fetchedItems = apiService.getSeries()
        items = fetchedItems.body()?.results!!

        Log.i("info_series", items.toString())
      } catch (e: Exception) {
        e.printStackTrace()
      } finally {
        isLoading = false
      }
    }
  }


  Scaffold(
    topBar = {
        TopAppBar(

          title = {
            Text(text = stringResource(id = R.string.tituloAppSeries))
          },
          navigationIcon = {
            IconButton(onClick = {
              navController.navigateUp()
            }) {
              Icon(Icons.Outlined.ArrowBack, contentDescription = "Voltar")
            }
          },
        )
    },
    content = {
      if (isLoading) {
        CircularProgressIndicator(
          modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
        )
      } else {
        LazyColumn(
          modifier = Modifier.fillMaxSize(),
          contentPadding = PaddingValues(vertical = 60.dp)
        ) {
          items(items) { item ->

            ListItemSeries(

              item = item, navController = navController,
              )
          }
        }

      }
    }
  )
}


@SuppressLint("SuspiciousIndentation")
@Composable
fun ListItemSeries(item: SerieResultModel, navController: NavController) {
  val image = HttpHelper.BASE_ULR_IMAGE + "w1280" +item.backdrop_path

  val coroutineScope = rememberCoroutineScope()
  val context = LocalContext.current

    Card(
      modifier = Modifier
      .fillMaxWidth()
      .wrapContentSize(Alignment.Center)
      .padding(10.dp),
      shape = MaterialTheme.shapes.small,

    ) {
      Text(
        text = item?.name!!.toString(),
        modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 10.dp),
      )
      SubcomposeAsyncImage(
        model = image,
        loading = {
          CircularProgressIndicator(
            modifier = Modifier
              .padding(start = 6.dp, end = 6.dp)
              .wrapContentSize(Alignment.Center)
          )
        },

        modifier = Modifier
          .fillMaxSize()
          .padding(start = 10.dp, end = 10.dp, bottom = 10.dp, top = 2.dp),
        filterQuality = FilterQuality.High,
        contentDescription = stringResource(R.string.SeriesImage)
      )

    }




}

