package com.hanbitkang.core.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.hanbitkang.core.data.model.Pokemon

@Composable
fun PokemonList(
    modifier: Modifier = Modifier,
    pokemons: List<Pokemon>,
    navigateToPokemonDetail: (String) -> Unit,
    onScrollBottom: () -> Unit = {}
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(pokemons) {
            PokemonCard(
                pokemon = it,
                navigateToPokemonDetail = navigateToPokemonDetail
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
    navigateToPokemonDetail: (String) -> Unit,
) {
    Card(
        onClick = {
            navigateToPokemonDetail(pokemon.getPokemonId())
        }
    ) {
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
    }
}