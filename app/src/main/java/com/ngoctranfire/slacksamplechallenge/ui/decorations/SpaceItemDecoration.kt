package com.ngoctranfire.slacksamplechallenge.ui.decorations

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by ngoctranfire on 9/27/17.
 */

class SpaceItemDecoration(private val left: Int,
                          private val right: Int,
                          private val top: Int,
                          private val bottom: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
        outRect.left = left
        outRect.right = right
        outRect.top = top
        outRect.bottom = bottom
    }
}
