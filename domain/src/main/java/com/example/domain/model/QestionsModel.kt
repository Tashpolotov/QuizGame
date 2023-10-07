package com.example.domain.model

data class QestionsModel(
    val questions:String,
    val answer:List<String>,
    val currentAnswer:String,
    var nameZeus:String,
    var nameEnemy:String,
    var hpZeus:String,
    var hpEnemy:String,
    val imgZeus:Int,
    val imgEnemy:Int,
    val bg:Int,

    )