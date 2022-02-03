package br.com.alura.aluraSport.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import br.com.alura.aluraSport.R
import br.com.alura.aluraSport.ui.viewmodel.EstadoAppViewModel
import kotlinx.android.synthetic.main.main_activity.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val controlador by lazy { findNavController(R.id.main_activity_nav_host) }

    private val viewModel: EstadoAppViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        
        controlador.addOnDestinationChangedListener { controller, destination, argumentos ->
            title = destination.label
            viewModel.componentes.observe(this, Observer {
                it?.let { temComponentes ->
                    if (temComponentes.appBar) {
                        supportActionBar?.show()
                    } else {
                        supportActionBar?.hide()
                    }
                    if (temComponentes.bottomNavigation) {
                        main_activity_bottom_navigation.isVisible = true
                    } else {
                        main_activity_bottom_navigation.isVisible = false
                    }
                }
            })
        }
        main_activity_bottom_navigation.setupWithNavController(controlador)
    }

}
