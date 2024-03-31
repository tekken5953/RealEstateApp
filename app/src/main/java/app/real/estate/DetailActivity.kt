package app.real.estate

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import androidx.databinding.DataBindingUtil
import app.real.estate.databinding.ActivityDetailBinding
import at.favre.lib.dali.Dali
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        binding.apply {

            val imgResExtra = intent.extras?.getInt("img")
            imgResExtra?.let {
                Glide.with(this@DetailActivity).load(it).into(detailImg)
            }

            val titleExtra = intent.extras?.getString("title")
            val priceExtra  = intent.extras?.getFloat("price") ?: 0F
            val addressExtra  = intent.extras?.getString("address")

            detailTitle.text = titleExtra
            detailPrice.text = "$${priceExtra.toInt()}K"
            detailAddress.text = addressExtra

            val showMoreSpan = SpannableStringBuilder(detailTabText.text.toString())
            val spanText = "Show more"
            val spanStartIndex = detailTabText.text.toString().indexOf(spanText)
            val spanEndIndex = spanStartIndex + spanText.length
            showMoreSpan.setSpan(ForegroundColorSpan(Color.WHITE), spanStartIndex, spanEndIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            showMoreSpan.setSpan(UnderlineSpan(), spanStartIndex, spanEndIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

            detailTabText.text = showMoreSpan
        }
    }
}