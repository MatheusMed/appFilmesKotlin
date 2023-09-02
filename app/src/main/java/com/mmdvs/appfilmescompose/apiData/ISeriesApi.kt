package com.mmdvs.appfilmescompose.apiData

import com.mmdvs.appfilmescompose.models.SeriesModels.SerieResultTvModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface ISeriesApi {
  @Headers(
    "Accept: application/json",
    "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhODU4ZmFhN2UzNDcxMDQ4ZWNmZDAyNTg0MTI4NDAyNiIsInN1YiI6IjYxNmQ1NjM5M2Q0ZDk2MDA0MzJmODc0YSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.RFqgvLYN43iNZn5w0274hhrl6Ojzgn7PmebexhiUrzw")
  @GET("trending/tv/day?language=pt-BR")
  suspend fun getSeries() : Response<SerieResultTvModel>
}