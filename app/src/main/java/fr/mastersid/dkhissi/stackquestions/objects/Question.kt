package fr.mastersid.dkhissi.stackquestions.objects

import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity( tableName = "question_table")
data class Question (@PrimaryKey val question : String, val countNumber : Int ) {

    class DiffCallback : DiffUtil.ItemCallback<Question>() {
        override fun areItemsTheSame(oldItem: Question, newItem: Question): Boolean {
            return oldItem.question == newItem.question
        }

        override fun areContentsTheSame(oldItem: Question, newItem: Question): Boolean {
            return  oldItem.question == newItem.question
                    && oldItem.countNumber == newItem.countNumber
        }
    }
}
