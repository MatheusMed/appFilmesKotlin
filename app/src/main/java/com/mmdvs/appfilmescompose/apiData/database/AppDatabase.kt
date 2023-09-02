package com.mmdvs.appfilmescompose.apiData.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.mmdvs.appfilmescompose.models.FilmesModels.FilmesModel


@Database(entities = [FilmesModel::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
  abstract fun FilmesDB(): FilmesDBDao
}