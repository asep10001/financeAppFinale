package id.kotlin.financeapp.Adapters.MainActivityAdapaters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.kotlin.financeapp.Adapters.GlideApp
import id.kotlin.financeapp.BottomSheetFragment
import id.kotlin.financeapp.Model.getData.Category.DataItem
import id.kotlin.financeapp.R
import kotlinx.android.synthetic.main.list_category.view.*
import kotlinx.android.synthetic.main.list_income.view.*

class CategoryAdapter(val data: List<DataItem>?, val itemClick: OnClickListener) :

    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val categoryName = view.textCategoryName
        val btnHapusCategory = view.btnHapusCategory
        val btnEditCategory = view.btnEditCategory
        val incomeImage = view.textCategoryImage
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_category, parent, false)
        return ViewHolder(view);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data?.get(position)

        holder.categoryName.text = item?.name
        GlideApp.with(holder.view.context).load(item?.image).into(holder.incomeImage)

        holder.view.setOnClickListener {
            itemClick.detail(item)
        }

        holder.btnHapusCategory.setOnClickListener {
            itemClick.hapusData(item)
        }

        holder.btnEditCategory.setOnClickListener{
            itemClick.updateData(item)
        }

    }

    override fun getItemCount(): Int = data?.size ?: 0

    interface OnClickListener {
        fun detail(category: DataItem?)

        fun hapusData(category: DataItem?)

        fun updateData(category: DataItem?)

    }


}