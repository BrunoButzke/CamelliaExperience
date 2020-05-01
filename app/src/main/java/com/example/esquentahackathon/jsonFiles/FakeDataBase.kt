package com.example.esquentahackathon.jsonFiles

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONArray
import com.alibaba.fastjson.JSONObject

class FakeDataBase {

        private val questions = """
            [{
              " beginner " : [
                {
                  " question " : " Qual é o pior lugar para se guardar dinheiro ? " ,
                  " answers " : [
                    " Poupança " ,
                    " Debaixo do colchão " ,
                    " Corretora " ,
                    " Carteira "
                  ],
                  " correctIndex " : 2
                },
                {
                  " question " : " 1 + 1? " ,
                  " answers " : [
                    " 0 " ,
                    " 1 " ,
                    " 2 " ,
                    " 3 "
                  ],
                  " correctIndex " : 3
                }
                ],
              " intermediate " : [
                {
                  " question " : " 2 + 2? " ,
                  " answers " : [
                    " 1 " ,
                    " 4 " ,
                    " 8 " ,
                    " 16 "
                  ],
                  " correctIndex " : 2
                }
              ],
              " advanced " : [
                {
                  " question " : " 3 + 3? " ,
                  " answers " : [
                    " 6 " ,
                    " 16 " ,
                    " 66 " ,
                    " 33 "
                  ],
                  " correctIndex " : 1
                }
              ]
            }]
        """

    companion object {
        const val BEGINNER = 0
        const val ADVANCED = 1
        const val INTERMEDIATE = 2

        const val ANSWERS = 0
        const val CORRECT = 1
        const val QUESTION = 2
    }

    private var json = JSON.parseArray(questions)
    private val questionsList: JSONObject = json.getJSONObject(0)

    //((((questionsList.toList()[0].second) as JSONArray).toArray()[0] as Map<*, *>).toList()[0].second as JSONArray).toArray()[0]

    private fun getDataQuestions(difficulty: Int, question: Int): Map<*, *> {
        return ((questionsList.toList()[difficulty].second) as JSONArray).toArray()[question] as Map<*, *>
    }

    // View one information of a question
    fun getSpecificInfo(difficulty: Int, question: Int, info: Int): Any? {
        return getDataQuestions(difficulty, question).toList()[info].second
    }

    // View all information for a question
    fun getAllInfoOfQuestion(difficulty: Int, question: Int): Map<String, Any?> {
        return mapOf(
            "question" to getSpecificInfo(difficulty, question, QUESTION),
            "answerOne" to (getSpecificInfo(difficulty, question, ANSWERS) as JSONArray).toArray()[0],
            "answerTwo" to (getSpecificInfo(difficulty, question, ANSWERS) as JSONArray).toArray()[1],
            "answerThree" to (getSpecificInfo(difficulty, question, ANSWERS) as JSONArray).toArray()[2],
            "answerFour" to (getSpecificInfo(difficulty, question, ANSWERS) as JSONArray).toArray()[3],
            "correctAnswer" to getSpecificInfo(difficulty, question, CORRECT)
        )
    }

}