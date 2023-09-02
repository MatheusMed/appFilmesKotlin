package com.mmdvs.appfilmescompose.apiData

import android.content.Context
import androidx.room.Room
import com.mmdvs.appfilmescompose.apiData.database.AppDatabase

class DatabaseApp {
  companion object{

      private var INSTANCE: AppDatabase? = null

      fun getInstance(context: Context): AppDatabase {
        if (INSTANCE == null) {
          synchronized(AppDatabase::class) {
            INSTANCE = Room.databaseBuilder(
              context.applicationContext,
              AppDatabase::class.java, "nome-do-seu-banco-de-dados"
            ).build()
          }
        }
        return INSTANCE!!
      }

  }
}