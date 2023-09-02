package com.mmdvs.appfilmescompose.api

import com.mmdvs.appfilmescompose.apiData.IFilmesApi
import com.mmdvs.appfilmescompose.apiData.ISeriesApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HttpHelper {
    companion object{

        const val API_KEY = "a858faa7e3471048ecfd025841284026"
        const val  BASE_URL = "https://api.themoviedb.org/3/"
        const val BASE_ULR_IMAGE="https://image.tmdb.org/t/p/"

        val apiFilmes = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build().create(IFilmesApi::class.java)
        val apiSeries =  Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build().create(ISeriesApi::class.java)
    }

}