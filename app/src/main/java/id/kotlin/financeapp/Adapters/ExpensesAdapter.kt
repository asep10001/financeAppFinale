package id.kotlin.financeapp.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.kotlin.financeapp.Model.getData.Expenses.DataExpenses
import id.kotlin.financeapp.R
import kotlinx.android.synthetic.main.list_income.view.*

class ExpensesAdapter(val data: List<DataExpenses>?, val itemClick: OnClickListener) : RecyclerView.Adapter<ExpensesAdapter.ViewHolder>(){
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        val expensesName = view.textIncomeName
        val expensesSum = view.textIncomeSum
        val expensesDate = view.textIncomeDate
        val expensesImage = view.textIncomeImage
        val deleteBtn = view.btnHapus
    }

    interface OnClickListener {
        fun detail(expenses: DataExpenses?)

        fun hapusData(expenses: DataExpenses?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_income, parent, false)

        return ViewHolder(view);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data?.get(position)

        holder.expensesName.text = item?.name
        holder.expensesSum.text = item?.ammount.toString()
        holder.expensesDate.text = item?.transactionDate
        Glide.with(holder.view.context).load(item?.image).into(holder.expensesImage)


        holder.view.setOnClickListener{
            itemClick.detail(item)
        }

        holder.deleteBtn.setOnClickListener{
            itemClick.hapusData(item)
        }
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }
}