package br.com.alura.aluraSport.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.alura.aluraSport.model.Produto
import br.com.alura.aluraSport.repository.ProdutoRepository

class ProdutosViewModel(private val repository: ProdutoRepository) : ViewModel() {

    fun buscaTodos(): LiveData<List<Produto>> = repository.buscaTodos()

}
