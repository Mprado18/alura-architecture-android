package br.com.alura.technews.ui.activity.lista_noticias

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import br.com.alura.technews.R
import br.com.alura.technews.model.Noticia
import br.com.alura.technews.ui.activity.common.constants.MENSAGEM_FALHA_CARREGAR_NOTICIAS
import br.com.alura.technews.ui.activity.common.constants.NOTICIA_ID_CHAVE
import br.com.alura.technews.ui.activity.common.constants.TITULO_APPBAR
import br.com.alura.technews.ui.activity.common.extensions.mostraErro
import br.com.alura.technews.ui.activity.formulario_noticia.FormularioNoticiaActivity
import br.com.alura.technews.ui.activity.lista_noticias.viewmodel.ListaNoticiasViewModel
import br.com.alura.technews.ui.activity.visualiza_noticia.VisualizaNoticiaActivity
import br.com.alura.technews.ui.recyclerview.adapter.ListaNoticiasAdapter
import kotlinx.android.synthetic.main.activity_lista_noticias.*
import org.koin.android.viewmodel.ext.android.viewModel

class ListaNoticiasActivity : AppCompatActivity() {

    private val viewModel: ListaNoticiasViewModel by viewModel()

    private val adapter by lazy {
        ListaNoticiasAdapter(context = this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_noticias)
        title = TITULO_APPBAR
        configuraRecyclerView()
        configuraFabAdicionaNoticia()

        buscaNoticias()
    }

    private fun configuraFabAdicionaNoticia() {
        activity_lista_noticias_fab_salva_noticia.setOnClickListener {
            abreFormularioModoCriacao()
        }
    }

    private fun configuraRecyclerView() {
        val divisor = DividerItemDecoration(this, VERTICAL)
        activity_lista_noticias_recyclerview.addItemDecoration(divisor)
        activity_lista_noticias_recyclerview.adapter = adapter
        configuraAdapter()
    }

    private fun configuraAdapter() {
        adapter.quandoItemClicado = this::abreVisualizadorNoticia
    }

    private fun buscaNoticias() {
        viewModel.buscaTodos().observe(this, Observer { resource ->
            resource.dado?.let { adapter.atualiza(it) }
            resource.erro?.let {
                mostraErro(MENSAGEM_FALHA_CARREGAR_NOTICIAS)
            }
        })
    }

    private fun abreFormularioModoCriacao() {
        val intent = Intent(this, FormularioNoticiaActivity::class.java)
        startActivity(intent)
    }

    private fun abreVisualizadorNoticia(it: Noticia) {
        val intent = Intent(this, VisualizaNoticiaActivity::class.java)
        intent.putExtra(NOTICIA_ID_CHAVE, it.id)
        startActivity(intent)
    }

}