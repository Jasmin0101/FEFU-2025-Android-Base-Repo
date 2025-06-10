package co.feip.fefu2025.presentation.custom

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View

import android.widget.LinearLayout
import android.widget.TextView
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import co.feip.fefu2025.R


class ProgrammingLanguageTag @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {
    private val circleView: View
    private val textView: TextView

    init {
        LayoutInflater.from(context).inflate(R.layout.programming_language_tag, this, true)
        circleView = findViewById(R.id.circle)
        textView = findViewById(R.id.languageText)
    }

    fun setProperties(name: String, color: Color, persent: Float) {
        textView.text = "$name ${String.format("%.1f", persent)}%"

        circleView.backgroundTintList = ColorStateList.valueOf(color.toArgb())
    }

}