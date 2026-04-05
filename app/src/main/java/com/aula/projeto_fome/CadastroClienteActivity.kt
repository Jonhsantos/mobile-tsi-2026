package com.aula.projeto_fome

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CadastroClienteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cadastro_cliente)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnVoltar = findViewById<ImageButton>(R.id.btnVoltarCriarContaCliente)
        val btnCriarConta = findViewById<ImageButton>(R.id.btnCriarContaLojista)

        val nomeCliente = findViewById<EditText>(R.id.inputNomeCadastroLojista)
        val inputEmailCliente = findViewById<EditText>(R.id.inputEmailCadastroLojista)
        val inputSenhaCliente = findViewById<EditText>(R.id.inputSenhaCadsastroLojista)


        btnVoltar.setOnClickListener {
            finish()
        }
    }
}