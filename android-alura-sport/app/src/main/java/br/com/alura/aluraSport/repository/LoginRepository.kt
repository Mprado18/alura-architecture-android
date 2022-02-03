package br.com.alura.aluraSport.repository

import android.content.SharedPreferences
import androidx.core.content.edit

private const val CHAVE_LOGADO = "LOGADO"

class LoginRepository(private val preferences: SharedPreferences) {

    fun loga() = salva(true)

    fun desloga() = salva(false)

    private fun salva(estado: Boolean) {
        preferences.edit {
            putBoolean(CHAVE_LOGADO, estado)
        }
    }

    fun estaLogado(): Boolean = preferences.getBoolean(CHAVE_LOGADO, false)

}
