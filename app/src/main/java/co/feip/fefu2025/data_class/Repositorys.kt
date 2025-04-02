package co.feip.fefu2025.data_class

import co.feip.fefu2025.R

data class Repository(
    val repositoryName: String,
    val description: String,
    val stars: Int,
    val forks: Int,
    val avatarRes: Int? = null,
    val isMyRepo: Boolean? = false,
)

val repositories = listOf(
    Repository(
        repositoryName = "GitLab App",
        description = "Приложение для работы с GitLab API",
        stars = 150,
        forks = 30,
        avatarRes = R.drawable.download,
        isMyRepo = true,
    ),
    Repository(
        repositoryName = "Custom FlexBoxLayout",
        description = "Кастомный ViewGroup для динамического размещения элементов",
        stars = 95,
        forks = 18,
        avatarRes = R.drawable.lovecat,
                isMyRepo = true,
    ),
    Repository(
        repositoryName = "Anime Genre View",
        description = "Компонент для отображения жанров аниме",
        stars = 80,
        forks = 12,
        avatarRes = R.drawable.ducks
        ,
        isMyRepo = true,
    ),
    Repository(
        repositoryName = "Kotlin SVD Compressor",
        description = "Алгоритм сжатия изображений с использованием SVD",
        stars = 120,
        forks = 25,
        avatarRes = R.drawable.cruto
        ,
        isMyRepo = true,
    ),
    Repository(
        repositoryName = "Flutter Web Converter",
        description = "Конвертер HTML/CSS в Flutter-код",
        stars = 140,
        forks = 35,
        avatarRes = R.drawable.dina
        ,
        isMyRepo = true,
    ),
    Repository(
        repositoryName = "Jetpack Compose UI",
        description = "Набор лучших практик для Compose",
        stars = 200,
        forks = 50,
        avatarRes = R.drawable.cruto
    ),
    Repository(
        repositoryName = "Android Architecture Samples",
        description = "Примеры архитектурных решений на Android",
        stars = 350,
        forks = 90,
        avatarRes = R.drawable.cruto
    ),
    Repository(
        repositoryName = "Kotlin Coroutines Guide",
        description = "Руководство по Kotlin Coroutines",
        stars = 500,
        forks = 120,
        avatarRes = R.drawable.cruto
    ),
    Repository(
        repositoryName = "Retrofit API Client",
        description = "Пример использования Retrofit с Hilt и Flow",
        stars = 270,
        forks = 75,
        avatarRes = R.drawable.cruto
    ),
    Repository(
        repositoryName = "Room Database Example",
        description = "Пример работы с Room и Jetpack ViewModel",
        stars = 310,
        forks = 85,
        avatarRes = R.drawable.cruto
    )
)
