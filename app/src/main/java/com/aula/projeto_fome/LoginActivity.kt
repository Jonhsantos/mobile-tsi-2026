package com.aula.projeto_fome

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback import retrofit2.Response import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory import retrofit2.http.GET
import retrofit2.http.Query


class LoginActivity : AppCompatActivity() {
    private lateinit var inputEmail: EditText
    private lateinit var inputSenha: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tela_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val jornada = intent.getBooleanExtra("JORNADA", false)
        val btnVoltar = findViewById<ImageButton>(R.id.btnVoltar)
        inputEmail = findViewById(R.id.inputEmailLogin)
        inputSenha = findViewById(R.id.inputSenhaLogin)
        val btnEsqueciSenha = findViewById<Button>(R.id.btnEsqueciSenha)
        val btnEntar = findViewById<Button>(R.id.btnEntrar)
        val btnCriarConta = findViewById<Button>(R.id.btnCriarConta)

        btnVoltar.setOnClickListener {
            finish()
        }

        btnCriarConta.setOnClickListener {
            if (jornada) {
                val intent = android.content.Intent(this, CadastroLojistaActivity::class.java)
                startActivity(intent)
            } else {
                val intent = android.content.Intent(this, CadastroClienteActivity::class.java)
                startActivity(intent)
            }
        }
        btnEntar.setOnClickListener {
            blockLogin()
        }
    }
    private fun blockLogin() {
        val email = inputEmail.text.toString().trim()
        val password = inputSenha.text.toString().trim()


        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.15.100/ ")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiService = retrofit.create(ApiService::class.java)


        val call = apiService.login(email, password)
        call.enqueue(object : Callback<List<LoginResponse>> {
            override fun onResponse(
                call: Call<List<LoginResponse>>,
                response: Response<List<LoginResponse>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    val loginResponses = response.body()!!
                    if (loginResponses.isNotEmpty()) {
                        val intent = Intent(
                            this@LoginActivity,
                            HomePageActivity::class.java
                        )
                        startActivity(intent)
                        finish()
                    } else {

                        Toast.makeText(
                            this@LoginActivity,
                            "Usuário ou senha inválidos",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } else {
                    Toast.makeText(this@LoginActivity, "Erro no login", Toast.LENGTH_LONG)
                        .show()
                }
            }

            override fun onFailure(call: Call<List<LoginResponse>>, t: Throwable) {
                Toast.makeText(
                    this@LoginActivity, "Erro: ${t.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }


    interface ApiService {
        @GET("/apis/login.php")
        fun login(
            @Query("usuario") usuario: String, @Query("senha") senha: String
        ): Call<List<LoginResponse>>
    }
}




