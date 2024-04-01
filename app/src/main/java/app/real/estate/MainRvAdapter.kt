package app.real.estate

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.transition.Explode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import at.favre.lib.dali.Dali
import com.bumptech.glide.Glide

/**
 * @author : Lee Jae Young
 * @since : 2023-07-12 오전 11:12
 **/
class MainRvAdapter(private val context: Context, list: ArrayList<MainItem>) :
    RecyclerView.Adapter<MainRvAdapter.ViewHolder>() {

    data class MainItem(
        val sort: String,
        val img: Int,
        val title: String,
        val address: String,
        val price: Float
    )

    private val mList = list

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val view: View = inflater.inflate(R.layout.list_item_main_rv, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = mList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) { holder.bind(mList[position]) }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val img: ImageView = itemView.findViewById(R.id.listItemMainRvImg)
        private val title: TextView = itemView.findViewById(R.id.listItemMainRvTitle)
        private val sort: TextView = itemView.findViewById(R.id.listItemMainRvSort)
        private val address: TextView = itemView.findViewById(R.id.listItemMainRvAddress)

        fun bind(dao: MainItem) {
            Dali.create(context).load(dao.img).skipCache().brightness(-30f)
                .concurrent().reScale().blurRadius(3).into(img)

            title.text = dao.title
            sort.text = dao.sort
            address.text = dao.address

            itemView.setOnClickListener {
                val position = adapterPosition

                if (position != RecyclerView.NO_POSITION) {
                    try {
                        if (context is MainActivity) {
                            Glide.with(context)
                                .load(dao.img)
                                .preload()

                            val options = ActivityOptions.makeSceneTransitionAnimation(
                                context as Activity, img, "transDetailImg"
                            )

                            val transition = Explode()
                            transition.duration = 600
                            context.window.exitTransition = transition

                            val intent = Intent(context, DetailActivity::class.java)
                            intent.putExtra("img", dao.img)
                            intent.putExtra("title", dao.title)
                            intent.putExtra("address",dao.address)
                            intent.putExtra("sort", dao.sort)
                            intent.putExtra("price",dao.price)
                            context.startActivity(intent, options.toBundle())
                        }
                    } catch (e: UninitializedPropertyAccessException) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }
}