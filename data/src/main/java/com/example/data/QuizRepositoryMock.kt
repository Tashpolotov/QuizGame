package com.example.data

import android.content.Context
import com.example.domain.model.QestionsModel
import com.example.domain.repository.QuizRepository

class QuizRepositoryMock(val context: Context):QuizRepository {


    override fun questions(): List<QestionsModel> {
        return listOf(
            QestionsModel(context.getString(R.string.quesrtions1),
            listOf(context.getString(R.string.answer1), 
                context.getString(R.string.answer2), context.getString(R.string.answer3)), "2",
                context.getString(R.string.zeus),
                context.getString(R.string.poseidon), "1000", "1000",
                R.drawable.zeus, R.drawable.poseidon, R.drawable.background1),

  
            QestionsModel(context.getString(R.string.quesrtions2),
                listOf(context.getString(R.string.answer4),
                    context.getString(R.string.answer5), 
                    context.getString(R.string.answer6)), "0",  context.getString(R.string.zeus),
                context.getString(R.string.apollo), "1000", "1000",
                R.drawable.zeus, R.drawable.apollo, R.drawable.background2),
   
            QestionsModel(context.getString(R.string.quesrtions3),
                listOf(context.getString(R.string.answer7), 
                    context.getString(R.string.answer8), 
                    context.getString(R.string.answer9)), "2",  context.getString(R.string.zeus),
                context.getString(R.string.hades), "1000", "1000",
                R.drawable.zeus, R.drawable.hades, R.drawable.background3),

            QestionsModel(context.getString(R.string.quesrtions5),
                listOf(context.getString(R.string.answer13), 
                    context.getString(R.string.answer14), 
                    context.getString(R.string.answer15)), "1",  context.getString(R.string.zeus),
                context.getString(R.string.hermes), "1000", "1000",
                R.drawable.zeus, R.drawable.hermes, R.drawable.background5),
   
            QestionsModel(context.getString(R.string.quesrtions6),
                listOf(context.getString(R.string.answer16), 
                    context.getString(R.string.answer17),
                    context.getString(R.string.answer18)), "2",  context.getString(R.string.zeus),
                context.getString(R.string.hades), "1000", "1000",
                R.drawable.zeus, R.drawable.dyonysus, R.drawable.background6),
 
            
            QestionsModel(context.getString(R.string.quesrtions7),
                listOf(context.getString(R.string.answer19), 
                    context.getString(R.string.answer20), 
                    context.getString(R.string.answer312)), "0",  context.getString(R.string.zeus),
                context.getString(R.string.hephaestus), "1000", "1000",
                R.drawable.zeus, R.drawable.hephaestus, R.drawable.background7),
   

            QestionsModel(context.getString(R.string.quesrtions8),
                listOf(context.getString(R.string.answer22), 
                    context.getString(R.string.answer23), 
                    context.getString(R.string.answer24)), "2",  context.getString(R.string.zeus),
                context.getString(R.string.cupid), "1000", "1000",
                R.drawable.zeus, R.drawable.cupid, R.drawable.background8),
  
            
            QestionsModel(context.getString(R.string.quesrtions9),
                listOf(context.getString(R.string.answer25), 
                    context.getString(R.string.answer26), 
                    context.getString(R.string.answer27)), "2",  context.getString(R.string.zeus),
                context.getString(R.string.pan), "1000", "1000",
                R.drawable.zeus, R.drawable.pan, R.drawable.background9),

            QestionsModel(context.getString(R.string.quesrtions4),
                listOf(context.getString(R.string.answer10),
                    context.getString(R.string.answer11),
                    context.getString(R.string.answer12)), "2",  context.getString(R.string.zeus),
                context.getString(R.string.ares), "1000", "1000",
                R.drawable.zeus, R.drawable.ares, R.drawable.background4),


            )
    }
}