package id.kotlin.financeapp.Adapters.MainActivityAdapaters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.kotlin.financeapp.Adapters.GlideApp
import id.kotlin.financeapp.Model.getData.Expenses.DataExpenses
import id.kotlin.financeapp.R
import kotlinx.android.synthetic.main.list_income.view.*

class ExpensesAdapter(val data: List<DataExpenses>?, val itemClick: OnClickListener) : RecyclerView.Adapter<ExpensesAdapter.ViewHolder>(){
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        val expensesName = view.textIncomeName
        val expensesSum = view.textIncomeSum
        val expensesDate = view.textIncomeDate
        val incomeImage = view.textIncomeImage
        val deleteBtn = view.btnHapus
    }

    interface OnClickListener {
        fun detail(expenses: DataExpenses?)

        fun hapusData(expenses: DataExpenses?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_income_main, parent, false)

        return ViewHolder(view);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data?.get(position)

        holder.expensesName.text = item?.name
        holder.expensesSum.text = item?.ammount.toString()
        holder.expensesDate.text = item?.transactionDate
        GlideApp.with(holder.view.context).load(item?.image).into(holder.incomeImage)

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