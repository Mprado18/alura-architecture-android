package br.com.alura.aluraSport.ui.viewmodel

import androidx.lifecycle.ViewModel
import br.com.alura.aluraSport.repository.ProdutoRepository

class DetalhesProdutoViewModel(
    produtoId: Long,
    repository: ProdutoRepository
) : ViewModel() {

    val produtoEncontrado = repository.buscaPorId(produtoId)

}