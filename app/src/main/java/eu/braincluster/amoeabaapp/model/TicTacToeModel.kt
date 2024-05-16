package eu.braincluster.amoeabaapp.model

object TicTacToeModel
{
    private val model = arrayOf(
        arrayOf(Sign.EMPTY, Sign.EMPTY, Sign.EMPTY),
        arrayOf(Sign.EMPTY, Sign.EMPTY, Sign.EMPTY),
        arrayOf(Sign.EMPTY, Sign.EMPTY, Sign.EMPTY)
    )

    private var nextPlayer = Sign.CIRCLE

    fun resetModel()
    {
        for (i in 0..2)
        {
            for (j in 0..2)
            {
                model[i][j] = Sign.EMPTY
            }
        }

        nextPlayer = Sign.CIRCLE
    }

    fun getFieldContent(x: Int, y: Int) = model[x][y]

    fun setFieldContent(x: Int, y: Int, content: Sign)
    {
        model[x][y] = content
    }

    fun getNextPlayer() = nextPlayer

    fun changeNextPlayer()
    {
        nextPlayer = if (nextPlayer == Sign.CIRCLE) Sign.CROSS else Sign.CIRCLE
    }

    fun getWinner(): GameResult
    {
        val row1_O = model[0][0] == Sign.CIRCLE && model[1][0] == Sign.CIRCLE && model[2][0] == Sign.CIRCLE
        val row2_O = model[0][1] == Sign.CIRCLE && model[1][1] == Sign.CIRCLE && model[2][1] == Sign.CIRCLE
        val row3_O = model[0][2] == Sign.CIRCLE && model[1][2] == Sign.CIRCLE && model[2][2] == Sign.CIRCLE
        val col1_O = model[0][0] == Sign.CIRCLE && model[0][1] == Sign.CIRCLE && model[0][2] == Sign.CIRCLE
        val col2_O = model[1][0] == Sign.CIRCLE && model[1][1] == Sign.CIRCLE && model[1][2] == Sign.CIRCLE
        val col3_O = model[2][0] == Sign.CIRCLE && model[2][1] == Sign.CIRCLE && model[2][2] == Sign.CIRCLE
        val diag1_O = model[0][0] == Sign.CIRCLE && model[1][1] == Sign.CIRCLE && model[2][2] == Sign.CIRCLE
        val diag2_O = model[2][0] == Sign.CIRCLE && model[1][1] == Sign.CIRCLE && model[0][2] == Sign.CIRCLE
        val row1_X = model[0][0] == Sign.CROSS && model[1][0] == Sign.CROSS && model[2][0] == Sign.CROSS
        val row2_X = model[0][1] == Sign.CROSS && model[1][1] == Sign.CROSS && model[2][1] == Sign.CROSS
        val row3_X = model[0][2] == Sign.CROSS && model[1][2] == Sign.CROSS && model[2][2] == Sign.CROSS
        val col1_X = model[0][0] == Sign.CROSS && model[0][1] == Sign.CROSS && model[0][2] == Sign.CROSS
        val col2_X = model[1][0] == Sign.CROSS && model[1][1] == Sign.CROSS && model[1][2] == Sign.CROSS
        val col3_X = model[2][0] == Sign.CROSS && model[2][1] == Sign.CROSS && model[2][2] == Sign.CROSS
        val diag1_X = model[0][0] == Sign.CROSS && model[1][1] == Sign.CROSS && model[2][2] == Sign.CROSS
        val diag2_X = model[2][0] == Sign.CROSS && model[1][1] == Sign.CROSS && model[0][2] == Sign.CROSS

        if (row1_O || row2_O || row3_O || col1_O || col2_O || col3_O || diag1_O || diag2_O)
        {
            return GameResult.CIRCLE
        }
        else if (row1_X || row2_X || row3_X || col1_X || col2_X || col3_X || diag1_X || diag2_X)
        {
            return GameResult.CROSS
        }
        else
        {
            for (i in 0..2)
            {
                for (j in 0..2)
                {
                    if (model[i][j] == Sign.EMPTY)
                    {
                        return GameResult.NOT_FINISHED
                    }
                }
            }
        }

        return GameResult.TIE
    }
}
