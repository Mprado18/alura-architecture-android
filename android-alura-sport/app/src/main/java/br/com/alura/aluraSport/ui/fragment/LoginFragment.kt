package br.com.alura.aluraSport.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.alura.aluraSport.R
import br.com.alura.aluraSport.ui.viewmodel.ComponentesVisuais
import br.com.alura.aluraSport.ui.viewmodel.EstadoAppViewModel
import br.com.alura.aluraSport.ui.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.login.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private val controlador by lazy {
        findNavController()
    }

    private val viewModel: LoginViewModel by viewModel()
    private val estadoAppViewModel: EstadoAppViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        estadoAppViewModel.temComponentes = ComponentesVisuais()
        login_botao_logar.setOnClickListener {
            viewModel.loga()
            vaiParaListaProdutos()
        }
        login_botao_cadastrar.setOnClickListener {
            val actionLoginToCadastroUsuario =
                LoginFragmentDirections.actionLoginToCadastroUsuario()
            controlador.navigate(actionLoginToCadastroUsuario)
        }
    }

    private fun vaiParaListaProdutos() {
        val actionLoginToListaProdutos = LoginFragmentDirections.actionLoginToListaProdutos()
        controlador.navigate(actionLoginToListaProdutos)
    }

}