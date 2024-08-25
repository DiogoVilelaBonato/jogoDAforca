package com.example.jogodaforca

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    private lateinit var palavraSecreta: String
    private lateinit var palavraAtual: StringBuilder
    private var tentativasRestantes = 6

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val palavraTextView: TextView = findViewById(R.id.palavraTextView)
        val tentativasTextView: TextView = findViewById(R.id.tentativasTextView)
        val chuteEditText: EditText = findViewById(R.id.chuteEditText)
        val chuteButton: Button = findViewById(R.id.chuteButton)
        val imagemForca: ImageView = findViewById(R.id.imagemForca)

        palavraSecreta = "KOTLIN"
        palavraAtual = StringBuilder("_".repeat(palavraSecreta.length))
        palavraTextView.text = palavraAtual.toString()
        tentativasTextView.text = "Tentativas restantes: $tentativasRestantes"

        chuteButton.setOnClickListener {
            val letraChutada = chuteEditText.text.toString().uppercase()
            chuteEditText.text.clear()

            if (letraChutada.isNotEmpty() && letraChutada.length == 1) {
                if (palavraSecreta.contains(letraChutada)) {
                    for (i in palavraSecreta.indices) {
                        if (palavraSecreta[i].toString() == letraChutada) {
                            palavraAtual.setCharAt(i, letraChutada[0])
                        }
                    }
                    palavraTextView.text = palavraAtual.toString()
                    if (!palavraAtual.contains("_")) {
                        tentativasTextView.text = "Você venceu!"
                        chuteButton.isEnabled = false
                    }
                } else {
                    tentativasRestantes--
                    tentativasTextView.text = "Tentativas restantes: $tentativasRestantes"
                    atualizarImagemForca(imagemForca)
                    if (tentativasRestantes <= 0) {
                        tentativasTextView.text = "Você perdeu! A palavra era $palavraSecreta"
                        chuteButton.isEnabled = false
                    }
                }
            }
        }
    }

    private fun atualizarImagemForca(imagemForca: ImageView) {
        val imagens = listOf(
            R.drawable.forca_0,
            R.drawable.forca_1,
            R.drawable.forca_2,
            R.drawable.forca_3,
            R.drawable.forca_4,
            R.drawable.forca_5,
            R.drawable.forca_6
        )
        imagemForca.setImageResource(imagens[6 - tentativasRestantes])
    }
}
