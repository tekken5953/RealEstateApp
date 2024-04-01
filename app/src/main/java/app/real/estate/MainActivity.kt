package app.real.estate

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.ViewPropertyAnimatorListenerAdapter
import androidx.databinding.DataBindingUtil
import app.real.estate.databinding.ActivityMainBinding
import eightbitlab.com.blurview.RenderScriptBlur
import kotlin.math.absoluteValue


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val listItem = ArrayList<MainRvAdapter.MainItem>()
    private val listAdapter by lazy { MainRvAdapter(this, listItem) }
    private var selectedPosition = 0
    private val menuItems by lazy {listOf(binding.mainMenuHome,binding.mainMenuGps,binding.mainMenuSearch,binding.mainMenuChat,binding.mainMenuPerson)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        UtilObject.hideNavBarOnly(this)

        binding.apply {
            mainRv.adapter = listAdapter
            mainTabLayout.bringToFront()

            mainMenuLinear.bringToFront()
            val decorView: View = window.decorView
            val rootView = decorView.findViewById<View>(android.R.id.content) as ViewGroup
            mainMenuBlur.outlineProvider = ViewOutlineProvider.BACKGROUND
            mainMenuBlur.clipToOutline = true
            mainMenuBlur.setBlurEnabled(true)
            mainMenuBlur.setupWith(rootView, RenderScriptBlur(this@MainActivity))
                .setBlurRadius(12F)

            mainMenuHome.setOnClickListener { animateBgTrans(selectedPosition, 0) }
            mainMenuGps.setOnClickListener { animateBgTrans(selectedPosition, 1) }
            mainMenuSearch.setOnClickListener { animateBgTrans(selectedPosition, 2) }
            mainMenuChat.setOnClickListener { animateBgTrans(selectedPosition, 3) }
            mainMenuPerson.setOnClickListener { animateBgTrans(selectedPosition, 4) }
        }

        addRvItem("Single Family", "Hayfiled Ashton Place", "931B 76th Ave E, Tulsa, Ok 76423", R.drawable.house1,420F)
        addRvItem("Fourth Family", "Graslin Place Cambronne", "1098 N Venetine Dr.Mianmi, FL 33139", R.drawable.house2, 568F)
        addRvItem("Double Family", "Ubu Villa Flamboyan", "Jogjakarta Indonesia", R.drawable.house3, 250F)
        addRvItem("Single Family", "Lakote House", "Williamsburg, Brooklyn, NY 11211", R.drawable.house4, 430F)
        addRvItem("Fourth Family", "Colonial Brooklyn", "456 Elm Avenue, Brooklyn, NY 11201", R.drawable.house5, 763F)
    }

    private fun addRvItem(sort: String, title: String,address: String, img: Int, price: Float) {
        val item = MainRvAdapter.MainItem(sort,img,title,address,price)
        listItem.add(item)
        listAdapter.notifyItemInserted(listItem.lastIndex)
    }

    private fun animateBgTrans(fromPosition: Int, toPosition: Int) {
        val fromView: View = menuItems[fromPosition]
        val toView: View = menuItems[toPosition]
        val fromX = fromView.x
        val toX= toView.x

        if (fromPosition != toPosition) {
            ViewCompat.animate(binding.mainMenuSelected)
                .translationXBy(toX - fromX)
                .setInterpolator(AccelerateDecelerateInterpolator())
                .setDuration(80 * (fromPosition - toPosition).absoluteValue.toLong())
                .setListener(object : ViewPropertyAnimatorListenerAdapter() {
                    override fun onAnimationStart(view: View) { super.onAnimationStart(view) }
                    override fun onAnimationEnd(view: View) {
                        selectedPosition = toPosition
                    }
                }).start()
        }
    }
}