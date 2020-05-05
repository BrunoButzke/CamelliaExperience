package com.example.esquentahackathon.ui.quiz

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.esquentahackathon.R
import com.example.esquentahackathon.ui.home.HomeFragment
import kotlinx.android.synthetic.main.fragment_done.*

class QuizDoneFragment: Fragment() {

    private lateinit var viewModel: QuizViewModel
    private var coinValue = 0
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
            coinValue = bundle.getInt("coinValue", 0)
            oldCoinValue = bundle.getInt("oldCoinValue", 0)
            money = bundle.getInt("money", 0)
            state = bundle.getInt("state", 0)
            clotheIndex = bundle.getInt("clotheIndex", 0)
        }
        viewModel = ViewModelProviders.of(this).get(QuizViewModel::class.java)
        return inflater.inflate(R.layout.fragment_done, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupView()
    }

    @SuppressLint("SetTextI18n")
    private fun setupView() {

        coins.text = "+ $coinValue"

        tryAgain.setOnClickListener {
            openFragment(QuizFragment())
        }

        homeButton.setOnClickListener {
            openFragment(HomeFragment())
        }
    }

    private fun openFragment(nextFrag: Fragment) {
        val arguments = Bundle()
        arguments.putInt("coinValue", coinValue + oldCoinValue)
        // presets
        arguments.putInt("money", money)
        arguments.putInt("state", if(state == 3 || state == 4) 4 else 10)
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