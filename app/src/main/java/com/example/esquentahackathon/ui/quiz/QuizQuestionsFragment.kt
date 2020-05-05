package com.example.esquentahackathon.ui.quiz

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import com.example.esquentahackathon.R
import com.example.esquentahackathon.jsonFiles.FakeDataBase
import com.example.esquentahackathon.jsonFiles.FakeDataBase.Companion.ADVANCED
import com.example.esquentahackathon.jsonFiles.FakeDataBase.Companion.BEGINNER
import com.example.esquentahackathon.jsonFiles.FakeDataBase.Companion.INTERMEDIATE
import kotlinx.android.synthetic.main.fragment_questions.*
import java.lang.Exception


class QuizQuestionsFragment: Fragment() {

    private lateinit var viewModel: QuizViewModel
    private val fakeDB = FakeDataBase()
    private var difficulty: String = BEGINNER
    private var oldCoinValue = 0
    private var score = 0
    private var maxPossibleErrors = 3
    private var money = 0
    private var state = 0
    private var clotheIndex = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bundle = this.arguments
        if (bundle != null) {
            difficulty = bundle.getString("difficulty", BEGINNER)
            oldCoinValue = bundle.getInt("coinValue", 0)
            money = bundle.getInt("money", 0)
            state = bundle.getInt("state", 0)
            clotheIndex = bundle.getInt("clotheIndex", 0)
        }
        viewModel = ViewModelProviders.of(this).get(QuizViewModel::class.java)
        return inflater.inflate(R.layout.fragment_questions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        registerObserver()
    }

    private fun registerObserver() {
        viewModel.questionIndex.observe(this, Observer {
            it?.let { index ->
                try {
                    val question = fakeDB.getAllInfoOfQuestion(difficulty, index)
                    var loseScore = 0
                    var winScore = 4
                    var multiplierPerDifficulty = when(difficulty) {
                        BEGINNER -> 1
                        INTERMEDIATE -> 2
                        else -> 3
                    }

                    textView.text = question["question"].toString()
                    answerOne.text = question["answerOne"].toString()
                    answerTwo.text = question["answerTwo"].toString()
                    answerThree.text = question["answerThree"].toString()
                    answerFour.text = question["answerFour"].toString()

                    val failButtons = mutableListOf(answerOne, answerTwo, answerThree, answerFour)
                    val successButton = failButtons[question["correctAnswer"].toString().toInt() - 1]

                    failButtons.forEach { failButton ->
                        failButton.background = context?.getDrawable(R.drawable.circular_border)
                        failButton.setTextColor(ContextCompat.getColor(context!!, R.color.green));

                        failButton.setOnClickListener { button ->
                            button.background = context?.getDrawable(R.drawable.failed_mark)
                            (button as Button).setTextColor(ContextCompat.getColor(context!!, R.color.white));
                            val animShake = AnimationUtils.loadAnimation(context, R.anim.shake);
                            button.startAnimation(animShake);
                            if(loseScore < maxPossibleErrors) loseScore++
                        }
                    }

                    successButton.background = context?.getDrawable(R.drawable.circular_border)
                    successButton.setTextColor(ContextCompat.getColor(context!!, R.color.green));

                    successButton.setOnClickListener { button ->
                        button.background = context?.getDrawable(R.drawable.checked_mark)
                        val animShake = AnimationUtils.loadAnimation(context, R.anim.slide);
                        button.startAnimation(animShake);

                        score += (winScore - loseScore) * multiplierPerDifficulty
                        Handler().postDelayed({
                            viewModel.questionIndex.value = viewModel.questionIndex.value?.plus(1)
                        }, 800)
                    }
                } catch (error: Exception) {
                    openFragment(QuizDoneFragment(), score)
                }
            }
        })
    }

    private fun openFragment(nextFrag: Fragment, value: Int) {
        val arguments = Bundle()
        arguments.putInt("coinValue", value)
        arguments.putInt("oldCoinValue", oldCoinValue)
        // presets
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