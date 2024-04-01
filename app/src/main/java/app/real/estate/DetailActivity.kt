package app.real.estate

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import app.real.estate.UtilObject.hideNavBarOnly
import app.real.estate.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    private var isLike = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        hideNavBarOnly(this)

        binding.apply {
            val imgResExtra = intent.extras?.getInt("img")
            imgResExtra?.let {
                Glide.with(this@DetailActivity)
                    .load(it)
                    .into(detailImg)
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

            detailBack.setOnClickListener { supportFinishAfterTransition() }
            detailShare.setOnClickListener {
                val intent = Intent(Intent.ACTION_SEND_MULTIPLE)
                intent.type = "text/plain"
                intent.putExtra(
                    Intent.EXTRA_TEXT, "Share Real Estate")
                startActivity(Intent.createChooser(intent, "Share Real Estate"))
            }
            detailLike.setOnClickListener {
                if (isLike) {
                    isLike = false
                    Glide.with(this@DetailActivity).load(R.drawable.like_empty).into(detailLike)
                    CoroutineScope(Dispatchers.IO).launch {
                        Snackbar.make(detailRoot, "Canceled the likes", Snackbar.LENGTH_SHORT).show()
                    }
                } else {
                    isLike = true
                    Glide.with(this@DetailActivity).load(R.drawable.like_fill).into(detailLike)
                    CoroutineScope(Dispatchers.IO).launch {
                        Snackbar.make(detailRoot, "Like this house", Snackbar.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}