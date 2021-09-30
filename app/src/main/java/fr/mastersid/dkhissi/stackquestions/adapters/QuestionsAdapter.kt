package fr.mastersid.dkhissi.stackquestions.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.mastersid.dkhissi.stackquestions.R
import fr.mastersid.dkhissi.stackquestions.objects.Question


class QuestionsAdapter( ) : androidx.recyclerview.widget.ListAdapter<Question,
        QuestionViewHolder>(Question.DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            QuestionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)
        return QuestionViewHolder(view)
    }

    override fun onBindViewHolder (holder : QuestionViewHolder, position : Int ) {
        val question = getItem(position)
        holder.txt_question.text = "${question.question}"
        holder.txt_countNumber.text = "${question.countNumber}"
    }


}

class QuestionViewHolder ( view : View) : RecyclerView. ViewHolder ( view ) {

    val txt_question : TextView = view . findViewById (R.id.txt_question )
    val txt_countNumber : TextView = view . findViewById (R.id.txt_countNumber )
}