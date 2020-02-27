package com.example.roomdbexample

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.roomdbexample.room.Word
import com.example.roomdbexample.room.WordRoomDB
import com.example.roomdbexample.viewmodel.WordViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class WordListAdapter internal constructor(context: Context, val clickListener: (Word) -> Unit) :
    RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {


    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var words = emptyList<Word>()


    inner class WordViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val worditemview: TextView = itemview.findViewById(R.id.textview)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        val itemview = inflater.inflate(R.layout.recyclerview_items, parent, false)
        return WordViewHolder(itemview)
    }

    override fun getItemCount(): Int = words.size


    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        holder.worditemview.text = words[position].name
        // (holder as WordViewHolder).bind(words[position],clickListener)
        holder.worditemview.setOnClickListener {
            clickListener(words[position])
        }
    }


    internal fun setWords(word: List<Word>) {
        this.words = word
        notifyDataSetChanged()
    }

}