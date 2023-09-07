package com.furkanreyhan.amphibians

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.furkanreyhan.amphibians.ui.AmphibianAdapter
import androidx.lifecycle.ViewModelProvider
import com.furkanreyhan.amphibians.remote.model.AmphibiansItem
import com.furkanreyhan.amphibians.ui.AmphibianViewModel

class MainActivity : AppCompatActivity() {
    //ViewModelProvider sınıfını kullanarak AmphibianViewModel sınıfının bir örneğini oluşturur
    // ve viewModel özelliğine atar.
    private val viewModel by lazy { ViewModelProvider(this)[AmphibianViewModel::class.java] }
    private lateinit var recyclerView: RecyclerView
    private val amphibiansList: MutableList<AmphibiansItem> = mutableListOf()
    private val adapter by lazy { AmphibianAdapter(amphibiansList, this@MainActivity) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        observeViewModel()

        // Uzak servisi çağırın ve sonuçları ViewModel içindeki LiveData'ya yerleştirme
        viewModel.getAmphibians()
    }

    private fun initViews(){
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView.adapter = adapter
    }

    // ViewModel içindeki LiveData'yı observe edin ve sonuçları RecyclerView'e bağlama
    @SuppressLint("NotifyDataSetChanged")
    private fun observeViewModel(){
        viewModel.amphibiansList.observe(this, Observer { amphibiansList ->
            this.amphibiansList.addAll(amphibiansList)
            adapter.notifyDataSetChanged()

        })
    }
}
