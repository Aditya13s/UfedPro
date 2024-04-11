package com.app.ufedpro

// CustomDialogFragment.kt
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.app.ufedpro.databinding.DialogLayoutLinkCheckingBinding

class CustomDialogFragment(private val url: String, val context: MainActivity) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val binding = DialogLayoutLinkCheckingBinding.inflate(requireActivity().layoutInflater)

        // Set dialog window background to translucent black
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
        dialog?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        binding.root.background =
            ContextCompat.getDrawable(requireContext(), android.R.color.transparent)

        Handler().postDelayed({
            binding.checkingStatus.visibility = View.VISIBLE
            Handler().postDelayed({
                openBrowser(url)
            }, 800)
        }, 1000)

        return Dialog(requireContext()).apply {
            setContentView(binding.root)
            setCancelable(false) // Prevent dismissing dialog when clicking outside
        }
    }

    private fun openBrowser(urlString: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(urlString))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.setPackage("com.android.chrome")
        try {
            context.startActivity(intent)
        } catch (ex: ActivityNotFoundException) {
            // Chrome browser presumably not installed so allow user to choose instead
            intent.setPackage(null)
            context.startActivity(intent)
        }
        dialog?.dismiss()
    }
}
