package com.mmdvs.appfilmescompose.apiData.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.mmdvs.appfilmescompose.models.FilmesModels.FilmesModel
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmesDBDao {
  @Query("SELECT * FROM filmes")
  suspend fun getAll(): Flow<List<FilmesModel>>

  @Query("SELECT * FROM filmes WHERE id IN (:id)")
  suspend fun loadAllByIds(id: IntArray): List<FilmesModel>

  @Insert
  suspend fun insertAll(vararg filmes: FilmesModel)
  @Delete
  suspend fun delete(filmes: FilmesModel)
}