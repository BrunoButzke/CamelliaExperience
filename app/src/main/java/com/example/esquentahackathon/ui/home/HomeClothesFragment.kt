package com.example.esquentahackathon.ui.home

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.esquentahackathon.R
import kotlinx.android.synthetic.main.fragment_select_char.*


class HomeClothesFragment: Fragment() {

    private var coins = 0
    private var money = 0
    private var state = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bundle = this.arguments
        if (bundle != null) {
            coins = bundle.getInt("coinValue", 0)
            money = bundle.getInt("money", 0)
            state = bundle.getInt("state", 0)
        }
                return inflater.inflate(R.layout.fragment_select_char, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setup()
    }

    fun setup() {
        var count = 0
        var images = listOf(
            context?.getDrawable(R.drawable.ic_urso_2),
            context?.getDrawable(R.drawable.ic_raposa_2),
            context?.getDrawable(R.drawable.ic_porco_2)
        )
        camellia.setImageDrawable(images[count])

        leftArrow.setOnClickListener {
            if(count != 0) {
                count --
            }
            camellia.setImageDrawable(images[count])
        }

        rightArrow.setOnClickListener {
            if(count != 2) {
                count ++
            }
            camellia.setImageDrawable(images[count])
        }

        selectButton.setOnClickListener {
            openFragment(HomeFragment(), count)
        }
    }

    private fun openFragment(nextFrag: Fragment, value: Int) {
        val arguments = Bundle()
        arguments.putInt("clotheIndex", value)
        // presets
        arguments.putInt("coinValue", coins)
        arguments.putInt("money", money)
        arguments.putInt("state", if(state == 5) 6 else 10)
        nextFrag.arguments = arguments;

        activity!!.supportFragmentManager
            .beginTransaction()
            .replace(id, nextFrag, "findThisFragment")
            .disallowAddToBackStack()
            .remove(this)
            .commit()
    }
}