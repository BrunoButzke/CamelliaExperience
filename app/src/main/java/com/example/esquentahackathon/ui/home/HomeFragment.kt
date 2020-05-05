package com.example.esquentahackathon.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.support.v4.app.Fragment
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Handler
import android.view.MotionEvent
import com.example.esquentahackathon.R
import com.example.esquentahackathon.ui.config.ConfigFragment
import com.example.esquentahackathon.ui.quiz.QuizFragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private var state = 0
    private var money = 0
    private var clotheIndex = 3
    private var coinValue = 0
    // config info
    private var goal = 100
    private var amountCredited = 50
    private var beginningMonth = false

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val bundle = this.arguments
        if (bundle != null) {
            // config info
            goal = bundle.getString("goal", "100").toInt()
            amountCredited = bundle.getString("amountCredited", "0").toInt()
            beginningMonth = bundle.getBoolean("beginningMonth", false)
            // presets
            state = bundle.getInt("state", 0)
            money = bundle.getInt("money", 0)
            coinValue = bundle.getInt("coinValue", 0)
            clotheIndex = bundle.getInt("clotheIndex", 3)

        }
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setup()
    }

    fun setup() {

        var sadnessImages = listOf(
            context?.getDrawable(R.drawable.ic_urso_1),
            context?.getDrawable(R.drawable.ic_raposa_1),
            context?.getDrawable(R.drawable.ic_porco_1),
            context?.getDrawable(R.drawable.ic_sem_roupa_1)
        )

        var defaultImages = listOf(
            context?.getDrawable(R.drawable.ic_urso_2),
            context?.getDrawable(R.drawable.ic_raposa_2),
            context?.getDrawable(R.drawable.ic_porco_2),
            context?.getDrawable(R.drawable.ic_sem_roupa_2)
        )

        var happyImages = listOf(
            context?.getDrawable(R.drawable.ic_urso_3),
            context?.getDrawable(R.drawable.ic_raposa_3),
            context?.getDrawable(R.drawable.ic_porco_3),
            context?.getDrawable(R.drawable.ic_sem_roupa_3)
        )

        var veryHappyImages = listOf(
            context?.getDrawable(R.drawable.ic_urso_4),
            context?.getDrawable(R.drawable.ic_raposa_4),
            context?.getDrawable(R.drawable.ic_porco_4),
            context?.getDrawable(R.drawable.ic_sem_roupa_4)
        )

        coinsValue.text = coinValue.toString()
        moneyValue.text = money.toString()

        when (state) {
            0 -> {
                quizButton.visibility = View.GONE
                balao.visibility = View.VISIBLE
                textView.visibility = View.VISIBLE
                textView.text = "Olá eu sou a Camellia, estou aqui pra te ajudar á alcançar seus objetivos!"
                state++
            }
            4 -> {
                balao.visibility = View.VISIBLE
                textView.visibility = View.VISIBLE
                clotheButton.visibility = View.VISIBLE
                textView.text = "Divertido né ? E parece que vc ganhou alguma coisa."
                state++
            }
            6 -> {
                balao.visibility = View.VISIBLE
                textView.visibility = View.VISIBLE
                textView.text = "Nossa fiquei linda, obrigada!"
                state++
            }
        }


        camellia.setOnClickListener {
            when (state){
                1 -> {
                    textView.text = "Mas vamos começar pequeno, conhecimento nunca é demais, então ..."
                    state++
                }
                2 -> {
                    textView.text = "Responda o meu QUIZ e depois venha me visitar!"
                    state++
                }
                3 -> {
                    balao.visibility = View.GONE
                    textView.visibility = View.GONE
                    openFragment(QuizFragment())
                }
                5 -> {
                    textView.text = "Vamos. É só clicar no presentinho."
                }
                7 -> {
                    textView.text = "Venha me visitar todos os dias para ganhar mais coisinhas."
                    state++
                }
                8 -> {
                    balao.visibility = View.VISIBLE
                    textView.visibility = View.VISIBLE
                    textView.text = "Agora você pode acessar o meu QUIZ no botão ali em cima e ..."
                    state++
                }
                9 -> {
                    textView.text = "ganhar varias moedinha para concorrer a gift cards."
                    state++
                }
                10 -> {
                    balao.visibility = View.GONE
                    textView.visibility = View.GONE
                    state++
                }
                11 -> {
                    balao.visibility = View.VISIBLE
                    textView.visibility = View.VISIBLE
                    if (beginningMonth) {
                        textView.text = "Vamos guardar um dinheirinho? Faz um deposito! é pro seu bem..."
                    } else {
                        textView.text = "Vamos jogar um QUIZ !?"
                        camellia.setImageDrawable(defaultImages[clotheIndex])
                    }
                    state--
                }
            }
        }

        if (beginningMonth) {
            camellia.setImageDrawable(sadnessImages[clotheIndex])
            balao.visibility = View.VISIBLE
            textView.visibility = View.VISIBLE
            textView.text = "Quando você fica tanto tempo sem economizar, sinto que me esqueceu..."
        } else {
            camellia.setImageDrawable(defaultImages[clotheIndex])
        }

        if (state > 9 && amountCredited > 0) {
            depositButton.visibility = View.VISIBLE
        }

        configButton.setOnClickListener {
            openFragment(ConfigFragment())
        }

        quizButton.setOnClickListener {
            openFragment(QuizFragment())
        }

        clotheButton.setOnClickListener {
            openFragment(HomeClothesFragment())
        }

        depositButton.setOnClickListener {
            money += amountCredited
            moneyValue.text = money.toString()
            balao.visibility = View.VISIBLE
            textView.visibility = View.VISIBLE
            if(beginningMonth) {
                if(money >= goal) {
                    camellia.setImageDrawable(veryHappyImages[clotheIndex])
                    textView.text = "Que felicidade!!! Você esta um passinho mais próximo dos seus objetivos!"
                    beginningMonth = false
                } else {
                    camellia.setImageDrawable(happyImages[clotheIndex])
                    textView.text = "É um bom começo, não conseguimos chegar na meta, na próxima vamos conseguir!"
                    beginningMonth = false
                }
            } else {
                camellia.setImageDrawable(veryHappyImages[clotheIndex])
                textView.text = "Estou orgulhosa de você, um passinho de cada vez!"
            }
        }
    }

    private fun openFragment(nextFrag: Fragment) {
        val arguments = Bundle()
        arguments.putInt("coinValue", coinValue)
        arguments.putInt("money", money)
        arguments.putInt("state", state)
        arguments.putInt("clotheIndex", clotheIndex)
        nextFrag.arguments = arguments;

        activity!!.supportFragmentManager
            .beginTransaction()
            .replace(id, nextFrag, "findThisFragment")
            .disallowAddToBackStack()
            .remove(this)
            .commit()
    }
}
