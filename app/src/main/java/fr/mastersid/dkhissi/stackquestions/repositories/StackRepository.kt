package fr.mastersid.dkhissi.stackquestions.repositories


import androidx.lifecycle.LiveData
import fr.mastersid.dkhissi.stackquestions.backend.StackDao
import fr.mastersid.dkhissi.stackquestions.backend.StackWebservice
import fr.mastersid.dkhissi.stackquestions.objects.Question
import javax.inject.Inject

class StackRepository @Inject constructor ( val stackWebservice: StackWebservice, val stackDao: StackDao){




    fun getQuestionsList() : LiveData<List<Question>>{

       return stackDao.getQuestionsList()
    }


    suspend fun updateQuestionsList() {
        stackDao.insertAll(stackWebservice.getQuestionsList())
    }




}