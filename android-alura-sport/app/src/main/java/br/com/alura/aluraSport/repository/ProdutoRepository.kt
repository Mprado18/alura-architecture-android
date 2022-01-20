package br.com.alura.aluraSport.repository

import androidx.lifecycle.LiveData
import br.com.alura.aluraSport.database.dao.ProdutoDAO
import br.com.alura.aluraSport.model.Produto

class ProdutoRepository(private val dao: ProdutoDAO) {

    fun buscaTodos(): LiveData<List<Produto>> = dao.buscaTodos()

    fun buscaPorId(id: Long): LiveData<Produto> = dao.buscaPorId(id)

}
