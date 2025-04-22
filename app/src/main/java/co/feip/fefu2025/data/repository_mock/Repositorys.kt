package co.feip.fefu2025.data.repository_mock

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import co.feip.fefu2025.R
import co.feip.fefu2025.domain.model.Repository
import java.time.LocalDate

@SuppressLint("NewApi")
val repositories = listOf(
    Repository(
        id = 1,
        repositoryName = "GitLab App",
        description = "Приложение для работы с GitLab API",
        stars = 150,
        forks = 30,
        avatarRes = R.drawable.download,
        isMyRepo = true,
        languages = mapOf("Kotlin" to 70f, "Java" to 30f),
        date = LocalDate.of(2025, 3, 1)
    ),
    Repository(
        id = 2,
        repositoryName = "Custom FlexBoxLayout",
        description = "Кастомный ViewGroup для динамического размещения элементов",
        stars = 95,
        forks = 18,
        avatarRes = R.drawable.lovecat,
        isMyRepo = true,
        languages = mapOf("Kotlin" to 100f),
        date = LocalDate.of(2025, 2, 15)
    ),
    Repository(
        id = 3,
        repositoryName = "Anime Genre View",
        description = "Компонент для отображения жанров аниме",
        stars = 80,
        forks = 12,
        avatarRes = R.drawable.ducks,
        isMyRepo = true,
        languages = mapOf("Kotlin" to 90f, "XML" to 10f),
        date = LocalDate.of(2025, 1, 28)
    ),
    Repository(
        id = 4,
        repositoryName = "Kotlin SVD Compressor",
        description = "Алгоритм сжатия изображений с использованием SVD",
        stars = 120,
        forks = 25,
        avatarRes = R.drawable.cruto,
        isMyRepo = true,
        languages = mapOf("Kotlin" to 100f),
        date = LocalDate.of(2025, 1, 5)
    ),
    Repository(
        id = 5,
        repositoryName = "Flutter Web Converter",
        description = "Конвертер HTML/CSS в Flutter-код",
        stars = 140,
        forks = 35,
        avatarRes = R.drawable.dina,
        isMyRepo = true,
        languages = mapOf("Dart" to 95f, "HTML" to 5f),
        date = LocalDate.of(2024, 12, 22)
    ),
    Repository(
        id = 6,
        repositoryName = "Jetpack Compose UI",
        description = "Набор лучших практик для Compose",
        stars = 200,
        forks = 50,
        avatarRes = R.drawable.cruto,
        isMyRepo = false,
        languages = mapOf("Kotlin" to 100f),
        date = LocalDate.of(2024, 11, 20)
    ),
    Repository(
        id = 7,
        repositoryName = "Android Architecture Samples",
        description = "Примеры архитектурных решений на Android",
        stars = 350,
        forks = 90,
        avatarRes = R.drawable.cruto,
        isMyRepo = false,
        languages = mapOf("Kotlin" to 70f, "Java" to 30f),
        date = LocalDate.of(2024, 10, 18)
    ),
    Repository(
        id = 8,
        repositoryName = "Kotlin Coroutines Guide",
        description = "Руководство по Kotlin Coroutines",
        stars = 500,
        forks = 120,
        avatarRes = R.drawable.cruto,
        isMyRepo = false,
        languages = mapOf("Kotlin" to 100f),
        date = LocalDate.of(2024, 8, 1)
    ),
    Repository(
        id = 9,
        repositoryName = "Retrofit API Client",
        description = "Пример использования Retrofit с Hilt и Flow",
        stars = 270,
        forks = 75,
        avatarRes = R.drawable.cruto,
        isMyRepo = false,
        languages = mapOf("Kotlin" to 100f),
        date = LocalDate.of(2024, 7, 11)
    ),
    Repository(
        id = 10,
        repositoryName = "Room Database Example",
        description = "Пример работы с Room и Jetpack ViewModel",
        stars = 310,
        forks = 85,
        avatarRes = R.drawable.cruto,
        isMyRepo = false,
        languages = mapOf("Kotlin" to 80f, "SQL" to 20f),
        date = LocalDate.of(2024, 5, 20)
    )
)
