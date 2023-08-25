package com.mmdvs.appfilmescompose


import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

import com.mmdvs.appfilmescompose.ui.theme.AppFilmesComposeTheme
import com.mmdvs.appfilmescompose.view.DetalhesFilmeView
import com.mmdvs.appfilmescompose.view.ListaCustom


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            AppFilmesComposeTheme {
                val navController = rememberNavController()

                NavHost(navController = navController , startDestination= "listaFilmes" ){
                    composable("listaFilmes") {
                        ListaCustom(navController)
                    }
                    composable(
                        "DetailScreen/{movieId}",
                        arguments = listOf(navArgument("movieId") { type = NavType.IntType })
                    ){item->
                        val movieId = item.arguments?.getInt("movieId")
                        DetalhesFilmeView(navController = navController, movieId = movieId!!)
                    }

                }

            }
        }
    }











}






