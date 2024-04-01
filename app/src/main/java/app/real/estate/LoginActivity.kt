package app.real.estate

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import app.real.estate.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        UtilObject.fullScreen(this)

        binding.apply {
            val boldText = loginTitle.text.toString()
            val boldSpan = SpannableStringBuilder(boldText)
            boldSpan.setSpan(
                StyleSpan(Typeface.BOLD),
                boldText.indexOf("perfect"), boldText.indexOf("perfect") + "perfect".length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            boldSpan.setSpan(
                StyleSpan(Typeface.BOLD),
                boldText.indexOf("live"), boldText.indexOf("live") + "live".length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

            loginTitle.text = boldSpan

            loginLoginBtn.setOnClickListener {
                vib()
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
                overridePendingTransition(1,1)
            }

            loginGoogle.setOnClickListener { vib() }
            loginFacebook.setOnClickListener { vib() }
            loginApple.setOnClickListener { vib() }
            loginLanguageContainer.setOnClickListener { vib() }
        }
    }

    private fun vib() {
        VibrateUtil(this).make(10)
    }
}