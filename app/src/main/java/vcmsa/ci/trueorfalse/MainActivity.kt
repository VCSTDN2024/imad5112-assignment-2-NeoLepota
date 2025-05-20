package vcmsa.ci.trueorfalse
import android.content.Intent
import android.os.Bundle
import android.os.Process
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val welcomeMessage = findViewById<TextView>(R.id.welcomeMessage)
        val startButton = findViewById<Button>(R.id.btnStart)
        val exitButton = findViewById<Button>(R.id.btnExit)

        welcomeMessage.text = "Welcome to True or False"
        // Set click listeners for the buttons
        startButton.setOnClickListener {
            val intent = Intent(this,MainActivity2::class.java)
            startActivity(intent)
        }

        exitButton.setOnClickListener {
            moveTaskToBack(true)
            Process.killProcess(Process.myPid())

        }
    }
}