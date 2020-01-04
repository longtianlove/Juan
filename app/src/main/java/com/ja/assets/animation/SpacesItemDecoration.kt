package com.fixed.u8.animation

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by zry on 2017/12/16.
 */
class SpacesItemDecoration(private val space: Int,private val column:Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.left = space
        outRect.right = space
        outRect.bottom = space
        outRect.top = space

        if (parent.getChildLayoutPosition(view) %column==0) {
            outRect.left = 0
        }
    }
}