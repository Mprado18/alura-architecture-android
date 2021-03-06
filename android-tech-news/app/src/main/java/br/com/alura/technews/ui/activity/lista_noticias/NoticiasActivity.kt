package br.com.alura.technews.ui.activity.lista_noticias

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import br.com.alura.technews.R
import br.com.alura.technews.model.Noticia
import br.com.alura.technews.ui.activity.common.constants.NOTICIA_ID_CHAVE
import br.com.alura.technews.ui.activity.common.extensions.transacaoFragment
import br.com.alura.technews.ui.activity.formulario_noticia.FormularioNoticiaActivity
import br.com.alura.technews.ui.fragments.ListaNoticiasFragment
import br.com.alura.technews.ui.fragments.VisualizaNoticiasFragment
import kotlinx.android.synthetic.main.activity_noticias.*

private const val TAG_FRAGMENT_VISUALIZA_NOTICIA = "visualiza_noticia"

class NoticiasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_noticias)

        configuraFragmentPeloEstado(savedInstanceState)
    }

    private fun configuraFragmentPeloEstado(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            abreListaNoticias()
        } else {
            tentaReabrirFragmentVisualizaNoticia()
        }
    }

    private fun tentaReabrirFragmentVisualizaNoticia() {
        supportFragmentManager.findFragmentByTag(TAG_FRAGMENT_VISUALIZA_NOTICIA)
            ?.let { fragment ->
                val argumentos = fragment.arguments
                val novoFragment = VisualizaNoticiasFragment()
                novoFragment.arguments = argumentos

                removeFragmentVisualizaNoticia(fragment)

                transacaoFragment {
                    val container = configuraContainerFragmentVisualizaNoticia()
                    replace(container, novoFragment, TAG_FRAGMENT_VISUALIZA_NOTICIA)
                }
            }
    }

    private fun FragmentTransaction.configuraContainerFragmentVisualizaNoticia() =
        if (activity_noticias_container_2 != null) {
            R.id.activity_noticias_container_2
        } else {
            addToBackStack(null)
            R.id.activity_noticias_container_1
        }

    private fun abreListaNoticias() {
        transacaoFragment {
            replace(R.id.activity_noticias_container_1, ListaNoticiasFragment())
        }
    }

    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)
        when (fragment) {
            is ListaNoticiasFragment -> {
                configuraListaNoticias(fragment)
            }
            is VisualizaNoticiasFragment -> {
                configuraVisualizaNoticias(fragment)
            }
        }
    }

    private fun configuraVisualizaNoticias(fragment: VisualizaNoticiasFragment) {
        fragment.quandoFinalizaTela = {
            supportFragmentManager.findFragmentByTag(TAG_FRAGMENT_VISUALIZA_NOTICIA)
                ?.let { fragment ->
                    removeFragmentVisualizaNoticia(fragment)
                }
        }
        fragment.quandoSelecionaMenuEdicao = this::abreFormularioEdicao
    }

    private fun removeFragmentVisualizaNoticia(fragment: Fragment) {
        transacaoFragment {
            remove(fragment)
        }
        supportFragmentManager.popBackStack()
    }

    private fun configuraListaNoticias(fragment: ListaNoticiasFragment) {
        fragment.quandoFabSalvaNoticiaClicada = this::abreFormularioModoCriacao
        fragment.quandoNoticiaSelecionada = this::abreVisualizadorNoticia
    }

    private fun abreFormularioModoCriacao() {
        val intent = Intent(this, FormularioNoticiaActivity::class.java)
        startActivity(intent)
    }

    private fun abreVisualizadorNoticia(noticia: Noticia) {
        val fragment = VisualizaNoticiasFragment()
        val dados = Bundle()
        dados.putLong(NOTICIA_ID_CHAVE, noticia.id)
        fragment.arguments = dados

        transacaoFragment {
            val container = configuraContainerFragmentVisualizaNoticia()
            replace(container, fragment, TAG_FRAGMENT_VISUALIZA_NOTICIA)
        }
    }

    private fun abreFormularioEdicao(noticia: Noticia) {
        val intent = Intent(this, FormularioNoticiaActivity::class.java)
        intent.putExtra(NOTICIA_ID_CHAVE, noticia.id)
        startActivity(intent)
    }

}
