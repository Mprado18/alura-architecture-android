package br.com.alura.aluraSport.ui.viewmodel

import androidx.lifecycle.ViewModel
import br.com.alura.aluraSport.model.Pagamento
import br.com.alura.aluraSport.repository.PagamentoRepository
import br.com.alura.aluraSport.repository.ProdutoRepository

class PagamentoViewModel(
    private val pagamentoRepository: PagamentoRepository,
    private val produtodRepository: ProdutoRepository) : ViewModel() {

    fun salva(pagamento: Pagamento) = pagamentoRepository.salva(pagamento)
    fun buscaProdutoPorId(id: Long) = produtodRepository.buscaPorId(id)

}