package app.real.estate

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.util.StateSet
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import app.real.estate.databinding.ActivityLoginBinding
import at.favre.lib.dali.Dali
import at.favre.lib.dali.builder.BuilderDefaults

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        @Suppress("DEPRECATION")
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                )

        binding.apply {
            Dali.create(this@LoginActivity).load(R.drawable.login_bg).skipCache().brightness(-15f)
                .concurrent().reScale().blurRadius(5).into(loginBgIv)

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
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
                overridePendingTransition(1,1)
            }

            loginGoogle.setOnClickListener {  }
            loginFacebook.setOnClickListener {  }
            loginApple.setOnClickListener {  }
            loginLanguageContainer.setOnClickListener { }
        }
    }
}