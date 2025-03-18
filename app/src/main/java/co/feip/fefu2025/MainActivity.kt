package co.feip.fefu2025

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.Color
import co.feip.fefu2025.views.custom.ProgrammingLanguageTag
import views.FexBoxLayoutCustom
import kotlin.random.Random

class MainActivity : ComponentActivity() {

    private val flexBoxLayout : FexBoxLayoutCustom by lazy { findViewById(R.id.flexBox) }
    private val button : Button by lazy { findViewById(R.id.addItemButton) }

    val programmingLanguages = arrayOf(
        "Java", "Kotlin", "Python", "C", "C++", "C#", "JavaScript", "TypeScript",
        "Swift", "Go", "Rust", "PHP", "Ruby", "Dart", "Scala", "Perl", "Haskell",
        "Objective-C", "Lua", "F#", "Elixir", "Clojure", "R", "Julia", "MATLAB"
    )

    fun getRandomColor(): Color {
        val red = Random.nextInt(0, 256) // Случайное значение от 0 до 255
        val green = Random.nextInt(0, 256)
        val blue = Random.nextInt(0, 256)
        val alpha = 255 // Прозрачность, можно сделать случайной тоже, если нужно

        return Color(alpha, red, green, blue) // Создаём цвет
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.main_activity)

        button.setOnClickListener{

            val tag = ProgrammingLanguageTag(this)

            val randomLanguage = programmingLanguages.random()
            val randomColor = getRandomColor()
            var randomPercent = (Random.nextFloat() * 100)

            tag.setProperties(
                randomLanguage,
                randomColor,
                randomPercent
            )

            flexBoxLayout.addView( tag);
        }
    }


}



