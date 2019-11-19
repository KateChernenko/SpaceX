package com.kchernenko.spacex.ui.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.kchernenko.spacex.R
import java.util.*
import java.util.concurrent.TimeUnit

class GraphView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private var linePaint: Paint
    private val pointPaint: Paint
    private val textPaint: Paint
    private val gridPaint: Paint
    private var timeStep = 0
    private var launchStep = 0
    private var verticalPadding = 0
    private var data: List<MonthLaunches> = emptyList()

    init {
        linePaint = Paint().apply {
            color = ContextCompat.getColor(context!!, R.color.colorPrimaryDark)
            strokeWidth = 5f }
        pointPaint = Paint().apply { color = ContextCompat.getColor(context!!, R.color.colorPrimaryDark) }
        gridPaint = Paint().apply { color = Color.LTGRAY }
        textPaint = Paint().apply { color = Color.LTGRAY
            textSize = resources.getDimensionPixelSize(R.dimen.graph_text).toFloat() }

        timeStep = resources.getDimensionPixelSize(R.dimen.graph_time_step)
        verticalPadding = resources.getDimensionPixelSize(R.dimen.graph_vertical_padding)
    }

    fun setData(data: List<MonthLaunches>?) {
        if (data.isNullOrEmpty()) {
            return
        }
        this.data = data

        minimumWidth = (numberOfPeriods() + 5) * timeStep
        val maxLaunches = getMaxLaunches()
        launchStep = height / if ((maxLaunches - 2) == 0) 2 else (maxLaunches - 2)

        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (!data.isNullOrEmpty()) {
            val maxLaunches = getMaxLaunches()
            launchStep = height / maxLaunches
            drawGrid(canvas!!, maxLaunches, numberOfPeriods(), data[0].timestamp)
            drawLaunches(canvas)
        }
    }

    private fun drawLaunches(canvas: Canvas) {
        var curX = 0
        var curY = data[0].launchesNum * launchStep
        for (i in 0 until data.size - 1) {
            val nextX = curX + diffInMonths(data[i], data[i+1]) * timeStep
            val nextY = data[i+1].launchesNum * launchStep

            canvas.drawCircle(getX(curX), getY(curY), 10f, pointPaint)
            if (i == data.size - 2) {
                canvas.drawCircle(getX(nextX), getY(nextY), 10f, pointPaint)
            }
            drawLine(canvas, curX, curY, nextX, nextY)
            curX = nextX
            curY = nextY
        }
    }

    private fun drawGrid(canvas: Canvas, numOfLaunches: Int, numOfMonths: Int, firstTimestamp: Long) {
        var curY = 0
        for (i in 0 until numOfLaunches + 1) {
            if (i == 0){
                canvas.drawText("$i",
                    10f, height - textPaint.textSize / 2,
                    textPaint)
            } else {
                canvas.drawText("$i",
                    10f, getY(curY) - textPaint.textSize / 2,
                    textPaint)
            }
            canvas.drawLine(0f, getY(curY),
                getX((numOfMonths + 5) * timeStep), getY(curY),
                gridPaint)
            curY += launchStep
        }

        var curX = 0
        var curTimestamp = firstTimestamp
        val yearLinePaint = Paint().apply { color = gridPaint.color
            strokeWidth = 5f }
        for (i in 0 until numOfMonths + 5) {
            val isYear = isYear(curTimestamp)
            if (isYear) {
                canvas.drawText("${getYear(curTimestamp)}",
                    getX(curX) + 10, height - textPaint.textSize / 2,
                    textPaint)
            }
            canvas.drawLine(getX(curX), 0f,
                getX(curX), height.toFloat(),
                if (isYear) yearLinePaint else gridPaint)
            curTimestamp += TimeUnit.DAYS.toMillis(30)
            curX += timeStep
        }
    }

    private fun drawLine(canvas: Canvas, x1: Int, y1: Int, x2: Int, y2: Int) {
        canvas.drawLine(getX(x1), getY(y1), getX(x2), getY(y2), linePaint)
    }

    private fun getX(physicalX: Int) = physicalX.toFloat() + timeStep
    private fun getY(physicalY: Int) = (height - physicalY).toFloat() + textPaint.textSize*2

    private fun getMaxLaunches(): Int = data.map { it.launchesNum }.max()!!

    private fun diffInMonths(current: MonthLaunches, next: MonthLaunches): Int {
        val start = GregorianCalendar.getInstance().apply { time = Date(current.timestamp) }
        val end = GregorianCalendar.getInstance().apply { time = Date(next.timestamp) }
        val yearsInBetween = end.get(Calendar.YEAR) - start.get(Calendar.YEAR)
        val monthsDiff = end.get(Calendar.MONTH) - start.get(Calendar.MONTH)
        return (yearsInBetween * 12) + monthsDiff
    }

    private fun isYear(timestamp: Long): Boolean {
        val cal = GregorianCalendar.getInstance()
            .apply { time = Date(timestamp) }
        return cal.get(Calendar.MONTH) == 11
    }

    private fun getYear(timestamp: Long) = GregorianCalendar.getInstance()
        .apply { time = Date(timestamp) }
        .get(Calendar.YEAR)

    private fun numberOfPeriods(): Int {
        return diffInMonths(data.minBy { it.timestamp }!!, data.maxBy { it.timestamp }!!)
    }

    data class MonthLaunches(val timestamp: Long, val launchesNum: Int)

}