package com.mmdvs.appfilmescompose.models.FilmesModels

data class FilmeResposta(
  val page: Int,
  val results: List<FilmesModel>,
  val total_pages: Int,
  val total_results: Int
)