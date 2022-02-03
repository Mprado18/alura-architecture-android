package br.com.alura.aluraSport.ui.viewmodel

import androidx.lifecycle.ViewModel
import br.com.alura.aluraSport.repository.LoginRepository

class LoginViewModel(private val repository: LoginRepository) : ViewModel() {

    fun loga() = repository.loga()

    fun desloga() = repository.desloga()

    fun estaLogado(): Boolean = repository.estaLogado()

    fun naoEstaLogado(): Boolean = !estaLogado()
}
