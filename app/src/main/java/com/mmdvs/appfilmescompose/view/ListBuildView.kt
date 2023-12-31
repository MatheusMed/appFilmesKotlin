package com.mmdvs.appfilmescompose.view

import CustomToast
import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.Navigator
import androidx.room.Room
import coil.compose.SubcomposeAsyncImage
import com.mmdvs.appfilmescompose.R
import com.mmdvs.appfilmescompose.RouteName
import com.mmdvs.appfilmescompose.api.HttpHelper
import com.mmdvs.appfilmescompose.apiData.database.AppDatabase
import com.mmdvs.appfilmescompose.apiData.database.FilmesDBDao
import com.mmdvs.appfilmescompose.apiData.database.datasource.FilmeDatasource
import com.mmdvs.appfilmescompose.apiData.database.repository.FilmeRepository
import com.mmdvs.appfilmescompose.models.FilmesModels.FilmesModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaCustom(
  navController: NavController
) {
  var items by remember { mutableStateOf(emptyList<FilmesModel>()) }
  var isLoading by remember { mutableStateOf(true) }
  val coroutineScope = rememberCoroutineScope()

  val context = LocalContext.current



  val apiService by lazy { HttpHelper.apiFilmes}



  LaunchedEffect(Unit) {
    coroutineScope.launch {
      try {
        val fetchedItems = apiService.getFilmesPopulares()
        items = fetchedItems.body()?.results!!

      } catch (e: Exception) {
        e.printStackTrace()

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
    floatingActionButton = {
      FloatingActionButton(
        onClick = {
          navController.navigate(RouteName.SERIES.name)
        }) {
        Icon(
          painter = painterResource(id = R.drawable.icon_tv_series),
          contentDescription = "",
          Modifier.size(30.dp)
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
          modifier = Modifier.fillMaxSize(),
          contentPadding = PaddingValues(vertical = 60.dp)
        ) {
          items(items) { item ->
            ListItemFilmes(item = item, navController = navController,)
          }
        }
      }
    }
  )
}


@SuppressLint("RememberReturnType")
@Composable
fun ListItemFilmes(
  item: FilmesModel,
  navController: NavController,

) {


  val image = HttpHelper.BASE_ULR_IMAGE + "w1280" +item.backdrop_path

  val coroutineScope = rememberCoroutineScope()
  val context = LocalContext.current


  val db = AppDatabase.getInstance(context = context);




  Card(
    modifier = Modifier
      .fillMaxWidth()
      .wrapContentSize(Alignment.Center)
      .padding(10.dp),
    shape = MaterialTheme.shapes.small,

    ) {

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
        .padding(10.dp)
        .clickable {
          navController.navigate("DetailScreen/${item.id}")
        },
      filterQuality = FilterQuality.High,
      contentDescription = stringResource(R.string.description)
    )
    Text(
      text = item?.title!!,
      modifier = Modifier.padding(10.dp),
    )

  }

}
