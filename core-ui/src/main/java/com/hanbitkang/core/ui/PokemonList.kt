package com.hanbitkang.core.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.hanbitkang.core.model.Pokemon

@Composable
fun PokemonList(
    modifier: Modifier = Modifier,
    pokemons: List<Pokemon>,
    navigateToPokemonDetail: (Int) -> Unit,
    onScrollBottom: () -> Unit = {},
    switchIsFavorite: (Pokemon) -> Unit,
) {
    LazyVerticalGrid(
        modifier = modifier
            .padding(10.dp),
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(pokemons) {
            PokemonCard(
                pokemon = it,
                navigateToPokemonDetail = navigateToPokemonDetail,
                switchIsFavorite = switchIsFavorite
            )
        }
        // This item works as a bottom scroll listener.
        item {
            LaunchedEffect(true) {
                onScrollBottom()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PokemonCard(
    pokemon: Pokemon,
    navigateToPokemonDetail: (Int) -> Unit,
    switchIsFavorite: (Pokemon) -> Unit,
) {
    Card(
        onClick = {
            navigateToPokemonDetail(pokemon.getPokemonId())
        }
    ) {
        Box {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .height(150.dp),
            ) {
                AsyncImage(
                    model = pokemon.getImageUrl(),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    pokemon.name,
                    textAlign = TextAlign.Center
                )
            }

            IconButton(
                onClick = { switchIsFavorite(pokemon) },
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Icon(
                    painter = painterResource(
                        id = if (pokemon.isFavorite) com.hanbitkang.core.designsystem.R.drawable.ic_baseline_favorite_24
                        else com.hanbitkang.core.designsystem.R.drawable.ic_baseline_favorite_border_24
                    ),
                    contentDescription = null
                )
            }
        }
    }
}