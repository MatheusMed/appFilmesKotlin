package com.mmdvs.appfilmescompose.apiData.database.repository

import com.mmdvs.appfilmescompose.models.FilmesModels.FilmesModel

interface FilmeRepository {

  suspend fun insert(filme: FilmesModel)

  suspend fun getAllFilmes(): List<FilmesModel>


}