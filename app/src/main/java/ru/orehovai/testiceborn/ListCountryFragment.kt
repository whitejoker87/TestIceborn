package ru.orehovai.testiceborn


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.orehovai.testiceborn.databinding.FragmentListCountrysBinding


class ListCountryFragment : Fragment() {

    private val mainViewModel: MainViewModel by lazy {
        ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
    }

    private lateinit var listCountryRecyclerView: RecyclerView

    private lateinit var binding: FragmentListCountrysBinding

//    private lateinit var fabAddPart: FloatingActionButton

    private lateinit var adapter: ContinentListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_countrys, container, false)
        binding.lifecycleOwner = this

        mainViewModel.fetchContinent()



        listCountryRecyclerView = binding.rvCountry
        listCountryRecyclerView.layoutManager = LinearLayoutManager(activity)
        listCountryRecyclerView.addItemDecoration(
            DividerItemDecoration(
                activity!!,
                DividerItemDecoration.VERTICAL
            )
        )


        mainViewModel.listContinents.observe(this, Observer { items ->
            if (items != null && items.size > 0) {
                adapter = ContinentListAdapter(mainViewModel.listContinents.value!!, context!!)
                listCountryRecyclerView.adapter = adapter
                adapter.notifyDataSetChanged()
            }
        })

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        fabAddPart = binding!!.fabAddPart
//        fabAddPart!!.setOnClickListener { createApplicViewModel.setFragmentLaunch("add_part") }//вызов экрана добавления машины сделал почему то с экрана добавления фото по фабу пустьпобудет там
    }
}
