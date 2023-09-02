package com.mmdvs.appfilmescompose.apiData.database.repository

import com.mmdvs.appfilmescompose.models.FilmesModels.FilmesModel
import kotlinx.coroutines.flow.Flow

interface FilmeRepository {

  suspend fun insert(filme: FilmesModel)

  suspend fun getAllFilmes(): Flow<List<FilmesModel>>


}