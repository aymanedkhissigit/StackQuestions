package fr.mastersid.dkhissi.stackquestions.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import fr.mastersid.dkhissi.stackquestions.R
import fr.mastersid.dkhissi.stackquestions.adapters.QuestionsAdapter
import fr.mastersid.dkhissi.stackquestions.objects.Question
import fr.mastersid.dkhissi.stackquestions.repositories.StackRepository
import fr.mastersid.dkhissi.stackquestions.viewmodels.StackViewModel
import kotlinx.android.synthetic.main.fragment_main.*


@AndroidEntryPoint
class MainFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val stackViewModel : StackViewModel by viewModels ()


        //Appel
        val questionsAdapter =  QuestionsAdapter()
        myRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = questionsAdapter
        }
        //----

        stackViewModel.getQuestionsList().observe(viewLifecycleOwner, Observer {
            questionsAdapter.submitList(it)
        })

        myRefresh.setOnRefreshListener {
            stackViewModel.updateQuestionsList()

        }

        stackViewModel.requestState.observe(viewLifecycleOwner, Observer {
            when (it) {
                StackRepository.RequestState.NONE_OR_DONE -> myRefresh.isRefreshing=false
                StackRepository.RequestState.PENDING -> myRefresh.isRefreshing=true
                StackRepository.RequestState.ERROR_NETWORK -> errorNetwork()
                StackRepository.RequestState.ERROR_REQUETE -> errorRequest()
            }
        })





    }

    private fun errorNetwork() {
        Toast.makeText(activity,"Network Error",Toast.LENGTH_SHORT).show()
        myRefresh.isRefreshing=false
    }

    private fun errorRequest() {
        Toast.makeText(activity,"Request Error",Toast.LENGTH_SHORT).show()
        myRefresh.isRefreshing=false
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }


}