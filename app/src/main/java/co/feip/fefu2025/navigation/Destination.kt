package co.feip.fefu2025.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Destination {
    @Serializable
    data object HomePage: Destination
    @Serializable
    data class Repositories(val id : Int ) : Destination
}
