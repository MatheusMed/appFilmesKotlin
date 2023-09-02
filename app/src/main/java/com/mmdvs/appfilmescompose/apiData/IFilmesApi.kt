package com.mmdvs.appfilmescompose.apiData

import com.mmdvs.appfilmescompose.api.HttpHelper
import com.mmdvs.appfilmescompose.models.FilmesModels.DetalheFilme
import com.mmdvs.appfilmescompose.models.FilmesModels.FilmeResposta
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface IFilmesApi {

    @GET("movie/popular?language=pt-BR&api_key=${HttpHelper.API_KEY}")
    suspend fun getFilmesPopulares() : Response<FilmeResposta>

    @GET("movie/{movie_id}?language=pt-BR&api_key=${HttpHelper.API_KEY}")
    suspend fun getDetalheFilmes(
        @Path("movie_id") movieId: Int
    ) : Response<DetalheFilme>

}