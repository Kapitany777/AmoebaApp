package eu.braincluster.amoeabaapp.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class TicTacToeView(context: Context?, attrs: AttributeSet?) : View(context, attrs)
{
    private var paintBackground: Paint = Paint()
    private var paintLine: Paint = Paint()

    init
    {
        paintBackground.color = Color.BLACK
        paintBackground.style = Paint.Style.FILL

        paintLine.color = Color.GREEN
        paintLine.style = Paint.Style.STROKE
        paintLine.strokeWidth = 5f

    }

    override fun onDraw(canvas: Canvas)
    {
        super.onDraw(canvas)

        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paintBackground)

        drawBoard(canvas)
    }

    private fun drawBoard(canvas: Canvas)
    {
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paintLine)

        canvas.drawLine(0f, height / 3.0f, width.toFloat(), height / 3.0f, paintLine)
        canvas.drawLine(0f, 2.0f * height / 3.0f, width.toFloat(), 2.0f * height / 3.0f, paintLine)

        canvas.drawLine(width / 3.0f, 0f, width / 3.0f, height.toFloat(), paintLine)
        canvas.drawLine(2.0f * width / 3.0f, 0f, 2.0f * width / 3.0f, height.toFloat(), paintLine)
    }
}