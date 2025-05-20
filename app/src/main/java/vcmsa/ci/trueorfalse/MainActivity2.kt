package vcmsa.ci.trueorfalse

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {

    private lateinit var btnTrue: Button
    private lateinit var btnFalse: Button
    private lateinit var btnNext: Button
    private lateinit var statementsTextView: TextView
    private lateinit var btnReview: Button

    companion object {
        val questions = arrayOf(
            "Barcelona is the best team in the world",
            "Barcelona lost the Supercopa this season",
            "Lamine Yamal is the youngest player to start for Barcelona",
            "Barcelona have sextuple",
            "Pedri Gonzalez is from La Masia",
            "Barcelona is sponsored by Spotify"
        )
        val answers = booleanArrayOf(true, false, true, true, false, true)

        private var currentQuestionIndex = 0
        private var score = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        statementsTextView = findViewById(R.id.statementsTextView)
        btnNext = findViewById(R.id.btnNext)
        btnTrue = findViewById(R.id.btnTrue)
        btnFalse = findViewById(R.id.btnFalse)
        btnReview = findViewById(R.id.btnReview)

        displayQuestion()

        btnTrue.setOnClickListener {
            checkAnswer(true)
        }

        btnFalse.setOnClickListener {
            checkAnswer(false)
        }

        btnNext.setOnClickListener {
            nextQuestion()
        }

        btnReview.setOnClickListener {
            val intent = Intent(this, MainActivity3::class.java)
            intent.putExtra("score", score)
            intent.putExtra("questions", questions)
            intent.putExtra("answers", answers)
            startActivity(intent)
        }
    }
    // Function to display the current question
    private fun displayQuestion() {
        statementsTextView.text = questions[currentQuestionIndex]
    }
    // Function to check the answer and update the UI
    private fun checkAnswer(userAnswer: Boolean) {
        val correct = answers[currentQuestionIndex]
        val (message, color) = if (userAnswer == correct) {
            score++
            "Correct!" to Color.GREEN
        } else {
            "Incorrect!" to Color.RED
        }

        statementsTextView.apply {
            text = message
            setTextColor(color)
        }

        btnTrue.isEnabled = false
        btnFalse.isEnabled = false
        btnNext.isEnabled = true
    }

    private fun nextQuestion() {
        currentQuestionIndex = (currentQuestionIndex + 1) % questions.size
        displayQuestion()

        // Clear feedback and re-enable buttons
        statementsTextView.text = questions[currentQuestionIndex]
        statementsTextView.setTextColor(Color.BLACK)
        btnTrue.isEnabled = true
        btnFalse.isEnabled = true
        btnNext.isEnabled = false
    }
}


