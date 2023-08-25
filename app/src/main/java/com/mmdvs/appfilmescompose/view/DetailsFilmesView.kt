package com.mmdvs.appfilmescompose.view

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.mmdvs.appfilmescompose.R
import com.mmdvs.appfilmescompose.api.HttpHelper
import com.mmdvs.appfilmescompose.models.DetalheFilme
import com.mmdvs.appfilmescompose.models.FilmesModel
import kotlinx.coroutines.launch
import retrofit2.Response

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalhesFilmeView(
navController: NavController,
 movieId: Int
){
 var item by remember { mutableStateOf<DetalheFilme?>(null) }
 var isLoading by remember { mutableStateOf(true) }
 val coroutineScope = rememberCoroutineScope()

 var fetchedItems: Response<DetalheFilme>? = null


 val context = LocalContext.current;

 val apiService by lazy { HttpHelper.apiFilmes}

 LaunchedEffect(Unit) {
  coroutineScope.launch {
   try {
    Log.i("info_id",movieId.toString())
     fetchedItems = apiService.getDetalheFilmes(movieId)
    item = fetchedItems?.body()!!


   } catch (e: Exception) {
    e.printStackTrace()
    Log.e("Erro", e.message.toString())
   } finally {
    isLoading = false
   }
  }
 }



 if(isLoading){
  return Column(
   modifier = Modifier.fillMaxSize(),
   verticalArrangement = Arrangement.Center,
   horizontalAlignment = Alignment.CenterHorizontally

   ) {
   CircularProgressIndicator()
  }
 }

 if(item != null){
  val image = HttpHelper.BASE_ULR_IMAGE + "w1280" +item?.backdrop_path!!
  var model by remember { mutableStateOf(image) }
   Scaffold(



    topBar = {

     Surface(shadowElevation = 3.dp) {
      TopAppBar(
       title = {
        Text(text = item?.title!!)
       },


       navigationIcon = {
                        IconButton(onClick = {
                         navController.navigateUp()
                        }) {
                         Icon(Icons.Outlined.ArrowBack, contentDescription = "Voltar")
                        }
       },
      )
     }
    },
   ) {
     Column {
      Text(
       text = item?.title!!,
       modifier = Modifier.padding(start = 5.dp)
      )
      AsyncImage(
       model = model,
       contentDescription = "",
       modifier = Modifier.padding(end = 5.dp,start = 5.dp,top=10.dp),
       filterQuality = FilterQuality.High,

      )
      Text(
       text = if(item?.overview.isNullOrEmpty()){ "Sinopse Nao Divulgada" } else {"Sinopse: ${item?.overview!!}" },
       maxLines = 3,

       modifier = Modifier.padding(20.dp),

      )

      Row(
       modifier = Modifier.padding(start = 5.dp),
       verticalAlignment = Alignment.CenterVertically
      ) {
       Text(
        text = "Genero:",
        modifier = Modifier.padding(5.dp)
       )
       LazyRow(
        modifier = Modifier.padding(20.dp)
       ) {
        items(item?.genres!!) { item ->
         Text(
          text = item?.name!!,
          modifier = Modifier.padding(5.dp)
         )

        }
       }
      }



     }
   }
 }else{
   Toast.makeText(context,"Erro ao trazer o Detalhe do Filme",Toast.LENGTH_SHORT).show()
 }


}