package com.hanbitkang.feature.detail.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hanbitkang.core.designsystem.MpNavigationDestination
import com.hanbitkang.feature.detail.PokemonDetailRoute

object PokemonDetailDestination : MpNavigationDestination {
    const val pokemonIdArg = "pokemonId"
    override val route = "pokemon_detail_route/{$pokemonIdArg}"
    override val destination = "pokemon_detail_destination"

    fun createNavigationRoute(pokemonIdArg: Int): String {
        return "pokemon_detail_route/$pokemonIdArg"
    }
}

fun NavGraphBuilder.pokemonDetailGraph(
    onClickBackButton: () -> Unit
) {
    composable(
        route = PokemonDetailDestination.route,
        arguments = listOf(
            navArgument(PokemonDetailDestination.pokemonIdArg) { type = NavType.IntType }
        )
    ) {
        PokemonDetailRoute(onClickBackButton = onClickBackButton)
    }
}