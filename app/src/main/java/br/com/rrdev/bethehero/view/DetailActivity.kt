package br.com.rrdev.bethehero.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import br.com.rrdev.bethehero.R
import br.com.rrdev.bethehero.models.Incident
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private var incident: Incident? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        incident = intent.extras?.getParcelable<Incident>("incident")

        incident?.run {
            text_ong.text = name
            text_value.text = getValueFormat()
            text_description.text = title
            text_description_complete.text = description
        }
        Log.d("content", "onDetail: "+incident.toString())

        btn_back.setOnClickListener { finish() }

        btn_whatsapp.setOnClickListener {
            val phone= incident?.whatsapp ?: ""
            val url = "https://api.whatsapp.com/send?phone=$phone"
            val whatsAppIntent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(url)
            }
            startActivity(whatsAppIntent)
        }

        btn_email.setOnClickListener {
            val intentEmail = Intent(Intent.ACTION_SENDTO).apply {
                putExtra(Intent.EXTRA_EMAIL, incident?.email ?: "")
                putExtra(Intent.EXTRA_SUBJECT, "Subject")
                putExtra(Intent.EXTRA_TEXT, "email body")
                type = "message/rfc822"
            }
            startActivity(intentEmail)
        }
    }
}
