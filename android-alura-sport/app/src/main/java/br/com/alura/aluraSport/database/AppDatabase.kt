package br.com.alura.aluraSport.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.alura.aluraSport.database.converter.ConversorBigDecimal
import br.com.alura.aluraSport.database.dao.PagamentoDAO
import br.com.alura.aluraSport.database.dao.ProdutoDAO
import br.com.alura.aluraSport.model.Pagamento
import br.com.alura.aluraSport.model.Produto

@Database(
    version = 2,
    entities = [Produto::class, Pagamento::class],
    exportSchema = false
)
@TypeConverters(ConversorBigDecimal::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun produtoDao(): ProdutoDAO
    abstract fun pagamentoDao(): PagamentoDAO
}