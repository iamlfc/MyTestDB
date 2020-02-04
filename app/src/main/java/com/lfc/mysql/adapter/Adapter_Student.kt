package com.lfc.mysql.adapter

import android.content.Context
import com.lfc.mysql.DataBeans.Student
import com.lfc.mysql.R
import com.lfc.mysql.callback.OnItemDelCallback
import com.lfc.mysql.callback.OnItemEditCallback
import com.zhy.adapter.recyclerview.CommonAdapter
import com.zhy.adapter.recyclerview.base.ViewHolder
import java.util.*

/**
 * Created by LFC
 * on 2019/8/13.
 *    个人资料评价
 */
class Adapter_Student(val baseContext: Context, layoutId: Int, datas: List<Student>) :
    CommonAdapter<Student?>(baseContext, layoutId, datas) {
    private var list_data: List<Student> = ArrayList() //

    var onDelCallback: OnItemDelCallback? = null
    var onEditCallback: OnItemEditCallback? = null
    override fun convert(holder: ViewHolder, commonDataM: Student?, position: Int) {
        holder.setText(R.id.tv_name_items, commonDataM?.stuName)
        holder.setText(R.id.tv_perNo_items, commonDataM?.stuNo)
        holder.setText(R.id.tv_schollID_items, commonDataM?.strUId.toString())
        var strAds = commonDataM?.stuads
        if (strAds.isNullOrBlank()) strAds = "default"
        holder.setText(R.id.tv_sex_items, commonDataM?.stuSex + "|" + strAds)
        holder.setText(R.id.tv_score_items, commonDataM?.stuScore.toString())
        holder.itemView.setOnClickListener {
            if (commonDataM != null) {
                onEditCallback?.OnItemEditListener(position, commonDataM)
            }
        }
        holder.itemView.setOnLongClickListener {
            if (commonDataM != null) {
                onDelCallback?.OnItemDelListener(position, commonDataM)
            }
            true
        }
    }

    fun RefreshAll(list_data: List<Student>) {
        this.list_data = list_data
        notifyDataSetChanged()
    }

    init {
        list_data = datas
    }
}