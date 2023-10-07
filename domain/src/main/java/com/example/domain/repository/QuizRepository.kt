package com.example.domain.repository

import com.example.domain.model.QestionsModel

interface QuizRepository {


    fun questions():List<QestionsModel>
}