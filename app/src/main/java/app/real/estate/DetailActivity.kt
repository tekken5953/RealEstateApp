package app.real.estate

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import app.real.estate.UtilObject.hideNavBarOnly
import app.real.estate.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    private var isLike = false
    private val vpList = ArrayList<String>()
    private val vpAdapter by lazy { DetailViewPagerAdapter(this@DetailActivity, vpList, binding.detailVp) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        hideNavBarOnly(this)

        binding.apply {
            val imgResExtra = intent.extras?.getInt("img")
            imgResExtra?.let { Glide.with(this@DetailActivity).load(it).into(detailImg) }

            detailVp.adapter = vpAdapter
            TabLayoutMediator(detailTabLayout, detailVp) { tab, position ->
                tab.text = when(position) {
                    0 -> {"Property details"}
                    1 -> {"detailTabItemHistory"}
                    else -> ""
                }
            }.attach()

            vpList.add("New-Construction - Just Completed and Staged in a highly sought-after area. Venetian islands. This home has over 6, 400 Total SF with 5BR/5.5BA designed by Golwen Sanchez - Praxis Architecture Show more")
            vpList.add("test title test title test title test title test title test title test title test title test title test title test title test title test title test title Show more")
            vpAdapter.notifyDataSetChanged()

            val titleExtra = intent.extras?.getString("title")
            val priceExtra  = intent.extras?.getFloat("price") ?: 0F
            val addressExtra  = intent.extras?.getString("address")

            detailTitle.text = titleExtra
            detailPrice.text = "$${priceExtra.toInt()}K"
            detailAddress.text = addressExtra

            detailBack.setOnClickListener {
                vib()
                supportFinishAfterTransition()
            }
            detailShare.setOnClickListener {
                vib()
                val intent = Intent(Intent.ACTION_SEND_MULTIPLE)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_TEXT, "Share Real Estate")
                startActivity(Intent.createChooser(intent, "Share Real Estate"))
            }
            detailLike.setOnClickListener {
                vib()
                if (isLike) {
                    isLike = false
                    Glide.with(this@DetailActivity).load(R.drawable.like_empty).into(detailLike)
                    CoroutineScope(Dispatchers.IO).launch {
                        Snackbar.make(detailRoot, "Canceled the likes", Snackbar.LENGTH_SHORT).show()
                    }
                } else {
                    isLike = true
                    val anim = AnimationUtils.loadAnimation(this@DetailActivity, R.anim.like_fill_anim)
                    anim.setAnimationListener(object : Animation.AnimationListener {
                        override fun onAnimationStart(animation: Animation?) {
                            Glide.with(this@DetailActivity).load(R.drawable.like_fill).into(detailLike)
                        }
                        override fun onAnimationEnd(animation: Animation?) {
                            CoroutineScope(Dispatchers.IO).launch {
                                Snackbar.make(detailRoot, "Like this house", Snackbar.LENGTH_SHORT).show()
                            }
                        }
                        override fun onAnimationRepeat(animation: Animation?) {}
                    })

                    detailLike.startAnimation(anim)
                }
            }
        }
    }

    private fun vib() {
        VibrateUtil(this@DetailActivity).make(10)
    }
}