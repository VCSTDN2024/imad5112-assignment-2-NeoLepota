package vcmsa.ci.trueorfalse

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.system.exitProcess

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main3)

        val reviewTextView = findViewById<TextView>(R.id.reviewTextView)
        val btnRestart = findViewById<Button>(R.id.btnRestart)
        val btnExitApp = findViewById<Button>(R.id.btnExitApp)

        val questions = intent.getStringArrayExtra("questions")
        val answers = intent.getStringArrayExtra("answers")

        // Create a StringBuilder to build the review text
        val reviewText = StringBuilder()
        if (questions != null && answers != null && questions.size == answers.size) {
            for (i in questions.indices) {
                reviewText.append("${i + 1}. ${questions[i]}\n")
                reviewText.append("  Answer: ${if (answers[i] == "True") "True" else "False"}\n\n")
            }
            reviewTextView.text = reviewText.toString()
        } else {
            reviewTextView.text = "Cannot load data"
        }

        // Display the score
        val score = intent.getIntExtra("score", 0)
        reviewTextView.append("\nThe score you got: $score/5\n")

        val feedback = if (score >= 3) {
            "Perfect!"
        } else {
            "Please Try to do better"
        }
        reviewTextView.append(feedback)

        btnRestart.setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
        }

        btnExitApp.setOnClickListener {
            finishAffinity()
            exitProcess(0)
        }
    }
}

