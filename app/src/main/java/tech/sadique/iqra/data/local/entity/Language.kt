package tech.sadique.iqra.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "languages")
data class Language(
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String
) {
    companion object {
        const val EN = "EN"
        const val UR = "UR"

        val DEFAULT_IDS: List<String> = listOf(
            EN,
            UR
        )

        val DEFAULT_LANGUAGES: List<Language> = listOf(
            Language(EN, "English", "English language"),
            Language(UR, "Urdu", "Urdu language")
        )
    }
}
