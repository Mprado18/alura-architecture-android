package br.com.alura.aluraSport.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.alura.aluraSport.database.dao.PagamentoDAO
import br.com.alura.aluraSport.model.Pagamento
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PagamentoRepository(private val dao: PagamentoDAO) {

    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.IO + job)

    fun salva(pagamento: Pagamento): LiveData<Resource<Long>> {
        return MutableLiveData<Resource<Long>>().also { liveDate ->
            scope.launch {
                val idPagamento = dao.salva(pagamento)
                liveDate.postValue(Resource(idPagamento))
            }
        }
    }

}