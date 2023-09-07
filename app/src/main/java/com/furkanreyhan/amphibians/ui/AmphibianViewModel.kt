package com.furkanreyhan.amphibians.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.furkanreyhan.amphibians.remote.RemoteService
import com.furkanreyhan.amphibians.remote.model.AmphibiansItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AmphibianViewModel : ViewModel() {

    //amphibianları tutacağımız bir LiveData oluşturduk.
    private val _amphibiansList = MutableLiveData<List<AmphibiansItem>>()
    val amphibiansList: LiveData<List<AmphibiansItem>> = _amphibiansList

    //RemoteService oluşturduğumuz object. Object oldugu için nesneiz direkt içindeki amphibiansApi
    //değişkenine erişim sağladık. amphibiansApi değişkeni ile de AmphibiansApi içerisinde yer alan
    //sadece @GET ile tanımladığımız endpoint jsondan verileri çektik. (call istek. response cevap.)
    //onFailure ve onResponce metotları parantez içindeki object : ' in abstract metotları. Onları
    //override ettik. enqueueu sıralanmış olarak bize veriyor jsondaki verileri.
    //onResponce içerisinde de oluşturduğumuz amphibiansList değişkenine response sonucu gelen
    //verileri atadık. :? yapmamız yine elvis. response.body() boşsa emptyListe ata şeklinde.
    //.postValue'de mutableLiveData'ya veri atama işlemi.

    fun getAmphibians() {
        RemoteService.amphibiansApi.getAmphibians().enqueue(object : Callback<List<AmphibiansItem>> {
            override fun onFailure(call: Call<List<AmphibiansItem>>, t: Throwable) {
                Log.d("!!ERROR!!", t.message!!)
            }
            //postValue-value farkı
            override fun onResponse(call: Call<List<AmphibiansItem>>, response: Response<List<AmphibiansItem>>) {

                if (response.isSuccessful && response.body() != null){
                    _amphibiansList.value = response.body()
                }
            }
        })
    }

}