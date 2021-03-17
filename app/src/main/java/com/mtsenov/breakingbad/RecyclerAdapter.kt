package com.mtsenov.breakingbad


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.mtsenov.breakingbad.model.SeriesChar
import java.util.*


class RecyclerAdapter(
    private val context: Context,
    private var data: MutableList<SeriesChar>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var originalData: List<SeriesChar> = listOf()

    inner class ItemViewHolder(@NonNull itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val card: CardView = itemView.findViewById(R.id.cardView)
        var image: ImageView = itemView.findViewById(R.id.characterImage)
        var name: TextView = itemView.findViewById(R.id.characterName)
    }

    @NonNull
    override fun onCreateViewHolder(
        @NonNull viewGroup: ViewGroup,
        i: Int
    ): RecyclerView.ViewHolder {
        val layoutView: View =
            LayoutInflater.from(viewGroup.context).inflate(
                R.layout.list_item, viewGroup, false
            )
        return ItemViewHolder(layoutView)
    }

    override fun onBindViewHolder(
        @NonNull viewHolder: RecyclerView.ViewHolder,
        i: Int
    ) {
        val itemViewHolder = viewHolder as ItemViewHolder
        val character: SeriesChar = data[i]

        val url: String? = character.img
        Glide.with(context)
            .load(url)
            .override(Target.SIZE_ORIGINAL, 250)
            .error(Glide.with(viewHolder.image).load(R.drawable.ic_launcher_background))
            .placeholder(R.drawable.ic_launcher_background)
            .into(viewHolder.image)

        itemViewHolder.name.text = character.name

        itemViewHolder.card.setOnClickListener {
            val activity = context as AppCompatActivity
            val intent = Intent(activity, DetailsActivity::class.java)
            intent.putExtra("character", character)
            context.startActivity(intent)
        }
    }


    override fun getItemCount(): Int {
        return data.size
    }

    fun updateData(characters: MutableList<SeriesChar>) {
        this.data = characters
        originalData = data.toMutableList()
        notifyDataSetChanged()
    }

    fun filter(text: String) {
        data.clear()
        if (text.isEmpty()) {
            data.addAll(originalData)
        } else {
            val query = text.toLowerCase(Locale.ROOT)
            for (item in originalData) {
                if (item.name?.toLowerCase(Locale.ROOT)?.contains(query)!! ||
                    item.appearance?.contains(query.toIntOrNull())!!
                ) {
                    data.add(item)
                }
            }
        }
        notifyDataSetChanged()
    }
}