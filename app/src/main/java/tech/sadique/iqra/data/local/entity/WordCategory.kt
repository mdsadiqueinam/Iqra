package tech.sadique.iqra.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_categories")
data class WordCategory(
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String
) {
    companion object {
        const val FOOD = "FOOD"
        const val ANIMAL = "ANIMAL"
        const val INSECT = "INSECT"
        const val NATURE = "NATURE"
        const val BODY_PART = "BODY_PART"
        const val PEOPLE = "PEOPLE"
        const val CLOTHING = "CLOTHING"
        const val PLACE = "PLACE"
        const val TIME = "TIME"
        const val COLOR = "COLOR"
        const val PLANT = "PLANT"
        const val ASTRONOMY = "ASTRONOMY"
        const val GENERAL = "GENERAL"

        val DEFAULT_IDS: List<String> = listOf(
            FOOD,
            ANIMAL,
            INSECT,
            NATURE,
            BODY_PART,
            PEOPLE,
            CLOTHING,
            PLACE,
            TIME,
            COLOR,
            PLANT,
            ASTRONOMY,
            GENERAL
        )

        val DEFAULT_WORD_CATEGORIES: List<WordCategory> = listOf(
            WordCategory(FOOD, "Food", "Food, drinks, and edibles"),
            WordCategory(ANIMAL, "Animal", "Animals, birds, and creatures"),
            WordCategory(INSECT, "Insect", "Insects and creeping creatures"),
            WordCategory(NATURE, "Nature", "Natural phenomena, weather, and landscapes"),
            WordCategory(BODY_PART, "Body Part", "Human and animal anatomy"),
            WordCategory(PEOPLE, "People", "Kinship, roles, and human groups"),
            WordCategory(CLOTHING, "Clothing", "Garments and adornments"),
            WordCategory(PLACE, "Place", "Locations, dwellings, and landmarks"),
            WordCategory(TIME, "Time", "Temporal periods, days, and seasons"),
            WordCategory(COLOR, "Color", "Colors and hues"),
            WordCategory(PLANT, "Plant", "Trees, crops, and vegetation"),
            WordCategory(ASTRONOMY, "Astronomy", "Celestial bodies like sun, moon, and stars"),
            WordCategory(GENERAL, "General", "General or unclassified vocabulary")
        )
    }
}
