package com.example.kotlin_recyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin_recyclerview.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),RecyclerAdapter.OnSubTotalListener{

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RecyclerAdapter
    var movieList: ArrayList<Movie> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        movieList = arrayListOf<Movie>(
            Movie("Shingles IKO / Bundle 3 Per Sq..", 7,33.50,0),
            Movie("Shingles Certainteed / Bundle 3 Per Sq..", 6,36.00,0),
            Movie("Coil Nails / 12 Sq..", 7,40.00,0),
            Movie("Ice & Water Shield (Cheap) / 2 Sq..", 8,85.00,0),
            Movie("Ice & Water Shield (Grace) / 1.75 Sq..", 9,187.0,0),
            Movie("Lead Chimney (50 Lbs)", 8,125.0,0),
            Movie("Plywood Per Sheet 1/2 Inch", 8,40.00,0),
            Movie("Synthetic Underlayment (Cheap) 4 Sq..", 10,85.00,0),
            Movie("Synthetic Underlayment (Best) 5 Sq..", 8,145.0,0),
            Movie("Shingles BP / Bundle 3 Per Sq..", 10,27.00,0),
            Movie("Drip Edge / Per 10 Ft.", 7,16.00,0),
            Movie("Ridge Vent Per Linear Ft.", 6,20.00,0),
            Movie("Cap Shingles Per Bundle / 25 Ft.", 7,70.00,0),
        Movie("Starter Shingles Per Bundle / 30 Ft.", 8,70.00,0),
        Movie("Stinger Cap Nails / 5 Sq..", 9,55.00,0),
        Movie("Labor Per Sq..", 8,120.0,0),
        Movie("Braun Vent", 8,55.00,0),
        Movie("Fascia / Ft. ", 10,15.00,0),
        Movie("Pipe Boots", 8,25.00,0),
        Movie("Dumpster Small", 10,650.0,0),
                Movie("Dumpster Large 20+ Sq.", 10,950.0,0)
        )


        adapter = RecyclerAdapter(movieList,this)
        recyclerView.adapter= adapter
    }


    override fun onSubTotalUpdate(grandTotal: Double) {
        binding.grandTotal.setText("$ ${grandTotal.toString()}")
    }

}
