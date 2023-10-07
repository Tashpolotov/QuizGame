package com.example.quizgame.viewmodel

import com.example.domain.model.QestionsModel

data class QuizState(

    val quiz : List<QestionsModel> = emptyList(),
)