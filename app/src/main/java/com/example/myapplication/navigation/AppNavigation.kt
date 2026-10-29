package com.example.myapplication.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.myapplication.R
import com.example.myapplication.model.Place
import com.example.myapplication.ui.screens.HomeScreen
import com.example.myapplication.ui.screens.Home
import com.example.myapplication.ui.screens.DetailScreen
import com.example.myapplication.ui.screens.Detail
import com.example.myapplication.ui.screens.Edit
import com.example.myapplication.ui.screens.EditScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

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

    NavHost(
        navController = navController,
        startDestination = Home
    ) {
        composable<Home> { HomeScreen(
            placeList,
            onOpenDetail = { id ->
                navController.navigate(Detail(id = id))
            }
        ) }
        composable<Detail> {  entry ->
            val route = entry.toRoute<Detail>()
            val place = placeList.find { it.id == route.id }

            if (place != null) {
                DetailScreen(place, { navController.popBackStack() }, { navController.navigate(Edit(id = place.id)) })
            } else {
                Text("Item not found")
            }
        }
        composable<Edit> {  entry ->
            val route = entry.toRoute<Edit>()
            val place = placeList.find { it.id == route.id }

            if (place != null) {
                EditScreen(place, { navController.popBackStack() })
            } else {
                Text("Item not found")
            }
        }
    }
}