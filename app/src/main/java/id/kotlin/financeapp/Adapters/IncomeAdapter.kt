package id.kotlin.financeapp.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.kotlin.financeapp.Model.getData.Income.DataIncome
import id.kotlin.financeapp.R
import kotlinx.android.synthetic.main.list_income.view.*

class IncomeAdapter(val data: List<DataIncome>?, val itemClick: OnClickListener) :
    RecyclerView.Adapter<IncomeAdapter.ViewHolder>() {
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val incomeName = view.textIncomeName
        val incomeSum = view.textIncomeSum
        val incomeDate = view.textIncomeDate
        val incomeImage = view.textIncomeImage
        val deleteBtn = view.btnHapus
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


        holder.view.setOnClickListener{
            itemClick.detail(item)
        }

        holder.deleteBtn.setOnClickListener{
            itemClick.hapusData(item)
        }
    }

    override fun getItemCount(): Int = data?.size ?: 0

    interface OnClickListener {
        fun detail(income: DataIncome?)

        fun hapusData(income: DataIncome?)
    }
}