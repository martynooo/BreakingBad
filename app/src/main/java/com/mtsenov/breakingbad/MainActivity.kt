package com.mtsenov.breakingbad

import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.mtsenov.breakingbad.databinding.ActivityMainBinding
import com.mtsenov.breakingbad.model.SeriesChar


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var charactersList: MutableList<SeriesChar> = ArrayList()
    private lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        val model: MyViewModel by viewModels()
        adapter = RecyclerAdapter(this, charactersList)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(this,2)

        model.getCharacters().observe(this, Observer<List<SeriesChar>>{ characters ->
            Log.e("breakingBad", "Updated data")
            adapter.updateData(characters.toMutableList())
        })

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter.filter(query!!)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter(newText!!)
                return true
            }
        })

        val view = binding.root
        setContentView(view)
    }
}