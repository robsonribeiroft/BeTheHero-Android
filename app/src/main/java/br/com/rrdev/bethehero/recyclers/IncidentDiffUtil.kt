package br.com.rrdev.bethehero.recyclers

import androidx.recyclerview.widget.DiffUtil
import br.com.rrdev.bethehero.models.Incident

class IncidentDiffUtil(private val oldList: List<Incident>,private val newList: List<Incident>) : DiffUtil.Callback(){
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

}