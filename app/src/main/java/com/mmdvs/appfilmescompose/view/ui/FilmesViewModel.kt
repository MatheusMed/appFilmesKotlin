package com.mmdvs.appfilmescompose.view.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.mmdvs.appfilmescompose.apiData.DatabaseApp
import com.mmdvs.appfilmescompose.apiData.database.AppDatabase
import com.mmdvs.appfilmescompose.apiData.database.datasource.FilmeDatasource
import com.mmdvs.appfilmescompose.apiData.database.repository.FilmeRepository
import com.mmdvs.appfilmescompose.models.FilmesModels.FilmesModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class FilmesViewModel(application: Application) : AndroidViewModel(application) {
  private val repository: FilmeRepository

  val todasNotas: Flow<List<FilmesModel>>

  init {
    val notaDao = DatabaseApp.getInstance(application).FilmesDB()
    repository = FilmeDatasource(notaDao)
    todasNotas = repository.getAllFilmes()
  }

  fun inserirNota(filme: FilmesModel) = viewModelScope.launch {
    repository.insert(filme = filme)
  }

  // Implemente outras funções para atualizar e excluir notas conforme necessário.
}