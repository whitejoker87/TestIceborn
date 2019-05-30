package ru.orehovai.testiceborn

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import ru.orehovai.easycar.ui.fragments.applic.CountryListAdapter
import ru.orehovai.testiceborn.databinding.ContinentListItemBinding
import ru.orehovai.testiceborn.model.Continent
import java.util.*

class
ContinentListAdapter(data: ArrayList<Continent>, private val context: Context) : RecyclerView.Adapter<ContinentListAdapter.ListPartsHolder>() {

    private val items = ArrayList<Continent>()

//    private val createApplicViewModel: CreateApplicViewModel by lazy {
//        ViewModelProviders.of(context as FragmentActivity).get(CreateApplicViewModel::class.java)
//    }

    init {
        items.clear()
        items.addAll(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPartsHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ContinentListItemBinding>(inflater, R.layout.continent_list_item, parent, false)
        return ListPartsHolder(binding)
    }

    override fun onBindViewHolder(holder: ListPartsHolder, position: Int) {

        holder.bind(items[position])

         //val adapter = CountryListAdapter(createApplicViewModel.getListParts().value!![position].listOfferItem as ArrayList<ListPartOfferItem>, context)

        val listPartsRecyclerView = holder.binding.rvListContry
        listPartsRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        //listPartsRecyclerView.adapter = adapter

    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ListPartsHolder(var binding: ContinentListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Continent) {
            //binding.item = item
            binding.executePendingBindings()
        }
    }
}