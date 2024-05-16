package eu.braincluster.amoeabaapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import eu.braincluster.amoeabaapp.databinding.ActivityMainBinding
import eu.braincluster.amoeabaapp.model.TicTacToeModel

class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonReset.setOnClickListener()
        {
            binding.ticTacToeView.resetGame()
        }

        binding.ticTacToeView.resetGame()
    }

    fun showText(text : String)
    {
        binding.textViewInfo.text = text
    }
}