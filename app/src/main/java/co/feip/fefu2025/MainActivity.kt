package co.feip.fefu2025
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import android.util.Log
class MainActivity : ComponentActivity() {

    private var counter = 0
    private lateinit var textViewCounter: TextView
    private lateinit var textViewButton: TextView

    private val networkReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (isInternetAvailable(context)) {
                Log.d("Internet Connection", "Интернет: подключен\n")
            } else {
                Log.d("Internet Connection", "Интернет: отключен\n")
            }
        }
    }

    private fun isInternetAvailable(context: Context?): Boolean {
        val connectivityManager =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        textViewCounter = findViewById(R.id.textCounter)
        textViewButton = findViewById(R.id.textButton)

        // Восстанавливаем значение counter, если есть сохранённое состояние
        counter = savedInstanceState?.getInt("counter") ?: 0
        textViewCounter.text = "КОЛИЧЕСТВО ГРЕХОВ: $counter"

        textViewButton.setOnClickListener {
            counter++
            textViewCounter.text = "КОЛИЧЕСТВО ГРЕХОВ: $counter"
        }
        registerReceiver(networkReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    // Сохраняем значение counter при перевороте экрана
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("counter", counter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(networkReceiver)
    }

}
