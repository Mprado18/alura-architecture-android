package br.com.alura.aluraSport.database.dao

import androidx.room.Dao
import androidx.room.Insert
import br.com.alura.aluraSport.model.Pagamento

@Dao
interface PagamentoDAO {

    @Insert
    fun salva(pagamento: Pagamento) : Long

}