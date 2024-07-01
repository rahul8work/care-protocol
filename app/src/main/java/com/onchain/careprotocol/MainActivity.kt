package com.onchain.careprotocol

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        showProgressDialog(this)
    }

    private fun showProgressDialog(context: Context) {
        val dialogBuilder = MaterialAlertDialogBuilder(context)
        val dialog = dialogBuilder.show()

        // Dismiss the dialog after 2 seconds
        Handler(Looper.getMainLooper()).postDelayed({
            dialog.dismiss()
            startActivity(Intent(this, HomeActivity::class.java))
        }, 0)
    }
}