package hr.unidu.kz.primjerliste

object Pomocna {
    fun ucitajPokemone(): List<Pokemon> {
        return mutableListOf(
            Pokemon("Abra", "Psihički", R.drawable.abra),
            Pokemon("Absol", "Mračni", R.drawable.absol),
            Pokemon("Alakazam", "Psihički", R.drawable.alakazam),
            Pokemon("Arbok", "Otrovni", R.drawable.arbok),
            Pokemon("Arcanine", "Vatreni", R.drawable.arcanine),
            Pokemon("Articuno", "Ledeni/Leteći", R.drawable.articuno),
            Pokemon("Bagon", "Zmajski", R.drawable.bagon),
            Pokemon("Bayleef", "Travni", R.drawable.bayleef),
            Pokemon("Beedrill", "Kukac/Otrovni", R.drawable.beedrill),
            Pokemon("Bellossom", "Travni", R.drawable.bellossom),
            Pokemon("Bellsprout", "Travni/Otrovni", R.drawable.bellsprout),
            Pokemon("Blastoise", "Vodeni", R.drawable.blastoise),
            Pokemon("Blaziken", "Vatreni/Borbeni", R.drawable.blaziken),
            Pokemon("Breloom", "Travni/Borbeni", R.drawable.breloom),
            Pokemon("Bulbasaur", "Travni/Otrovni", R.drawable.bulbasaur),
            Pokemon("Buneary", "Normalni", R.drawable.buneary),
            Pokemon("Butterfree", "Kukac/Leteći", R.drawable.butterfree),
            Pokemon("Cacnea", "Travni", R.drawable.cacnea),
            Pokemon("Cacturne", "Travni/Mračni", R.drawable.cacturne),
            Pokemon("Camerupt", "Vatreni/Zemljani", R.drawable.camerupt),
            Pokemon("Caterpie", "Kukac", R.drawable.caterpie),
            Pokemon("Celebi", "Psihički/Travni", R.drawable.celebi),
            Pokemon("Charizard", "Vatreni/Leteći", R.drawable.charizard),
            Pokemon("Charmander", "Vatreni", R.drawable.charmander),
            Pokemon("Charmeleon", "Vatreni", R.drawable.charmeleon)
        )
    }
    fun ucitajStringove(): List<String> {
        return listOf(
            "Abra", "Absol", "Alakazam", "Arbok", "Arcanine", "Articuno",
            "Bagon", "Bayleef", "Beedrill", "Bellossom", "Bellsprout",
            "Blastoise", "Blaziken", "Breloom", "Bulbasaur", "Buneary",
            "Butterfree", "Cacnea", "Cacturne", "Camerupt", "Caterpie",
            "Celebi", "Charizard", "Charmander", "Charmeleon"
        )
    }
}