package com.ja.assets.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.SpinnerAdapter
import android.widget.TextView
import com.ja.assets.R


abstract class BaseSpinnerAdapter<T>(private val context: Context,private val spinnerInfoList: MutableList<T>) : BaseAdapter(), SpinnerAdapter {

    override fun getItem(position: Int): T {
        return spinnerInfoList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return spinnerInfoList.size
    }


    @SuppressLint("ViewHolder")
    override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {
        val layoutInflater = LayoutInflater.from(context)// //创建视图容器工厂并设置上下文
        val convertView: View = layoutInflater.inflate(R.layout.spinner_item, null)
        val spinnerName: TextView = convertView.findViewById(R.id.spinnerName)
        convert(spinnerName, spinnerInfoList[position])
        return convertView
    }

//    //创建区别于spinner的下拉试图
//    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
//        val layoutInflater = LayoutInflater.from(context)//
//        convertView = layoutInflater.inflate(R.layout.spinner_item, null)
//        if (convertView != null) {
//            val spinnerName: TextView = convertView.findViewById(R.id.spinnerName)
//            spinnerName.text = spinnerInfoList[position].dataName
//        }
//        return convertView
//    }

    protected abstract fun convert(convertView: TextView, item: T?)


}
