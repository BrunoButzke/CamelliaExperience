package com.example.esquentahackathon.ui.config

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.support.v4.app.Fragment
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import com.example.esquentahackathon.R
import com.example.esquentahackathon.ui.home.HomeFragment
import kotlinx.android.synthetic.main.fragment_config.*

class ConfigFragment : Fragment() {

    private var coins = 0
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
            coins = bundle.getInt("coinValue", 0)
            money = bundle.getInt("money", 0)
            state = bundle.getInt("state", 0)
            clotheIndex = bundle.getInt("clotheIndex", 0)
        }
        return inflater.inflate(R.layout.fragment_config, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setup()
    }

    fun setup() {
        saveAndQuit.setOnClickListener {
            openFragment(HomeFragment())
        }
    }

    private fun openFragment(nextFrag: Fragment) {
        val arguments = Bundle()
        arguments.putString("goal", goal.text.toString())
        arguments.putString("amountCredited", amountCredited.text.toString())
        arguments.putBoolean("beginningMonth", beginningMonth.isChecked)
        // presets
        arguments.putInt("coinValue", coins)
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
