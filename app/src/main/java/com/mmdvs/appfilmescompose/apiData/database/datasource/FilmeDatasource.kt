package com.mmdvs.appfilmescompose.apiData.database.datasource

import com.mmdvs.appfilmescompose.apiData.database.FilmesDBDao
import com.mmdvs.appfilmescompose.apiData.database.repository.FilmeRepository
import com.mmdvs.appfilmescompose.models.FilmesModels.FilmesModel

class FilmeDatasource(
  private  val filmesDBDao: FilmesDBDao,
): FilmeRepository {
  override suspend fun insert(filme: FilmesModel) {
    filmesDBDao.insertAll(filme)
  }

  override suspend fun getAllFilmes(): List<FilmesModel> {
    return filmesDBDao.getAll()
  }
}