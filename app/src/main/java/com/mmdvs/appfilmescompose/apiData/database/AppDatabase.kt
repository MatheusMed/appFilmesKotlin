package com.mmdvs.appfilmescompose.apiData.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.mmdvs.appfilmescompose.models.FilmesModels.FilmesModel


@Database(entities = [FilmesModel::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

  abstract val FilmesDB: FilmesDBDao

    companion object {
      @Volatile
      private var INSTANCE: AppDatabase? = null

      fun getInstance(context: Context): Any = INSTANCE ?: synchronized(this) {
        var instance: AppDatabase? = INSTANCE
        if (instance == null) {
          instance = databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "filmes.db"
          ).build()
        }
        return instance
      }
    }
}