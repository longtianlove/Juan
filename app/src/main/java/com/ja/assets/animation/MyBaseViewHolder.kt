package com.fixed.u8.animation

import android.content.Context
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseViewHolder
import com.ja.assets.glide.GlideLoadPhoto

/**
 * Created by zry on 2017/12/8.
 */

class MyBaseViewHolder(view: View) : BaseViewHolder(view) {


    /**
     * 普通加载图片
     */
    fun setImgLoad(context: Context, viewId: Int, imgUrl: Any, defaultImg: Int, errorImg: Int): MyBaseViewHolder {
        val view = getView<ImageView>(viewId)
        Glide.with(context).load(imgUrl).apply(GlideLoadPhoto().GlideLoadImg(defaultImg, errorImg)).into(view)
        return this
    }

    /**
     * 设置网络获取的圆形图片
     */
    fun setImgCircle(context: Context, viewId: Int, imgUrl: Any, defaultImg: Int, errorImg: Int): MyBaseViewHolder {
        val view = getView<ImageView>(viewId)
        Glide.with(context).load(imgUrl).apply(GlideLoadPhoto().GlideCircle(defaultImg, errorImg)).into(view)
        return this

    }

    /**
     * 设置网络获取的圆角图片
     */
    fun setImgRound(context: Context, viewId: Int, imgUrl: Any, defaultImg: Int, errorImg: Int, roundDp: Int): MyBaseViewHolder {
        val view = getView<ImageView>(viewId)
        Glide.with(context).load(imgUrl).apply(GlideLoadPhoto().GlideRound(defaultImg, errorImg, roundDp)).into(view)
        return this
    }

    /**
     * 设置字体颜色
     */
    /**
     * 设置TextView的背景颜色
     */
    fun setTextColorContext(context: Context, viewId: Int, textColor: Int): MyBaseViewHolder {
        val view = getView<TextView>(viewId)
        view.setTextColor(ContextCompat.getColor(context, textColor))
        return this
    }

    /**
     * 设置RadioButton的选中状态
     */
    fun setRadioButtonCheck(viewId: Int, boolean: Boolean): MyBaseViewHolder {
        val view = getView<RadioButton>(viewId)
        view.isChecked = boolean
        return this
    }


    /**
     * 设置recyclerView中控件的高度
     */
    fun setLinearLayoutHeight(viewId: Int, heightInt: Int): MyBaseViewHolder {
        val view = getView<View>(viewId)
        val params = view.layoutParams as LinearLayout.LayoutParams
        params.height = heightInt
        view.layoutParams = params
        return this
    }

    /**
     * 设置recyclerView中设置控件的宽度
     */
    fun setLinearLayoutWidth(viewId: Int, widthInt: Int): MyBaseViewHolder {
        val view = getView<View>(viewId)

        val params = view.layoutParams as LinearLayout.LayoutParams
        params.width = widthInt
        view.layoutParams = params
        return this
    }

    /**
     * 设置recyclerView中RelativeLayout的高度
     */
    fun setRelativeLayoutHeight(viewId: Int, heightInt: Int): MyBaseViewHolder {
        val view = getView<View>(viewId)
        val layoutParams = view.layoutParams as GridLayoutManager.LayoutParams
        layoutParams.height = heightInt
        view.layoutParams = layoutParams
        return this
    }

    /**
     * 设置recyclerView中RelativeLayout的宽度
     */
    fun setRelativeLayoutWidth(viewId: Int, widthInt: Int): MyBaseViewHolder {
        val view = getView<View>(viewId)
        val layoutParams = view.layoutParams as GridLayoutManager.LayoutParams
        layoutParams.width = widthInt
        view.layoutParams = layoutParams
        return this
    }


//    /**
//     * 设置给spinner设置数据
//     */
//    fun setSpinner(context: Context, viewId: Int, pointBeanList: MutableList<SpinnerBean>): MyBaseViewHolder {
//        val view = getView<AppCompatSpinner>(viewId)
//        initViewSpinner(context, view, pointBeanList)
//        return this
//    }
//
//
//    private fun initViewSpinner(context: Context, spinner: AppCompatSpinner, dataSpinnerList: MutableList<SpinnerBean>) {
//        val adapter = SpinnerAdapterKt(dataSpinnerList, context)  //创建一个数组适配器
//        spinner.adapter = adapter
//        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onNothingSelected(parent: AdapterView<*>?) = Unit
//
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                ToastUtil.toast(context, dataSpinnerList[position].dataName)
//            }
//        }
//    }


    /**
     * RadioGroup的点击事件
     */
    fun setOnCheckedChangeListener(viewId: Int, listener: RadioGroup.OnCheckedChangeListener): BaseViewHolder {
        val view = getView<RadioGroup>(viewId)
        view.setOnCheckedChangeListener(listener)
        return this
    }


//    /**
//     * item的recyclerview控件初始化
//     */
//
//    fun initRecycler(context: Context, viewId: Int, questionList: MutableList<QuestionBean>, projectID: String, categoryID: String) {
//        val view = getView<RecyclerView>(viewId)
//        val recyclerViewUtilKt = RecyclerViewUtilKt(context, view)
//        recyclerViewUtilKt.initRecyclerView()
//        val inspectPointsItemAdapter = InspectPointsItemAdapter(context, R.layout.item_item_inspect_points, questionList, projectID, categoryID)
//        recyclerViewUtilKt.setAdapter(inspectPointsItemAdapter)
//    }
//
//    /**
//     * 给GrioRadio添加RadioButton
//     */
//    fun setRadioButton(context: Context, viewId: Int, tempButtonList: MutableList<String>) {
//        tempButtonList.forEachIndexed { index, s ->
//            val groupRadio = getView<RadioGroup>(viewId)
//            val tempButton = RadioButton(context)
//            tempButton.setButtonDrawable(R.drawable.check_box_btn_bg)    // 设置按钮的样式
//            tempButton.setPadding(20, 5, 80, 5)      // 设置文字距离按钮四周的距离
//            tempButton.text = s
//            groupRadio.addView(tempButton, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
//            if (index==0){
//                groupRadio.check(tempButton.id)
//            }
//        }
//    }
}



