package com.example.esquentahackathon.ui.quiz

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.Toast
import com.example.esquentahackathon.R
import com.example.esquentahackathon.jsonFiles.FakeDataBase
import com.example.esquentahackathon.jsonFiles.FakeDataBase.Companion.BEGINNER
import kotlinx.android.synthetic.main.fragment_questions.*
import java.lang.Exception


class QuizQuestionsFragment: Fragment() {

    private lateinit var viewModel: QuizViewModel
    private val fakeDB = FakeDataBase()
    private var difficulty: Int = BEGINNER

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bundle = this.arguments
        if (bundle != null) {
            difficulty = bundle.getInt("difficulty", BEGINNER)
        }
        viewModel = ViewModelProviders.of(this).get(QuizViewModel::class.java)
        return inflater.inflate(R.layout.fragment_questions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        registerObserver()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.toolbar, R.id.fab, android.R.id.home -> {
                Toast.makeText(context,"toolbar",Toast.LENGTH_LONG).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun registerObserver() {
        viewModel.questionIndex.observe(this, Observer {
            it?.let { index ->
                try {
                    val question = fakeDB.getAllInfoOfQuestion(difficulty, index)

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
                        }
                    }

                    successButton.background = context?.getDrawable(R.drawable.circular_border)
                    successButton.setTextColor(ContextCompat.getColor(context!!, R.color.green));

                    successButton.setOnClickListener { button ->
                        button.background = context?.getDrawable(R.drawable.checked_mark)
                        val animShake = AnimationUtils.loadAnimation(context, R.anim.slide);
                        button.startAnimation(animShake);
                        Handler().postDelayed({
                            viewModel.questionIndex.value = viewModel.questionIndex.value?.plus(1)
                        }, 800)
                    }
                } catch (error: Exception) {
                    // TODO
                }

            }
        })
    }
}