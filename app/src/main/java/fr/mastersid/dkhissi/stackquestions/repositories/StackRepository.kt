package fr.mastersid.dkhissi.stackquestions.repositories


import androidx.lifecycle.LiveData
import fr.mastersid.dkhissi.stackquestions.backend.StackDao
import fr.mastersid.dkhissi.stackquestions.backend.StackWebservice
import fr.mastersid.dkhissi.stackquestions.objects.Question
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

import retrofit2.HttpException

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StackRepository @Inject constructor ( val stackWebservice: StackWebservice, val stackDao: StackDao){



    // state flow
    enum class RequestState {
        NONE_OR_DONE, PENDING , ERROR_NETWORK, ERROR_REQUETE
    }
    val requestState = MutableStateFlow(RequestState.NONE_OR_DONE)
    //------


    fun getQuestionsList() : Flow<List<Question>>{
       return stackDao.getQuestionsList()
    }


    suspend fun updateQuestionsList() {

        try {
        requestState.emit(RequestState.PENDING)
        stackDao.insertAll(stackWebservice.getQuestionsList())
        requestState.emit(RequestState.NONE_OR_DONE)
    } catch (exception: okio.IOException){
            requestState.emit(RequestState.ERROR_NETWORK)
    }
        catch (exception: HttpException){
            requestState.emit(RequestState.ERROR_REQUETE)
        }

    }




}