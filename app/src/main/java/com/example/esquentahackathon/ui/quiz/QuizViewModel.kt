package com.example.esquentahackathon.ui.quiz

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.MutableInt

class QuizViewModel : ViewModel() {

    val questionIndex = MutableLiveData<Int>().apply {
        value = 0
    }
}