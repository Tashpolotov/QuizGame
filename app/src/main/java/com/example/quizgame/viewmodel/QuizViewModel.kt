package com.example.quizgame.viewmodel

import androidx.lifecycle.ViewModel
import com.example.domain.repository.QuizRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(val repository: QuizRepository):ViewModel() {

    private val _quiz = MutableStateFlow(QuizState())
    val quiz : StateFlow<QuizState> = _quiz

    private var zeusHP = 1000

    fun getZeusHP(): Int {
        return zeusHP
    }

    fun decreaseZeusHP() {
        zeusHP -= 100
    }

    fun loadQuestions(){
        val questions = repository.questions()
        _quiz.value = _quiz.value.copy(quiz = questions)
    }

}