package br.com.rrdev.bethehero.recyclers

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.com.rrdev.bethehero.R
import br.com.rrdev.bethehero.models.Incident
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.incident_adapter_item.view.*

class IncidentViewHolder(private val view: View, onClick: ((position:Int) -> Unit) = {}): RecyclerView.ViewHolder(view)  {

    init {
        view.parent_view.setOnClickListener{
            onClick(adapterPosition)
        }
    }

    fun setContent(incident: Incident){
        view.run{
            Picasso
                .get()
                .load(incident.img_url)
                .placeholder(R.drawable.ic_logo_red)
                .error(R.drawable.ic_logo_red)
                .into(img_incident)
            text_title.text = incident.title
            text_city.text = incident.city
            text_ong_name.text = incident.name
        }
    }

}