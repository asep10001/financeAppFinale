package id.kotlin.financeapp.Adapters.DetailCategoryAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.kotlin.financeapp.Adapters.IncomeAdapter
import id.kotlin.financeapp.Model.getData.Income.DataIncome
import id.kotlin.financeapp.R
import kotlinx.android.synthetic.main.list_income.view.*

class IncomeDetailAdapter(val data: List<DataIncome>?, val itemClick: IncomeAdapter.OnClickListener) :
    RecyclerView.Adapter<IncomeDetailAdapter.ViewHolder>() {
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val incomeName = view.textIncomeName
        val incomeSum = view.textIncomeSum
        val incomeDate = view.textIncomeDate
        val incomeImage = view.textIncomeImage
        val btnHapusIncome = view.btnHapus
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_income, parent, false)

        return ViewHolder(view);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data?.get(position)

        holder.incomeName.text = item?.name
        holder.incomeSum.text = item?.ammount.toString()
        holder.incomeDate.text = item?.transactionDate
        Glide.with(holder.view.context).load(item?.image).into(holder.incomeImage)


        holder.view.setOnClickListener {
            itemClick.detail(item)
        }

        holder.btnHapusIncome.setOnClickListener {
            itemClick.hapusData(item)
        }
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

}