package ru.orehovai.easycar.ui.fragments.applic

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import ru.orehovai.testiceborn.MainViewModel
import ru.orehovai.testiceborn.R
import ru.orehovai.testiceborn.databinding.CountryListItemBinding
import ru.orehovai.testiceborn.model.Country

class CountryListAdapter(data: MutableList<Country>, private val context: Context) : RecyclerView.Adapter<CountryListAdapter.CountryHolder>(){

    private val items = mutableListOf<Country>()

    private val mainViewModel: MainViewModel by lazy {
        ViewModelProviders.of(context as FragmentActivity).get(MainViewModel::class.java)
    }

    init {
        items.clear()
        items.addAll(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<CountryListItemBinding>(inflater, R.layout.country_list_item, parent, false)
        return CountryHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {

        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }


    inner class CountryHolder(var binding: CountryListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Country) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

}