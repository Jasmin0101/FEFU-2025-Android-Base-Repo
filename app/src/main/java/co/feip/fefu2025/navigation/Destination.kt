package co.feip.fefu2025.navigation

import kotlinx.serialization.Serializable


sealed interface Destination {

    @Serializable
    data object BaseGraph: Destination

    @Serializable
    data object HomePage: Destination

    @Serializable
    data class Repository(val id : Int ) : Destination

    @Serializable
    data object MyStars: Destination
}
