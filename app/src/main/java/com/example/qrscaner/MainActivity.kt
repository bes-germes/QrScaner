package com.example.qrscaner

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator
import java.time.Instant


class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button: Button = findViewById(R.id.btn_scan)
        textView = findViewById(R.id.text)
        button.setOnClickListener{
            val scanner = IntentIntegrator(this)
            scanner.initiateScan()
//            val intent = Intent(this,MainActivity2::class.java)
//            intent.putExtra("text", textView.text.toString())
//            startActivity(intent)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (result != null) {
                if (result.contents == null) {
                    Toast.makeText(this, "Something wrong", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Scanned" + result.contents, Toast.LENGTH_LONG).show()
                    val intent = Intent(this,MainActivity2::class.java)
                    intent.putExtra("text", result.contents.toString())
                    startActivity(intent)
                }
            }
        }
    }
}