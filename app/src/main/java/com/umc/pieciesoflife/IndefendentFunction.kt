package com.umc.pieciesoflife

import androidx.recyclerview.widget.RecyclerView

/**
 * 스크롤 아래로 퍼센트 계산
 * @return %
 */
@JvmName("scrollPercent1")
fun scrollPercent(recyclerView: RecyclerView): Double {
        return (recyclerView.computeVerticalScrollOffset() * 1.0 / (recyclerView.computeVerticalScrollRange() - recyclerView.computeVerticalScrollExtent())) * 100.0
    }

    fun RecyclerView.scrollPercent() : Double {
        return (this.computeVerticalScrollOffset() * 1.0 / (this.computeVerticalScrollRange() - this.computeVerticalScrollExtent())) * 100.0
    }