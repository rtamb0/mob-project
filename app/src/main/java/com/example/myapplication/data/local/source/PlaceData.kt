package com.example.myapplication.data.local.source

import com.example.myapplication.R
import com.example.myapplication.data.local.entity.PlaceEntity

object PlaceData {
    val initialPlaces = listOf(
        PlaceEntity(
            id = 1,
            name = "Il Mheg",
            location = "Norvrandt",
            imageId = R.drawable.il_mheg,
            description = "Tucked away in the mountains, this enchanted land was once home to the Kingdom of Voeburt. When men abandoned it following the Flood of Light, it instead became the home of the fae folk, the playful and childlike pixies chief among them."
        ),
        PlaceEntity(
            id = 2,
            name = "Doma",
            location = "Yanxia",
            imageId = R.drawable.doma,
            description = "Upon the eastern banks of Othard lies Doma, the lands from which the ninja and samurai hail. Invaded twenty-five years ago by the Garlean Empire in their relentless effort to expand their borders, this once-peaceful nation now struggles under the imperial yoke. In the wake of a recent large-scale uprising that ended in failure, a majority of the civilians have been stripped of even their most fundamental freedoms."
        ),
        PlaceEntity(
            id = 3,
            name = "Ishgard",
            location = "Cortheas",
            imageId = R.drawable.ishgard,
            description = "To Ishgard, shining city on the mount, overlooking the dominion of Coerthas...\n" +
                    "\n" +
                    "A great and proud nation devoted to Halone, the Fury, ruled by Thordan VII, Archbishop of the Ishgardian Orthodox Church...\n" +
                    "\n" +
                    "The last bastion of the faith, her walls ever bristling with the swords and spears of her four High Houses..."
        ),
        PlaceEntity(
            id = 4,
            name = "Limsa Lominsa",
            location = "La Noscea",
            imageId = R.drawable.limsa_lominsa,
            description = "On a windswept isle in the southwestern corner of the realm, amidst the roiling waves of the Rhotano Sea, lies the maritime city-state of Limsa Lominsa. To this haven for bandits and brigands, cutthroats and curs, seekers of both freedom and fortune, comes a lone adventurer. Lone, yet not alone, for the hero's arrival has drawn the gaze of the nation's patron deity Llymlaen. What realm-shaking fate has She descried in the churning waters of this mortal's future? Through peril and hardship, discovery and triumph, may the Navigator guide this brave soul on [his/her] life's voyage... Till sea swallows all."
        ),
        PlaceEntity(
            id = 5,
            name = "Radz-at-Han",
            location = "Thavnair",
            imageId = R.drawable.radz_at_han,
            description = "This bustling city on the Near Eastern isle of Thavnair is renowned as a prosperous trading hub and the birthplace of alchemy. Over the course of its storied history, it has produced countless influential figures and ingenious creations that have since made their ways to distant shores.\n" +
                    "Perched atop a massive pillar of rock, its ornate buildings boast vibrant hues rivaled only by those of its locally produced textiles."
        )
    )
}