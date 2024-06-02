package com.ammaryasser.masb7ty.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.ammaryasser.masb7ty.data.Tasabee7Repository
import com.ammaryasser.masb7ty.data.Tasbee7
import kotlinx.coroutines.launch


class Masba7tyScreenViewModel(private val app: Application) : AndroidViewModel(app) {

    private val repo = Tasabee7Repository.build(app.applicationContext)

    private val _tasabee7 = MutableLiveData<List<Tasbee7>>()
    val tasabee7: LiveData<List<Tasbee7>> get() = _tasabee7


    init {
        viewModelScope.launch {
            repo.getAll().collect {
                _tasabee7.postValue(it)
            }
        }
    }


    fun updateCountOf(id: Int, count: Int) {
        viewModelScope.launch { repo.updateCountOf(id, count) }
    }

    fun saveTasbee7(tasbee7: Tasbee7) {
        viewModelScope.launch { repo.insertOrUpdate(tasbee7) }
    }

    fun getByIdThen(id: Int, then: (Tasbee7) -> Unit) {
        viewModelScope.launch {
            repo.getById(id).collect {
                it?.run {
                    then(this)
                }
            }
        }
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = (this[APPLICATION_KEY] as Application)

                Masba7tyScreenViewModel(app)
            }
        }
    }
}