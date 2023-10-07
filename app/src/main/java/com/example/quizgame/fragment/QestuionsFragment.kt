package com.example.quizgame.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.domain.model.QestionsModel
import com.example.quizgame.R
import com.example.quizgame.adapter.QuestionsAdapter
import com.example.quizgame.databinding.FragmentQestuionsBinding
import com.example.quizgame.viewmodel.QuizViewModel

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QestuionsFragment : Fragment() {

    private lateinit var binding: FragmentQestuionsBinding
    private val viewModel by viewModels<QuizViewModel>()
    private val adapter = QuestionsAdapter()
    private var isCurrentQuestionAnswered = false
    private var currentQuestionIndex = 0
    private var correctAnswersCount = 0
    private var questions: List<QestionsModel>? = null
    private val wrongAnswersList = mutableListOf<String>()
    private var hpZeus = 1000 // Начальное значение HP Zeus
    private var hpEnemy = 1000

    private val backgrounds = arrayOf(
        R.drawable.background1,
        R.drawable.background2,
        R.drawable.background3,
        R.drawable.background4,
        R.drawable.background5,
        R.drawable.background6,
        R.drawable.background7,
        R.drawable.background8,
        R.drawable.background9,
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentQestuionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.quiz.collect {
                questions = it.quiz
                adapter.submitList(listOf(questions?.get(currentQuestionIndex)))
            }
        }
        viewModel.loadQuestions()

        initAdapter()

    }

    private fun initAdapter() {
        binding.rv.adapter = adapter

        adapter.setOnAnswerClickListener(object : QuestionsAdapter.OnAnswerClickListener {
            override fun onAnswerClick(selectedIndex: Int) {
                isCurrentQuestionAnswered = true

                val currentQuestion = questions?.get(currentQuestionIndex)
                if (currentQuestion != null) {
                    val correctAnswerIndex = currentQuestion.currentAnswer.toInt()
                    if (selectedIndex != correctAnswerIndex) {
                        // Неправильный ответ
                        val formattedWrongAnswer = "${currentQuestionIndex + 1}. ${currentQuestion.answer[correctAnswerIndex]}"
                        wrongAnswersList.add(formattedWrongAnswer)

                        // Уменьшаем HP персонажа Zeus на 100
                        hpZeus -= 100
                        binding.tvHpZeus.text = hpZeus.toString()

                        // Обновляем значение в адаптере
                        adapter.notifyItemChanged(currentQuestionIndex)
                    } else {
                        hpEnemy -= 100
                        binding.tvHpEnemy.text = hpEnemy.toString()
                        correctAnswersCount++
                    }
                }
                if (currentQuestionIndex < questions?.size ?: 0 && isCurrentQuestionAnswered) {
                    currentQuestionIndex++
                    isCurrentQuestionAnswered = false
                    adapter.clearAnsweredQuestions()
                    updateQuestionCountText()
                    if (currentQuestionIndex < questions?.size ?: 0) {
                        adapter.submitList(listOf(questions?.get(currentQuestionIndex)))
                    }

                    if (currentQuestionIndex == questions?.size ?: 0) {
                        Log.d("QuestionFragment", "Quiz completed. Correct answers: $correctAnswersCount")

                        if (currentQuestionIndex == questions?.size ?: 0) {
                            if (hpZeus > hpEnemy) {
                                // Перейти в фрагмент результатов, так как HP Zeus больше
                                navigateToResultFragment()
                            } else if (hpZeus < hpEnemy) {
                                // Перейти в фрагмент поражения, так как HP enemy больше
                                navigateToLossFragment()
                            } else {
                                // Перейти в фрагмент поражения, так как HP Zeus и HP enemy равны
                                navigateToLossFragment()
                            }
                        }
                    }
                }
            }

        })

        adapter.clearAnsweredQuestions()

    }

    private fun updateQuestionCountText() {
        val currentQuestionNumber = currentQuestionIndex + 1
        val totalQuestions = questions?.size ?: 0
        val questionCountText = "$currentQuestionNumber/$totalQuestions"

        if (currentQuestionIndex < backgrounds.size) {
            val backgroundResId = backgrounds[currentQuestionIndex]
            binding.container.setBackgroundResource(backgroundResId)

        }
         val enemyName = arrayOf(
            getString(R.string.poseidon),
            getString(R.string.apollo),
            getString(R.string.hades),
            getString(R.string.hermes),
            getString(R.string.hades),
            getString(R.string.hephaestus),
            getString(R.string.cupid),
            getString(R.string.pan),
            getString(R.string.ares),

            )
        if (currentQuestionIndex < enemyName.size) {
            val name = enemyName[currentQuestionIndex]
            binding.tvNameEnemy.text = name
        } else {
            // Если текущий индекс выходит за пределы массива enemyName, установите имя по умолчанию или выполните другую логику.
            binding.tvNameEnemy.text =  getString(R.string.ares)
        }


    }
    private fun navigateToResultFragment() {
        val fragment = ResultFragment()
        val args = Bundle()
        args.putInt("correctAnswersCount", correctAnswersCount)
        args.putInt("totalQuestions", questions?.size ?: 0)
        args.putStringArrayList("wrongAnswersList", ArrayList(wrongAnswersList))
        fragment.arguments = args

        parentFragmentManager.beginTransaction()
            .replace(R.id.fr_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun navigateToLossFragment() {
        val fragment = LoseFragment()
        // Передайте нужные данные в фрагмент поражения, если необходимо
        val args = Bundle()
        // args.putString("key", value)
        fragment.arguments = args

        parentFragmentManager.beginTransaction()
            .replace(R.id.fr_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}