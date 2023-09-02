package com.mmdvs.appfilmescompose.view

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.mmdvs.appfilmescompose.api.HttpHelper
import com.mmdvs.appfilmescompose.apiData.IFilmesApi
import com.mmdvs.appfilmescompose.models.FilmesModels.DetalheFilme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
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


 CoroutineScope(Dispatchers.IO).launch{
   fetchedItems = getDetalheFilmes(movieId,apiService)

   if(fetchedItems != null){
    if(fetchedItems!!.isSuccessful){
      item = fetchedItems!!.body()
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
       modifier = Modifier.padding(end = 5.dp,start = 5.dp,top=40.dp),
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


suspend fun getDetalheFilmes(movieId: Int, apiService:IFilmesApi) :Response<DetalheFilme> {
 var fetchedItems: Response<DetalheFilme>? = null
 try {
  Log.i("info_id", movieId.toString())
   fetchedItems = apiService.getDetalheFilmes(movieId)

 } catch (e: Exception) {
  e.printStackTrace()
  Log.e("Erro", e.message.toString())
 }
 return fetchedItems!!
}



