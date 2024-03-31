package app.real.estate

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import android.os.Build
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.util.StateSet
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
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

        // API 레벨 30부터 사용 가능합니다.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
            val controller = window.insetsController

            // 상태 표시줄 및 시스템 바를 숨기는 옵션
            controller?.let {
                it.hide(
                        WindowInsets.Type.navigationBars())
                it.systemBarsBehavior =
                    WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            // API 레벨 30 미만의 경우 이전 방식을 사용
            window.decorView.apply {
                systemUiVisibility = (
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                        View.SYSTEM_UI_FLAG_IMMERSIVE)
            }
        }

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