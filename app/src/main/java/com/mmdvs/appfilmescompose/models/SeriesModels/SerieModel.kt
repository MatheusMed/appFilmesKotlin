package com.mmdvs.appfilmescompose.models.SeriesModels

data class SerieModel(
    val page: Int,
    val serieResultModels: List<SerieResultModel>,
    val total_pages: Int,
    val total_results: Int
)