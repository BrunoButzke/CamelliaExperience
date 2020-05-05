package com.example.esquentahackathon.jsonFiles

import android.util.Log
import android.widget.Toast
import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONArray
import com.alibaba.fastjson.JSONObject
import com.google.gson.JsonArray

class FakeDataBase {

        private val questions = """
            [{
              " beginner " : [
                {
                  " question " : " Qual é o PIOR lugar para se guardar dinheiro ? " ,
                  " answers " : [
                    " Poupança " ,
                    " Embaixo do colchão " ,
                    " Corretora " ,
                    " Carteira "
                  ],
                  " correctIndex " : 2
                },
                {
                  " question " : " Qual é o MELHOR local para se guardar dinheiro? " ,
                  " answers " : [
                    " Conta-corrente " ,
                    " Embaixo do colchão " ,
                    " Investimento " ,
                    " Poupança "
                  ],
                  " correctIndex " : 3
                },
                {
                  " question " : " Com o aumento da inflação e a manutenção do rendimento da poupança, é mais vantajoso: " ,
                  " answers " : [
                    " Utilizar fundo de investimentos " ,
                    " Utilizar a poupança " ,
                    " Utilizar a conta corrente " ,
                    " Não utilizar nada "
                  ],
                  " correctIndex " : 1
                },
                {
                  " question " : " Para construir um grande capital financeiro, é melhor: " ,
                  " answers " : [
                    " Poupar muito as vezes " ,
                    " Não poupar " ,
                    " Poupar pouco de forma constante " ,
                    " Jogar na mega sena "
                  ],
                  " correctIndex " : 3
                },
                {
                  " question " : " Para ter uma maior segurança nos seus investimentos e mesmo assim ter uma rentabilidade desejável, a melhor estratégia é: " ,
                  " answers " : [
                    " Só investir em renda fixa " ,
                    " Fazer bolão com amigos do trabalho " ,
                    " Só investir em renda variável " ,
                    " Diversificar os investimentos "
                  ],
                  " correctIndex " : 4
                }
                ],
              " intermediate " : [
                {
                  " question " : " É mais seguro colocar o seu dinheiro em um negócio, um investimento, ou em várias empresas e investimentos? " ,
                  " answers " : [
                    " Um Negócio " ,
                    " Múltiplas empresas e investimentos " ,
                    " Um Investimento " ,
                    " Nenhuma das alternativas "
                  ],
                  " correctIndex " : 2
                },
                {
                  " question " : " Suponha que, ao longo dos próximos anos, os preços das coisas dupliquem. Se sua renda também duplicar, você poderá comprar... " ,
                  " answers " : [
                    " Menos coisas " ,
                    " Mais coisas " ,
                    " O dobro de coisas " ,
                    " A mesma quantia de coisas "
                  ],
                  " correctIndex " : 4
                },
                {
                  " question " : " Suponha que você precise tomar emprestados 100 reais. Qual é o valor mais baixo a pagar de volta a quem emprestou? " ,
                  " answers " : [
                    " 105 reais " ,
                    " 100 reais + 3% " ,
                    " 100 reais + 5% " ,
                    " 95 reias  + 10% "
                  ],
                  " correctIndex " : 2
                },
                {
                  " question " : " Você colocou seu dinheiro no banco por dois anos com uma taxa de 15% ao ano. O banco irá adicionar mais dinheiro à sua conta em que ano ? " ,
                  " answers " : [
                    " Mesma quantia nos dois anos " ,
                    " Mais no primeiro do que no segundo " ,
                    " Mais no segundo ano do que no primeiro " ,
                    " Mais no terceiro ano "
                  ],
                  " correctIndex " : 3
                },
                {
                  " question " : " Dentro de todos os títulos públicos, quais você compraria pensando na sua aposentadoria? " ,
                  " answers " : [
                    " IPCA+ sem juros " ,
                    " Título pré-fixado " ,
                    " IPCA+ com juros semestrais " ,
                    " Ações no geral "
                  ],
                  " correctIndex " : 1
                }
              ],
              " advanced " : [
                {
                  " question " : " Qual é a maior diferença entre ações ordinárias e ações preferenciais? " ,
                  " answers " : [
                    " As ações ordinárias dão direito a voto em assembleias " ,
                    " As ações preferenciais dão direito a voto em assembleias " ,
                    " As ações ordinárias dão preferência na distribuição de dividendos " ,
                    " As Ações afirmativas dão direito a voto em assembleias"
                  ],
                  " correctIndex " : 1
                },
                {
                  " question " : " Quais seriam exemplos de títulos pré-fixados, pós-fixados e mistos, respectivamente? " ,
                  " answers " : [
                    " Selic, IPCA+, debenture " ,
                    " Debenture, IPCA+, Selic " ,
                    " IPCA+,  Selic, debenture " ,
                    " Dêbenture, Selic, IPCA "
                  ],
                  " correctIndex " : 4
                },
                {
                  " question " : " Quais são os investimentos em renda fixa que possuem cobertura pelo FGC? " ,
                  " answers " : [
                    " debêntures e CDB " ,
                    " Tesouro Selic e debêntures " ,
                    " CDB, LCI e LCA " ,
                    " Nenhuma das anteriores "
                  ],
                  " correctIndex " : 3
                },
                {
                  " question " : " Suponha que você tenha certa quantia aplicada a uma taxa de 12% ao ano. Qual é a taxa de equivalência ao mês? " ,
                  " answers " : [
                    " 1% " ,
                    " 0,3% " ,
                    " 0,95% " ,
                    " 0,0095% "
                  ],
                  " correctIndex " : 4
                },
                {
                  " question " : " Com a queda da taxa Selic, quais dos títulos a seguir teria uma valorização no seu valor presente? " ,
                  " answers " : [
                    " Pré-fixado de longo prazo " ,
                    " IPCA+ " ,
                    " Selic " ,
                    " IPCA com juros semestrais "
                  ],
                  " correctIndex " : 1
                },
              ]
            }]
        """

