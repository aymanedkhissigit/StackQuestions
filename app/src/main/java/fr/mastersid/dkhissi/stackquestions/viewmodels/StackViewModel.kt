package fr.mastersid.dkhissi.stackquestions.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.mastersid.dkhissi.stackquestions.objects.Question
import fr.mastersid.dkhissi.stackquestions.repositories.StackRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject




@HiltViewModel
class StackViewModel @Inject constructor (val stackRepository: StackRepository) : ViewModel() {



    fun getQuestionsList() : LiveData<List<Question>> {
        return stackRepository.getQuestionsList()
    }

     fun updateQuestionsList() {
        viewModelScope.launch (Dispatchers.IO) {
            stackRepository.updateQuestionsList()
        }
    }

}


