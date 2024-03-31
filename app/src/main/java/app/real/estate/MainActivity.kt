package app.real.estate

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import app.real.estate.databinding.ActivityMainBinding
import eightbitlab.com.blurview.RenderScriptBlur


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val listItem = ArrayList<MainRvAdapter.MainItem>()
    private val listAdapter by lazy { MainRvAdapter(this, listItem) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        window.statusBarColor = Color.TRANSPARENT

        UtilClass.fullScreen(this)


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

            mainMenuHome.setOnClickListener {  }
            mainMenuGps.setOnClickListener {  }
            mainMenuSearch.setOnClickListener {  }
            mainMenuChat.setOnClickListener {  }
            mainMenuPerson.setOnClickListener {  }
        }

        listItem.clear()
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
}