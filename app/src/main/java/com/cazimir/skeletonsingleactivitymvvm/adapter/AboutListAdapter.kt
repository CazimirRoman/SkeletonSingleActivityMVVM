package com.cazimir.skeletonsingleactivitymvvm.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.cazimir.skeletonsingleactivitymvvm.R
import com.cazimir.skeletonsingleactivitymvvm.adapter.AboutListAdapter.RowHolder
import com.cazimir.skeletonsingleactivitymvvm.model.AboutItem
import com.cazimir.skeletonsingleactivitymvvm.model.AboutItemType

class AboutListAdapter(
    private val context: Context,
    private val data: ArrayList<AboutItem>,
    private val interactor: Interactor
) : RecyclerView.Adapter<RowHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_about, parent, false)
        return RowHolder(view)
    }

    override fun onBindViewHolder(rowholder: RowHolder, position: Int) {
        val item = data[position]

        // example
        rowholder.name.text = item.name.textToDisplay
        rowholder.icon.setImageResource(item.icon)
        rowholder.rootLayout.setOnClickListener { view: View? ->
            interactor.onItemClick(
                item
            )
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun removeRemoveAds() {
        var itemToBeDeleted: AboutItem? = null
        for (aboutItem in data) {
            if (aboutItem.name == AboutItemType.SendFeedback(context.getString(R.string.send_feedback))) {
                itemToBeDeleted = aboutItem
                break
            }
        }
        val index = data.indexOf(itemToBeDeleted)
        data.removeAt(index)
        notifyItemRemoved(index)
    }

    interface Interactor {
        fun onItemClick(item: AboutItem)
    }

    /**
     * RowHolder holds reference to all views int he layout
     */
    class RowHolder(view: View) : RecyclerView.ViewHolder(view) {
        val rootLayout: LinearLayout
        val name: AppCompatTextView
        val icon: ImageView

        init {
            // example
            name = view.findViewById(R.id.about_item_name)
            icon = view.findViewById(R.id.about_item_logo)
            rootLayout = view.findViewById(R.id.rootLayout)

            // rest of the views
        }
    }

    companion object {
        private val TAG = AboutListAdapter::class.java.simpleName
    }

}