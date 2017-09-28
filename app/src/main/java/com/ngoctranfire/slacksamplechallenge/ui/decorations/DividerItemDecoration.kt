package com.ngoctranfire.slacksamplechallenge.ui.decorations

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import com.ngoctranfire.slacksamplechallenge.R

/**
 * Created by ngoctranfire on 9/27/17.
 */
class DividerItemDecoration(context: Context) : RecyclerView.ItemDecoration() {
    private val divider: Drawable = ContextCompat.getDrawable(context, R.drawable.line_divider)

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state)
        if (parent!!.getChildAdapterPosition(view) == 0) {
            return
        }
        outRect!!.top = divider.intrinsicHeight
    }
    override fun onDrawOver(c: Canvas?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.onDrawOver(c, parent, state)
        val left = parent!!.paddingLeft
        val right = parent.width - parent.paddingRight

        val childCount = parent.childCount
        var child: View

        var top: Int
        var bottom: Int

        for (i in 0 until childCount) {
            child = parent.getChildAt(i)
            val layoutParams = child.layoutParams as RecyclerView.LayoutParams
            top = child.bottom + layoutParams.bottomMargin
            bottom = top + divider.intrinsicHeight

            divider.setBounds(left, top, right, bottom)
            divider.draw(c)
        }
    }
}