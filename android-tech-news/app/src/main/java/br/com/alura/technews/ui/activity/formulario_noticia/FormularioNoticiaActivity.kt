package br.com.alura.technews.ui.activity.formulario_noticia

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import br.com.alura.technews.R
import br.com.alura.technews.model.Noticia
import br.com.alura.technews.ui.activity.common.constants.MENSAGEM_ERRO_SALVAR
import br.com.alura.technews.ui.activity.common.constants.NOTICIA_ID_CHAVE
import br.com.alura.technews.ui.activity.common.constants.TITULO_APPBAR_CRIACAO
import br.com.alura.technews.ui.activity.common.constants.TITULO_APPBAR_EDICAO
import br.com.alura.technews.ui.activity.common.extensions.mostraErro
import br.com.alura.technews.ui.activity.formulario_noticia.viewmodel.FormularioNoticiaViewModel
import kotlinx.android.synthetic.main.activity_formulario_noticia.*
import org.koin.android.viewmodel.ext.android.viewModel

class FormularioNoticiaActivity : AppCompatActivity() {

    private val noticiaId: Long by lazy {
        intent.getLongExtra(NOTICIA_ID_CHAVE, 0)
    }

    private val viewModel: FormularioNoticiaViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_noticia)
        definindoTitulo()
        preencheFormulario()
    }

    private fun definindoTitulo() {
        title = if (noticiaId > 0) {
            TITULO_APPBAR_EDICAO
        } else {
            TITULO_APPBAR_CRIACAO
        }
    }

    private fun preencheFormulario() {
        viewModel.buscaPorId(noticiaId).observe(this, Observer { noticiaEncontrada ->
            if (noticiaEncontrada != null) {
                activity_formulario_noticia_titulo.setText(noticiaEncontrada.titulo)
                activity_formulario_noticia_texto.setText(noticiaEncontrada.texto)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.formulario_noticia_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.formulario_noticia_salva -> {
                val titulo = activity_formulario_noticia_titulo.text.toString()
                val texto = activity_formulario_noticia_texto.text.toString()
                salva(Noticia(noticiaId, titulo, texto))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun salva(noticia: Noticia) {
        viewModel.salva(noticia).observe(this, Observer {
            if (it.erro == null) {
                finish()
            } else {
                mostraErro(MENSAGEM_ERRO_SALVAR)
            }
        })
    }
}
