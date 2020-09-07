package br.com.rrdev.bethehero.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.rrdev.bethehero.R
import br.com.rrdev.bethehero.models.Incident
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private var incident: Incident? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = "Casos disponíveis"
        }

        incident = intent.extras?.getParcelable("incident")

        incident?.run {
            text_title.text = title
            val money = "Valor: ${getValueFormat()}"
            text_valor.text = money
            text_description.text = description
            btn_contato.setOnClickListener { startWhatApp(whatsapp) }
            Picasso
                .get()
                .load(img_url)
                .placeholder(R.drawable.ic_logo_red)
                .error(R.drawable.ic_logo_red)
                .into(img_incident)
        }
    }

    private fun startWhatApp(phone: String){
        val url = "https://api.whatsapp.com/send?phone=$phone"
        val whatsAppIntent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(url)
        }
        startActivity(whatsAppIntent)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    companion object{
        fun newActivityInstance(context: Context, incident: Incident){
            val intent = Intent(context, DetailActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                putExtra("incident", incident)
            }
            context.startActivity(intent)
        }
    }
}
