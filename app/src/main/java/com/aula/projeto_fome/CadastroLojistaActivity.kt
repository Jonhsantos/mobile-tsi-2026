package com.aula.projeto_fome

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CadastroLojistaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cadastro_lojista)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnVoltar = findViewById<ImageButton>(R.id.btnVoltarCriarContaLojista)
        val btnCriarLoja = findViewById<Button>(R.id.btnCriarContaLojista)

        val inputNomeLoja = findViewById<EditText>(R.id.inputNomeCadastroLojista)
        val inputEmailLoja = findViewById<EditText>(R.id.inputEmailCadastroLojista)
        val inputSenhaLpoja = findViewById<EditText>(R.id.inputSenhaCadsastroLojista)

        btnVoltar.setOnClickListener {
            finish()
        }
    }
}