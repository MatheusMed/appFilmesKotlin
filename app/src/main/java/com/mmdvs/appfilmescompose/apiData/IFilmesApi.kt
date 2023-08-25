package com.mmdvs.appfilmescompose.apiData

import com.mmdvs.appfilmescompose.api.HttpHelper
import com.mmdvs.appfilmescompose.models.FilmeResposta
import retrofit2.Response
import retrofit2.http.GET

interface IFilmesApi {

    @GET("movie/popular?language=pt-BR&api_key=${HttpHelper.API_KEY}")
    suspend fun getFilmesPopulares() : Response<FilmeResposta>

}