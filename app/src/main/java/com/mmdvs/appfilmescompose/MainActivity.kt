package com.mmdvs.appfilmescompose


import android.annotation.SuppressLint
import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

import com.mmdvs.appfilmescompose.ui.theme.AppFilmesComposeTheme
import com.mmdvs.appfilmescompose.view.DetalhesFilmeView
import com.mmdvs.appfilmescompose.view.ListSeriesBuildListView
import com.mmdvs.appfilmescompose.view.ListaCustom


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            AppFilmesComposeTheme {
                val navController = rememberNavController()

                NavHost(navController = navController , startDestination= RouteName.FILMES.name, builder = {
                    composable(RouteName.FILMES.name) {
                        ListaCustom(navController)
                    }

                    composable(RouteName.SERIES.name) {
                        ListSeriesBuildListView(navController)
                    }

                    composable(
                        "DetailScreen/{movieId}",
                        arguments = listOf(navArgument("movieId") { type = NavType.IntType })
                    ){item->
                        val movieId = item.arguments?.getInt("movieId")
                        DetalhesFilmeView(navController = navController, movieId = movieId!!)
                    }
                } )
            }
        }

    }


    /// em teste
//    sealed class Screen(val route: String, val title: String, val iconId: ImageVector) {
//        object Home : Screen("listaFilmes", "Filmes", Icons.Outlined.Home)
//
//        object Settings : Screen("series", "Series", Icons.Outlined.Info)
//    }
//
//    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//    @OptIn(ExperimentalMaterial3Api::class)
//    @Composable
//    fun MyApp() {
//        val navController = rememberNavController()
//
//        Scaffold(
//            bottomBar = {
//                NavigationBar(
//                    modifier = Modifier.fillMaxWidth().size(75.dp)
//                ) {
//                    val currentRoute = navController.currentBackStackEntry?.destination?.route
//                    val items = listOf(Screen.Home, Screen.Settings)
//
//                    items.forEach { screen ->
//                        NavigationBarItem(
//                            icon = { Icon(screen.iconId, contentDescription = null) },
//                            label = { Text(text = screen.title) },
//                            selected = currentRoute == screen.route,
//                            onClick = {
//                                navController.navigate(screen.route) {
//
//                                    popUpTo(navController.graph.findStartDestination().id) {
//                                        saveState = true
//                                    }
//
//                                    launchSingleTop = true
//                                    restoreState = true
//                                }
//
//                            }
//
//                        )
//                    }
//                }
//            }
//        ) {
//            NavHost(navController = navController , startDestination= "listaFilmes", builder = {
//                composable("listaFilmes") {
//                    ListaCustom(navController)
//                }
//                composable(
//                    "DetailScreen/{movieId}",
//                    arguments = listOf(navArgument("movieId") { type = NavType.IntType })
//                ){item->
//                    val movieId = item.arguments?.getInt("movieId")
//                    DetalhesFilmeView(navController = navController, movieId = movieId!!)
//                }
//                composable(
//                    "series"
//                ){
//                    ListSeriesBuildListView(navController = navController)
//                }
//            } )
//        }
//
//
//    }











}






