package com.example.esquentahackathon.ui.quiz

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.esquentahackathon.R
import com.example.esquentahackathon.jsonFiles.FakeDataBase.Companion.ADVANCED
import com.example.esquentahackathon.jsonFiles.FakeDataBase.Companion.BEGINNER
import com.example.esquentahackathon.jsonFiles.FakeDataBase.Companion.INTERMEDIATE
import kotlinx.android.synthetic.main.fragment_quiz.*


class QuizFragment : Fragment() {

    private lateinit var viewModel: QuizViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(QuizViewModel::class.java)
        return inflater.inflate(R.layout.fragment_quiz, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupView()
    }

    private fun setupView() {
        textView.text = context?.getText(R.string.quiz_title)
        beginner.text = context?.getText(R.string.quiz_beginner)
        intermediate.text = context?.getText(R.string.quiz_intermediate)
        advanced.text = context?.getText(R.string.quiz_advanced)

        beginner.setOnClickListener {
            openFragment(QuizQuestionsFragment(), BEGINNER)
        }

        advanced.setOnClickListener {
            openFragment(QuizQuestionsFragment(), ADVANCED)
        }

        intermediate.setOnClickListener {
            openFragment(QuizQuestionsFragment(), INTERMEDIATE)
        }
    }

    private fun openFragment(nextFrag: Fragment, difficulty: Int) {
        val arguments = Bundle()
        arguments.putInt("difficulty", difficulty)
        nextFrag.arguments = arguments;

        activity!!.supportFragmentManager
            .beginTransaction()
            .replace(id, nextFrag, "findThisFragment")
            .disallowAddToBackStack()
            .remove(this)
            .commit()
    }
}
