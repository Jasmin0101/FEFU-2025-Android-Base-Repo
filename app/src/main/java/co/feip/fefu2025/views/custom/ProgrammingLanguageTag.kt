
package co.feip.fefu2025.views.custom

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


////ДИСКЛЕЙМЕР: комментарии чисто для меня :3 спасибо за понимание
//
////@JvmOverloads constructor - это конструктор который мы навешиваем над классом чтобы в джае можно было перегружать класс
//// условно мы говорим что у нас атрибут не обязательно явно указывать при выборе, как с указанием required в flutter
//
class ProgrammingLanguageTag  @JvmOverloads constructor (context: Context, attrs:  AttributeSet? = null): LinearLayout(context,attrs) {
    private val circleView: View
    private val textView: TextView

    init {
        // сказали что использовать в качестве референса
        LayoutInflater.from(context).inflate(R.layout.programming_language_tag,this,true)
        // говорим переменным искать тут референсы
        circleView = findViewById(R.id.circle)
        textView = findViewById(R.id.languageText)
    }

    fun setProperties(name: String, color: Color, persent : Float ){
        textView.text = "$name ${String.format("%.1f", persent)}%"

        circleView.backgroundTintList = ColorStateList.valueOf(color.toArgb())
    }

}