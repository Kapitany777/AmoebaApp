package eu.braincluster.amoeabaapp.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import eu.braincluster.amoeabaapp.MainActivity
import eu.braincluster.amoeabaapp.model.GameResult
import eu.braincluster.amoeabaapp.model.Sign
import eu.braincluster.amoeabaapp.model.TicTacToeModel

class TicTacToeView(context: Context?, attrs: AttributeSet?) : View(context, attrs)
{
    private var paintBackground: Paint = Paint()
    private var paintLine: Paint = Paint()
    private var paintLineCircle: Paint = Paint()
    private var paintLineCross: Paint = Paint()

    init
    {
        paintBackground.color = Color.BLACK
        paintBackground.style = Paint.Style.FILL

        paintLine.color = Color.GREEN
        paintLine.style = Paint.Style.STROKE
        paintLine.strokeWidth = 5f

        paintLineCircle.color = Color.BLUE
        paintLineCircle.style = Paint.Style.STROKE
        paintLineCircle.strokeWidth = 8f

        paintLineCross.color = Color.RED
        paintLineCross.style = Paint.Style.STROKE
        paintLineCross.strokeWidth = 8f
    }

    override fun onDraw(canvas: Canvas)
    {
        super.onDraw(canvas)

        drawBoard(canvas)
        drawPlayers(canvas)
    }

    private fun drawBoard(canvas: Canvas)
    {
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paintBackground)

        canvas.drawLine(0f, height / 3.0f, width.toFloat(), height / 3.0f, paintLine)
        canvas.drawLine(0f, 2.0f * height / 3.0f, width.toFloat(), 2.0f * height / 3.0f, paintLine)

        canvas.drawLine(width / 3.0f, 0f, width / 3.0f, height.toFloat(), paintLine)
        canvas.drawLine(2.0f * width / 3.0f, 0f, 2.0f * width / 3.0f, height.toFloat(), paintLine)
    }

    private fun drawPlayers(canvas: Canvas)
    {
        for (i in 0..2)
        {
            for (j in 0..2)
            {
                if (TicTacToeModel.getFieldContent(i, j) == Sign.CIRCLE)
                {
                    val centerX = i * width / 3f + width / 6f
                    val centerY = j * height / 3f + height / 6f
                    val radius = height / 6f - 2f

                    canvas.drawCircle(centerX, centerY, radius, paintLine)
                }
                else if (TicTacToeModel.getFieldContent(i, j) == Sign.CROSS)
                {
                    canvas.drawLine(
                        i * width / 3f, j * height / 3f,
                        (i + 1) * width / 3f, (j + 1) * height / 3f, paintLineCross
                    )

                    canvas.drawLine(
                        (i + 1) * width / 3f, j * height / 3f,
                        i * width / 3f, (j + 1) * height / 3f, paintLineCross
                    )
                }
            }
        }
    }

    fun resetGame()
    {
        TicTacToeModel.resetModel()
        invalidate()

        showNextPlayer()
    }

    private fun showNextPlayer()
    {
        var next = if (TicTacToeModel.getNextPlayer() == Sign.CIRCLE) "O" else "X"
        (context as MainActivity).showText("Next player is: $next")
    }

    private fun showWinner(winner: GameResult)
    {
        var text = "TIE"

        if (winner == GameResult.CIRCLE)
        {
            text = "O"
        }
        else if (winner == GameResult.CROSS)
        {
            text = "X"
        }

        (context as MainActivity).showText("The winner is: $text")
    }

    override fun onTouchEvent(event: MotionEvent): Boolean
    {
        if (event.action != MotionEvent.ACTION_DOWN)
        {
            return true
        }

        if (TicTacToeModel.getWinner() != GameResult.NOT_FINISHED)
        {
            return true;
        }

        val x = event.x.toInt() / (width / 3)
        val y = event.y.toInt() / (height / 3)

        if (x < 3 && y < 3 && TicTacToeModel.getFieldContent(x, y) == Sign.EMPTY)
        {
            TicTacToeModel.setFieldContent(x, y, TicTacToeModel.getNextPlayer())

            val gameResult = TicTacToeModel.getWinner()

            if (gameResult == GameResult.NOT_FINISHED)
            {
                TicTacToeModel.changeNextPlayer()
                showNextPlayer()
            }
            else
            {
                showWinner(gameResult)
            }

            invalidate()
        }

        return true
    }
}