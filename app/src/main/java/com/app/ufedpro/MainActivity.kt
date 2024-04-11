package com.app.ufedpro

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.ufedpro.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val startIntentData = intent.data
        if (startIntentData != null) {
            val intentUrl = startIntentData.toString()
            if (intentUrl.contains("http://") || intentUrl.contains("https://")) {  // checking if the intent's data was meant to be a url
                showLinkCheckingDialogue(intentUrl)

            }
        } else {
            val intent = Intent(this@MainActivity, DetailsActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun showLinkCheckingDialogue(url: String) {

//        val linkCheckingDialog = LinkCheckingDialog(this, url)
//        linkCheckingDialog.show()
        val dialogFragment = CustomDialogFragment(url, this@MainActivity)
        dialogFragment.show(supportFragmentManager, "custom_dialog")
    }


}