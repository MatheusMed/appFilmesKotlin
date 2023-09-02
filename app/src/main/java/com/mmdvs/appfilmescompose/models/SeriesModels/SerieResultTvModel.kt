package com.mmdvs.appfilmescompose.models.SeriesModels

import com.mmdvs.appfilmescompose.models.FilmesModels.DetalheFilme
import com.mmdvs.appfilmescompose.models.FilmesModels.FilmesModel

data class SerieResultTvModel(
  val page: Int,
  val results: List<SerieResultModel>,
  val total_pages: Int,
  val total_results: Int
)