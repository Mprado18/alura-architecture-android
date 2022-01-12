package br.com.alura.technews.ui.activity.common.extensions

import android.app.Activity
import android.widget.Toast

fun Activity.mostraErro(mensagem: String) {
    Toast.makeText(
        this,
        mensagem,
        Toast.LENGTH_LONG
    ).show()
}