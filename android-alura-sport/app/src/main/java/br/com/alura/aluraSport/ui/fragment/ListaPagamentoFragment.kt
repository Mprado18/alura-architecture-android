package br.com.alura.aluraSport.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import br.com.alura.aluraSport.R
import br.com.alura.aluraSport.ui.recyclerview.adapter.ListaPagamentosAdapter
import br.com.alura.aluraSport.ui.viewmodel.ComponentesVisuais
import br.com.alura.aluraSport.ui.viewmodel.EstadoAppViewModel
import br.com.alura.aluraSport.ui.viewmodel.PagamentoViewModel
import kotlinx.android.synthetic.main.lista_pagamentos.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ListaPagamentoFragment : BaseFragment() {

    private val adapter: ListaPagamentosAdapter by inject()
    private val viewModel: PagamentoViewModel by viewModel()
    private val estadoAppViewModel: EstadoAppViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.lista_pagamentos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lista_pagamentos_recyclerview.adapter = adapter
        viewModel.todos().observe(viewLifecycleOwner, Observer {
            it?.let { pagamentosEncontrados ->
                adapter.add(pagamentosEncontrados)
            }
        })
        estadoAppViewModel.temComponentes = ComponentesVisuais(
            appBar = true,
            bottomNavigation = true
        )
    }

}