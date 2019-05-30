package ru.orehovai.testiceborn

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.widget.Toast
import kotlinx.coroutines.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.orehovai.testiceborn.model.Continent
import ru.orehovai.testiceborn.model.Country
import ru.orehovai.testiceborn.utils.Retrofit
import kotlin.coroutines.CoroutineContext

class MainViewModel: ViewModel() {

    val continents = mutableListOf("AF", "AS", "EU", "NA", "SA", "OC")

    val listContinents = MutableLiveData<MutableList<MutableList<Country>>>()

    /*For observe to launch fragment*/
    val fragmentLaunch = MutableLiveData<String>()


    fun setListContinents(list: MutableList<MutableList<Country>>) {
        listContinents.value = list
    }

    fun setFragmentLaunch(setLaunch: String) {
        fragmentLaunch.value = setLaunch
    }

//    fun getListContinents() {
//        for(i in continents) {
//            Retrofit.api?.getContinent(i)?.enqueue(object : Callback<ResponseBody> {
//                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                }
//
//                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
//                    listContinents.add()
//                }
//
//            })
//        }
//
//    }

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

    private val repository : MovieRepository = MovieRepository(Retrofit.api!!)



    fun fetchContinent(){
        scope.launch {
            val tempListContinents = mutableListOf<MutableList<Country>>()
            for (i in continents.indices) {
                val listCountry = repository.getCountry(continents[i])
                tempListContinents.add(i, listCountry!!)
            }
            listContinents.postValue(tempListContinents)
        }
    }


    fun cancelAllRequests() = coroutineContext.cancel()

}
//функция для оповещения наблюдателей после добавления элеента в спискоо(обычно нужно список перезаписать)
fun <T> MutableLiveData<ArrayList<T>>.add(item: T) {
    val updatedItems = this.value as ArrayList
    updatedItems.add(item)
    this.value = updatedItems
}

//аналогичная для очистки
fun <T> MutableLiveData<ArrayList<T>>.clear() {
    val updatedItems = this.value as ArrayList
    updatedItems.clear()
    this.value = updatedItems
}
