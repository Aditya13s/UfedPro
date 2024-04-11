package com.app.ufedpro

import android.app.AlertDialog
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.app.ufedpro.databinding.DialogLayoutLinkCheckingBinding

class LinkCheckingDialog(context: Context, private val url: String) : AlertDialog(context) {
    private lateinit var binding: DialogLayoutLinkCheckingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogLayoutLinkCheckingBinding.inflate(layoutInflater)


        setContentView(binding.root)


        setCanceledOnTouchOutside(false)
        checkTheLink()
    }

    private fun checkTheLink() {
//        Handler().postDelayed({openBrowser("")})
    }


}