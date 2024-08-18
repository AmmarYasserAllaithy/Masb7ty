package com.ammaryasser.masb7ty.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.ammaryasser.masb7ty.data.Tasabee7Repository
import com.ammaryasser.masb7ty.data.Tasbee7
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class Tasabee7ScreenViewModel(app: Application) : AndroidViewModel(app) {

    private val repo = Tasabee7Repository.build(app.applicationContext)

    private val _tasabee7 = MutableStateFlow<List<Tasbee7>>(emptyList())
    val tasabee7: StateFlow<List<Tasbee7>> get() = _tasabee7


    init {
        fetchTasabee7()
    }


    private fun fetchTasabee7() {
        viewModelScope.launch {
            repo.getAll().collect {
                _tasabee7.emit(it)
            }
        }
    }

    fun saveTasbee7(tasbee7: Tasbee7) {
        viewModelScope.launch { repo.insertOrUpdate(tasbee7) }
    }

    fun deleteTasbee7(tasbee7: Tasbee7) {
        viewModelScope.launch { repo.delete(tasbee7) }
    }

    fun clearAllCounters() {
        viewModelScope.launch { repo.clearAllCounters() }
    }

    fun unifyAllTargets(value: Int) {
        viewModelScope.launch { repo.unifyAllTargets(value) }
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = (this[APPLICATION_KEY] as Application)

                Tasabee7ScreenViewModel(app)
            }
        }
    }
}