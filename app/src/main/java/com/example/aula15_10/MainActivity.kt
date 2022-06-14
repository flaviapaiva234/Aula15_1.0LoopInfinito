package com.example.aula15_10

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.aula15_10.databinding.ActivityMainBinding

// esse app da erro pq fica com um loop infinito na thread principal, fazerdo app travar

class MainActivity : AppCompatActivity() {
    //1.
    private lateinit var binding: ActivityMainBinding

    private var number: Int = 0
    private var isLoopStarted: Boolean = false // vai dizer se esse loop vai acontecer ou não

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        clickEvents() // chamando a função para lidar com os clicks dos eventos
    }
    // vai ter um loop, e enquanto ele vai rodando, vai incrementando uma variável e vai imprimir na minha tela
    //assim que apertar um dos botões vai começar isso

    //criar uma função que vai lidar com o click de cada função
    private fun clickEvents(){
        binding.apply{
            //assim que clicar no botão de iniciar, o loop será iniciado
            btnStart.setOnClickListener {
                isLoopStarted = true //vai iniciar o loop
                initLoop()
            }

            // esse botão seria para fazer parar o loop
            btnEnd.setOnClickListener {
                isLoopStarted = false // se o loop der false, para a contagem quando for clicado

            }

        }

      }

    //2. vai iniciar o loop
    private fun initLoop(){
        while (isLoopStarted) { // quando essa variaável for true, vai ficar rodando e vai continuar nesse processo até que essa variável se torne false
            number++ // a cada interação desse laço vai ser incrementado um number
            binding.textView.text = "$number" // e mostrar na minha tela o valor desse número incrementado
            Log.i(ContentValues.TAG, " Contagem: " + number) // esse Lop.i é tipo um print que vai imprimir resultados no painel RUN (na parte inferior do android Studio), o restante é só para fazer essa impressão funcionar

        }
    }
}

//quando clicka no botão start inicia o loop e foca fazendo uma contagem infinita, sendo incrementado e não parando nunca
// quando clica no botão end, depois de 5 segundos chega uma menssagem falando que o app não está respondndo, mas na verdade o loop infinito está na thread principal atrapalhando tudo.
//isso acontece pq essas ações vão entrar em uma especie de fila, e elas vão acontecendo em ordem, quando uma ação acaba outra é iniciada.
// no código acima as ações entraram na fila (thread), mas como uma das ações não acaba nunca, travou todas as outras.
// por padrão todos os eventos ocorrem em uma thread única
// se um evento muito demorado entrar nessa fila (thread) o app vai enviar uma menssagem e fecha, isso se chama erro de ANR,
// é quando um evento muito demorado entrar nessa thread impedindo ações mais simples de serem executadas, como por exemplo um click de botão.

// a solução seria colocar essa ação muito demorada em outra thread, só para esse evnto demorado, chamado de thread em segundo plano (thread in backgrownd)
// dá para fazer isso usando a ferramenta coroutine que tem que ser implementado no build.gradle que é: implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9"

// resultado no aoo Aula15_1.1Funcionando
