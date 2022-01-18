package br.com.alura.technews.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.alura.technews.R
import br.com.alura.technews.model.Noticia
import br.com.alura.technews.ui.activity.common.constants.MENSAGEM_FALHA_CARREGAR_NOTICIAS
import br.com.alura.technews.ui.activity.common.constants.TITULO_APPBAR
import br.com.alura.technews.ui.activity.lista_noticias.viewmodel.ListaNoticiasViewModel
import br.com.alura.technews.ui.fragments.extensions.mostraErro
import br.com.alura.technews.ui.recyclerview.adapter.ListaNoticiasAdapter
import kotlinx.android.synthetic.main.fragment_lista_noticias.*
import org.koin.android.viewmodel.ext.android.viewModel

class ListaNoticiasFragment : Fragment() {

    private val viewModel: ListaNoticiasViewModel by viewModel()

    private val adapter by lazy {
        context?.let {
            ListaNoticiasAdapter(context = it)
        } ?: throw IllegalArgumentException("Contexto inválido")
    }

    var quandoNoticiaSelecionada: (noticia: Noticia) -> Unit = {}
    var quandoFabSalvaNoticiaClicada: () -> Unit = {}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        buscaNoticias()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lista_noticias, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = TITULO_APPBAR

        configuraFabAdicionaNoticia()
        configuraRecyclerView()
    }

    private fun configuraFabAdicionaNoticia() {
        lista_noticias_fab_salva_noticia.setOnClickListener {
            quandoFabSalvaNoticiaClicada()
        }
    }

    private fun configuraRecyclerView() {
        val divisor = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        lista_noticias_recyclerview.addItemDecoration(divisor)
        lista_noticias_recyclerview.adapter = adapter
        configuraAdapter()
    }

    private fun configuraAdapter() {
        adapter.quandoItemClicado = quandoNoticiaSelecionada
    }

    private fun buscaNoticias() {
        viewModel.buscaTodos().observe(this, Observer { resource ->
            resource.dado?.let { adapter.atualiza(it) }
            resource.erro?.let {
                mostraErro(MENSAGEM_FALHA_CARREGAR_NOTICIAS)
            }
        })
    }

}