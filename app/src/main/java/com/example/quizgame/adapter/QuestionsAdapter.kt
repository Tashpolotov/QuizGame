    package com.example.quizgame.adapter

    import android.annotation.SuppressLint
    import android.view.LayoutInflater
    import android.view.ViewGroup
    import androidx.recyclerview.widget.DiffUtil
    import androidx.recyclerview.widget.ListAdapter
    import androidx.recyclerview.widget.RecyclerView
    import com.bumptech.glide.Glide
    import com.example.domain.model.QestionsModel
    import com.example.quizgame.R
    import com.example.quizgame.databinding.ItemQuestionsBinding

    class QuestionsAdapter:ListAdapter<QestionsModel, QuestionsAdapter.ViewHolder>(Diff()) {

        private val answeredQuestions = mutableSetOf<Int>()
        private val selectedAnswer = mutableMapOf<Int, Int>()
        private var onAnswerClickListener: OnAnswerClickListener? = null


        interface OnAnswerClickListener{
            fun onAnswerClick(selectedIndex : Int)
        }
        fun setOnAnswerClickListener(listener: OnAnswerClickListener) {
            onAnswerClickListener = listener
        }


        inner class ViewHolder(private val binding:ItemQuestionsBinding):RecyclerView.ViewHolder(binding.root) {

            private var correctAnswerIndex: Int = 0
            private var isAnswered: Boolean = false
            init {
                binding.const1.setOnClickListener { onAnswerClick(0) }
                binding.const2.setOnClickListener { onAnswerClick(1) }
                binding.const3.setOnClickListener { onAnswerClick(2) }

            }

            @SuppressLint("SuspiciousIndentation")
            private fun onAnswerClick(selectedIndex: Int) {
                if (!isAnswered) {
                    isAnswered = true
                    answeredQuestions.add(adapterPosition)
                    selectedAnswer[adapterPosition] = selectedIndex

                    // Проверить, является ли выбранный ответ правильным
                    val isCorrectAnswer = selectedIndex == correctAnswerIndex

                    onAnswerClickListener?.onAnswerClick(selectedIndex)
                }
            }


            fun bind(model: QestionsModel, position: Int) {
                binding.tvQuestions.text = model.questions
                binding.tvAnswer1.text = model.answer[0]
                binding.tvAnswer2.text = model.answer[1]
                binding.tvAnswer3.text = model.answer[2]





                Glide.with(binding.root)
                    .load(model.imgZeus)
                    .into(binding.imgZeus)

                Glide.with(binding.root)
                    .load(model.imgEnemy)
                    .into(binding.imgEnemy)
                correctAnswerIndex = model.currentAnswer.toInt()

                isAnswered = answeredQuestions.contains(adapterPosition)


            }

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(ItemQuestionsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(getItem(position), position)
        }
        fun clearAnsweredQuestions() {
            answeredQuestions.clear()
            selectedAnswer.clear()
            notifyDataSetChanged()
        }
    }

    class Diff:DiffUtil.ItemCallback<QestionsModel>() {
        override fun areItemsTheSame(oldItem: QestionsModel, newItem: QestionsModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: QestionsModel, newItem: QestionsModel): Boolean {
            return oldItem == newItem
        }

    }