    companion object {
//        const val BEGINNER = 0
//        const val ADVANCED = 1
//        const val INTERMEDIATE = 2

        const val BEGINNER = "beginner"
        const val INTERMEDIATE = "intermediate"
        const val ADVANCED = "advanced"

        const val QUESTION = 0
        const val ANSWERS = 1
        const val CORRECT = 2
    }

    private var json = JSON.parseArray(questions)
    private val questionsList: JSONObject = json.getJSONObject(0)

    //((((questionsList.toList()[0].second) as JSONArray).toArray()[0] as Map<*, *>).toList()[0].second as JSONArray).toArray()[0]

    private fun getDataQuestions(difficulty: String, question: Int): Map<*, *>? {

        var infoList = listOf(
            questionsList.toList()[0],
            questionsList.toList()[1],
            questionsList.toList()[2]
        )

        infoList.forEach { info ->
            if(info.first.removeSurrounding(" ", " ") == difficulty) {
                return ((info.second) as JSONArray).toArray()[question] as Map<*, *>
            }
        }

        return null
        //return ((questionsList.toList()[difficulty].second) as JSONArray).toArray()[question] as Map<*, *>
    }

    // View one information of a question
    fun getSpecificInfo(difficulty: String, question: Int, info: Int): Any? {
        return getDataQuestions(difficulty, question)!!.toList()[info].second
    }

    // View all information for a question
    fun getAllInfoOfQuestion(difficulty: String, question: Int): Map<String, Any?> {

        var map = mutableMapOf<String, Any?>()

        var infoList = listOf(
            getSpecificInfo(difficulty, question, QUESTION),
            getSpecificInfo(difficulty, question, ANSWERS),
            getSpecificInfo(difficulty, question, CORRECT)
        )

        infoList.forEach { info ->

            when (info) {
                is String -> {
                    map["question"] = info
                }
                is Int -> {
                    map["correctAnswer"] = info
                }
                is JSONArray -> {
                    map["answerOne"] = info.toArray()[0]
                    map["answerTwo"] = info.toArray()[1]
                    map["answerThree"] = info.toArray()[2]
                    map["answerFour"] = info.toArray()[3]
                }
            }
        }
        return map
    }
}