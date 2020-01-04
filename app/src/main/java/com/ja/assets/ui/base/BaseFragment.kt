package com.fixed.u8.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * Created by Administrator on 2017/5/17 0017.
 */

abstract class BaseFragment : Fragment() {


    var fragmentDataBinding: ViewDataBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setOnCreate()
    }

    protected abstract fun setOnCreate()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        initDataView()
        return fragmentDataBinding?.root
    }


    protected abstract fun getLayoutId(): Int

    protected abstract fun initDataView()

}
