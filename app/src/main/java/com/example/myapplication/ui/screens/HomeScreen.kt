package com.example.myapplication.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.model.Place
import com.example.myapplication.ui.components.PlaceCard
import kotlinx.serialization.Serializable

@Serializable
object Home
@Composable
fun HomeScreen() {
    val placeList = rememberSaveable(
        saver = listSaver(
            save = { it.toList() },
            restore = { mutableStateListOf(*it.toTypedArray()) }
        )
    ) {
        mutableStateListOf(
            Place(1, "Il Mheg", "Norvrandt", R.drawable.il_mheg, "Tucked away in the mountains, this enchanted land was once home to the Kingdom of Voeburt. When men abandoned it following the Flood of Light, it instead became the home of the fae folk, the playful and childlike pixies chief among them."),
            Place(2, "Doma", "Yanxia", R.drawable.doma, "Upon the eastern banks of Othard lies Doma, the lands from which the ninja and samurai hail. Invaded twenty-five years ago by the Garlean Empire in their relentless effort to expand their borders, this once-peaceful nation now struggles under the imperial yoke. In the wake of a recent large-scale uprising that ended in failure, a majority of the civilians have been stripped of even their most fundamental freedoms."),
            Place(3, "Ishgard", "Cortheas", R.drawable.ishgard, "To Ishgard, shining city on the mount, overlooking the dominion of Coerthas...\n" +
                    "\n" +
                    "A great and proud nation devoted to Halone, the Fury, ruled by Thordan VII, Archbishop of the Ishgardian Orthodox Church...\n" +
                    "\n" +
                    "The last bastion of the faith, her walls ever bristling with the swords and spears of her four High Houses..."),
            Place(4, "Limsa Lominsa", "La Noscea", R.drawable.limsa_lominsa, "On a windswept isle in the southwestern corner of the realm, amidst the roiling waves of the Rhotano Sea, lies the maritime city-state of Limsa Lominsa. To this haven for bandits and brigands, cutthroats and curs, seekers of both freedom and fortune, comes a lone adventurer. Lone, yet not alone, for the hero's arrival has drawn the gaze of the nation's patron deity Llymlaen. What realm-shaking fate has She descried in the churning waters of this mortal's future? Through peril and hardship, discovery and triumph, may the Navigator guide this brave soul on [his/her] life's voyage... Till sea swallows all."),
            Place(5, "Radz-at-Han", "Thavnair", R.drawable.radz_at_han, "This bustling city on the Near Eastern isle of Thavnair is renowned as a prosperous trading hub and the birthplace of alchemy. Over the course of its storied history, it has produced countless influential figures and ingenious creations that have since made their ways to distant shores.\n" +
                    "Perched atop a massive pillar of rock, its ornate buildings boast vibrant hues rivaled only by those of its locally produced textiles."),
        )
    }

    val lastId by remember {
        derivedStateOf {
            placeList.lastOrNull()?.id ?: 0
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        LazyVerticalGrid(columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            content = {
                items(placeList.size) { index ->
                    PlaceCard(placeList[index], onOpenDetail = {})
                }
            }
        )
    }
}
