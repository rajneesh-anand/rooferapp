package com.example.kotlin_recyclerview



import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_recyclerview.R.*
import com.example.kotlin_recyclerview.R.string.*
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.recycler_row.view.*


class RecyclerAdapter(private var movieList: ArrayList<Movie>, private val onSubTotalListener: OnSubTotalListener) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private var itemList = arrayListOf<Double>(0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(layout.recycler_row, parent, false)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {

        holder.itemView.rate.setText(movieList.get(position).price.toString())
        holder.itemView.quantity.setText(movieList.get(position).qty.toString())
        holder.itemView.item.setText(movieList.get(position).name)

        holder.itemView.quantity.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val result =calculateTotal()
              onSubTotalListener.onSubTotalUpdate(String.format("%.2f", result).toDouble())

            }
        })

        holder.itemView.rate.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val result =calculateTotal()
                onSubTotalListener.onSubTotalUpdate(String.format("%.2f", result).toDouble())

            }
        })

    }
    fun calculateTotal():Double{
        var total = 0.00
        for(item in itemList){
            total +=item
        }
        return total
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        init {

            val editRate : TextInputEditText = itemView.findViewById(id.rate)
            val editQnty : TextInputEditText = itemView.findViewById(id.quantity)

            editRate.addTextChangedListener(object : TextWatcher {

                override fun afterTextChanged(s: Editable) {}

                override fun beforeTextChanged(s: CharSequence, start: Int,
                                               count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    movieList.get(adapterPosition).price = if(editRate.text.toString() =="") 0.0 else editRate.text.toString().toDouble()
//                    itemView.total.setText("$ ${String.format("%.2f", movieList.get(adapterPosition).getTotal())}")
                    itemView.total.text = itemView.context.getString(total, movieList.get(adapterPosition).getTotal())
                    movieList.get(adapterPosition).getTotal()?.let {
                        itemList.set(adapterPosition,
                            it
                        )
                    }
                }
            })

            editQnty.addTextChangedListener(object : TextWatcher {

                override fun afterTextChanged(s: Editable) {}

                override fun beforeTextChanged(s: CharSequence, start: Int,
                                               count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    movieList.get(adapterPosition).qty = if(editQnty.text.toString() =="") 0 else editQnty.text.toString().toInt()
//                    itemView.total.text = "$ ${String.format("%.2f", movieList.get(adapterPosition).getTotal())}"
                    itemView.total.text = itemView.context.getString(total,movieList.get(adapterPosition).getTotal())
                    movieList.get(adapterPosition).getTotal()?.let {
                        itemList.set(adapterPosition,
                            it
                        )
                    }


                 }
            })


            itemView.setOnClickListener{
                Log.d("RecyclerView", "CLICK!")
            }

        }

    }


    interface OnSubTotalListener {
            fun onSubTotalUpdate(grandTotal :Double)
    }

}