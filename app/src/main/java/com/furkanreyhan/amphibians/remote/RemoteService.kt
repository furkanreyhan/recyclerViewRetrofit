package com.furkanreyhan.amphibians.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//objecti static gibi düşün. çağrıldığı yerde nesneye gerek duymadan direkt RemoteService.fonksiyon adı
//şeklinde çağrılabiliyor. Performans açısından avantaj
object RemoteService {

    private val BASE_URL = "https://developer.android.com/courses/pathways/android-basics-kotlin-unit-4-pathway-2/"

    //retrofit tipinde bir değişken oluşturduk
    private var retrofit: Retrofit? = null
    //retrofit döndüren bir fonksiyon
    fun getRetrofit(): Retrofit {
        //elvis operatörü. retrofit null değil ise direkt olarak döndürecek. eğer null ise(ki burada null)
        //aşağıdaki işlemleri yapacak. .apply ile de yeni retrofit değeri atanacak ve döndürülecek.
        return retrofit
            ?: Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .apply {
                    retrofit = this
                }
    }

    //api servisini oluşturmuş olduk. getRetrofit.create ile retrofit oluşturuldu.
    //ViewModel içerisinde bu amphibiansApi ile request atıyoruz.
    val amphibiansApi: AmphibiansApi = getRetrofit().create(AmphibiansApi::class.java)

//    val retrofit = Retrofit.Builder()
//        .baseUrl("https://developer.android.com/courses/pathways/android-basics-kotlin-unit-4-pathway-2/")
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//    val api = retrofit.create(AmphibiansApi::class.java)
}