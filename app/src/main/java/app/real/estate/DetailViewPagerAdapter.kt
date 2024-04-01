package app.real.estate

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

/**
 * @author : Lee Jae Young
 * @since : 2023-06-013 오후 2:35
 **/
class DetailViewPagerAdapter(
    private val context: Activity,
    list: ArrayList<String>,
    private val viewPager2: ViewPager2
) :
    RecyclerView.Adapter<DetailViewPagerAdapter.ViewHolder>() {
    private val mList = list

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val view: View = inflater.inflate(R.layout.vp_item_detail, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = mList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textView: TextView = view.findViewById(R.id.vpItemText)

        fun bind(dao: String) {
            val showMoreSpan = SpannableStringBuilder(dao)
            val spanText = "Show more"
            if (dao.contains(spanText)) {
                val spanStartIndex = dao.indexOf(spanText)
                val spanEndIndex = spanStartIndex + spanText.length
                showMoreSpan.setSpan(
                    ForegroundColorSpan(Color.WHITE),
                    spanStartIndex,
                    spanEndIndex,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                showMoreSpan.setSpan(
                    UnderlineSpan(),
                    spanStartIndex,
                    spanEndIndex,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )

                textView.text = showMoreSpan
            } else {
                textView.text = dao
            }

            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    try {

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }
}