package fr.mastersid.dkhissi.stackquestions.viewmodels

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.mastersid.dkhissi.stackquestions.objects.Question
import fr.mastersid.dkhissi.stackquestions.repositories.StackRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject




@HiltViewModel
class StackViewModel @Inject constructor (val stackRepository: StackRepository) : ViewModel() {



    //flow state refresh
    val requestState = stackRepository.requestState.asLiveData()



    fun getQuestionsList() : LiveData<List<Question>> {
        return stackRepository.getQuestionsList().asLiveData()
    }

     fun updateQuestionsList() {
        viewModelScope.launch (Dispatchers.IO) {
            stackRepository.updateQuestionsList()
        }
    }

}


