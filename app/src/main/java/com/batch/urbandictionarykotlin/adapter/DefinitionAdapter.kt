package com.batch.urbandictionarykotlin.adapter

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.batch.urbandictionarykotlin.R
import com.batch.urbandictionarykotlin.databinding.RowBinding
import com.batch.urbandictionarykotlin.dto.Definition
import java.io.IOException
import kotlin.collections.ArrayList

class DefinitionAdapter() : RecyclerView.Adapter<DefinitionAdapter.DefinitionHolder?>() {

    private var definitionList:List<Definition>
    private var isFavorite: Boolean
    private var context:Context?=null

    init {
        definitionList = ArrayList()
        isFavorite = false
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefinitionHolder {
        context = parent.context
        val rowBinding = RowBinding.inflate(LayoutInflater.from(context))
        return DefinitionHolder(rowBinding)
    }

    override fun onBindViewHolder(holder: DefinitionHolder, position: Int) {
        val definition = definitionList.get(position)
        holder.bind?.item = definition
        setupListeners(holder, definition)
    }
    private fun setupListeners(holder: DefinitionHolder,definition:Definition) {
        holder.bind?.relativeLayout?.setOnLongClickListener {
            val list= mutableListOf<Definition>()
            list.add(definition)
            val bundle = bundleOf("list" to  list)
            holder.itemView.findNavController().navigate(R.id.next_detail, bundle)
            false
        }

        holder.bind?.favorite?.setOnClickListener { v: View? ->
            isFavorite = if (!isFavorite) {
                holder.bind?.favorite?.setImageResource(R.drawable.ic_favorite)
                true
            } else {
                holder.bind?.favorite?.setImageResource(R.drawable.ic_favorite_border)
                false
            }
        }
        holder.bind?.share?.setOnClickListener { v: View? ->
            val sharingIntent = Intent(
                Intent.ACTION_SEND
            )
            sharingIntent.type = "text/html"
            val shareBody: String = definition.permalink!!
            sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
            context!!.startActivity(Intent.createChooser(sharingIntent, "Share via"))
        }
        holder.bind?.thumbsUpImage?.setOnClickListener { v: View? ->
            var vote = holder.bind?.thumbsUpVotes?.text.toString().toInt()
            vote++
            holder.bind?.thumbsUpVotes?.text = vote.toString()
        }
        holder.bind?.thumbsDownImage?.setOnClickListener { v: View? ->
            var vote = holder.bind?.thumbsDownVotes?.text.toString().toInt()
            vote++
            holder.bind?.thumbsDownVotes?.text = vote.toString()
        }


        holder.bind?.playSound?.setOnClickListener { v: View? ->
            if (definition.soundUrls?.size!! > 0) {
                val url: String = definition.soundUrls?.get(0)!!
                val mediaPlayer = MediaPlayer()
                try {
                    mediaPlayer.setDataSource(url)
                    mediaPlayer.prepare()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                mediaPlayer.start()
            } else {
                Toast.makeText(context, "NO SOUND FILE", Toast.LENGTH_SHORT).show()
            }

        }
    }
    override fun getItemCount(): Int {
        return definitionList.size
    }

    inner class DefinitionHolder(binding:RowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var bind:RowBinding?=null
        init {
            bind = binding
        }
    }

    fun addDefinitions(list: List<Definition>) {
        definitionList = list
        notifyDataSetChanged()
    }

}