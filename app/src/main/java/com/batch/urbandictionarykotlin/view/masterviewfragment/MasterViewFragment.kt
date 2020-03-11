package com.batch.urbandictionarykotlin.view.masterviewfragment


import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.batch.urbandictionarykotlin.adapter.DefinitionAdapter
import com.batch.urbandictionarykotlin.databinding.FragmentRecyclerViewBinding
import com.batch.urbandictionarykotlin.dto.Definition
import com.batch.urbandictionarykotlin.viewmodel.DefinitionViewModel


class MasterViewFragment : Fragment() {

    lateinit var viewModel: DefinitionViewModel
    lateinit var definitionList:List<Definition>
    private var adapter: DefinitionAdapter? = null
    lateinit var bind:FragmentRecyclerViewBinding
    lateinit var layoutManager: LinearLayoutManager
    private var toggle = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentRecyclerViewBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(DefinitionViewModel::class.java)
        layoutManager = LinearLayoutManager(requireActivity())
        bind.recyclerview.setLayoutManager(layoutManager)
        adapter = DefinitionAdapter()
        bind.recyclerview.setAdapter(adapter)

        definitionList = ArrayList()
        setupObservers()
        setupListeners()
        return bind.root
    }
    private fun setupObservers(){
        viewModel.getDefinitions().observe(viewLifecycleOwner, androidx.lifecycle.Observer {definitions ->
            if(definitions!!.size > 0){
                definitionList = definitions
                adapter!!.addDefinitions(definitions)
                layoutManager.scrollToPosition(0)
            }else{
                Toast.makeText(requireContext(),"Query returned nothing-",Toast.LENGTH_SHORT).show()
            }

        })
        viewModel.getShowProgressBar().observe(viewLifecycleOwner, androidx.lifecycle.Observer{loading ->
            if(loading){
                bind.progressBar.visibility = View.VISIBLE
            }else{
                bind.progressBar.visibility = View.GONE
            }


        })


    }
    private fun setupListeners(){
        bind.searchButton.setOnClickListener {
            viewModel.getDefinitionsObservable(bind.searchBar.text.toString())
            val inputMethodManager =
                requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            if (inputMethodManager.isActive)
                inputMethodManager.hideSoftInputFromWindow(bind.root.windowToken,0)
        }
        bind.sortButton.setOnClickListener {
            if(toggle){
                sortList(toggle)
                toggle = false
            }else{
                sortList(toggle)
                toggle = true
            }
        }
    }
    fun sortList(like:Boolean){
        if(like){
            Log.d("TAG","INSED FIRST")
            adapter!!.addDefinitions(
                definitionList
                    .sortedWith(compareByDescending{it.thumbsUp})
            )
        }else{
            Log.d("TAG","INSED SECOND")
            adapter!!.addDefinitions(
                definitionList
                    .sortedWith(compareByDescending{it.thumbsDown})
            )
        }
    }
}



