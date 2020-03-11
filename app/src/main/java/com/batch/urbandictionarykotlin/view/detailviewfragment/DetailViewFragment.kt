package com.batch.urbandictionarykotlin.view.detailviewfragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.batch.urbandictionarykotlin.R
import com.batch.urbandictionarykotlin.databinding.RowBinding
import com.batch.urbandictionarykotlin.dto.Definition

/**
 * A simple [Fragment] subclass.
 */
class DetailViewFragment : Fragment() {
    lateinit var list:List<Definition>
    lateinit var bind: RowBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = DataBindingUtil.inflate(inflater, R.layout.row, container, false)
        @Suppress("UNCHECKED_CAST")
        list = arguments?.get("list") as List<Definition>

        bind.item = list.get(0)
        return bind.root
    }
}



