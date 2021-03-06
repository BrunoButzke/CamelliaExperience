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
import com.example.esquentahackathon.ui.home.HomeFragment
import kotlinx.android.synthetic.main.fragment_quiz.*


class QuizFragment : Fragment() {

    private lateinit var viewModel: QuizViewModel
    private var oldCoinValue = 0
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
            oldCoinValue = bundle.getInt("coinValue", 0)
            money = bundle.getInt("money", 0)
            state = bundle.getInt("state", 0)
            clotheIndex = bundle.getInt("clotheIndex", 0)
        }
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

        intermediate.setOnClickListener {
            openFragment(QuizQuestionsFragment(), INTERMEDIATE)
        }

        advanced.setOnClickListener {
            openFragment(QuizQuestionsFragment(), ADVANCED)
        }

        cancelButton.setOnClickListener {
            openFragment(HomeFragment())
        }
    }

    private fun openFragment(nextFrag: Fragment, difficulty: String = BEGINNER) {
        val arguments = Bundle()
        arguments.putString("difficulty", difficulty)
        arguments.putInt("coinValue", oldCoinValue)
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
