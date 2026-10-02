package tech.sadique.iqra.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_types")
data class WordType(
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String
) {
    companion object {
        const val ISM_SINGULAR = "ISM_SINGULAR"
        const val ISM_DUAL = "ISM_DUAL"
        const val ISM_PLURAL = "ISM_PLURAL"
        const val FIAL_FORM_I = "FIAL_FORM_I"
        const val FIAL_FORM_II = "FIAL_FORM_II"
        const val FIAL_FORM_III = "FIAL_FORM_III"
        const val FIAL_FORM_IV = "FIAL_FORM_IV"
        const val FIAL_FORM_V = "FIAL_FORM_V"
        const val FIAL_FORM_VI = "FIAL_FORM_VI"
        const val FIAL_FORM_VII = "FIAL_FORM_VII"
        const val FIAL_FORM_VIII = "FIAL_FORM_VIII"
        const val FIAL_FORM_IX = "FIAL_FORM_IX"
        const val FIAL_FORM_X = "FIAL_FORM_X"
        const val HURF = "HURF"

        val DEFAULT_IDS: List<String> = listOf(
            ISM_SINGULAR,
            ISM_DUAL,
            ISM_PLURAL,
            FIAL_FORM_I,
            FIAL_FORM_II,
            FIAL_FORM_III,
            FIAL_FORM_IV,
            FIAL_FORM_V,
            FIAL_FORM_VI,
            FIAL_FORM_VII,
            FIAL_FORM_VIII,
            FIAL_FORM_IX,
            FIAL_FORM_X,
            HURF
        )

        val DEFAULT_WORD_TYPES: List<WordType> = listOf(
            WordType(ISM_SINGULAR, "Ism Singular", "Singular noun or adjective (Mufrad)"),
            WordType(ISM_DUAL, "Ism Dual", "Dual noun or adjective (Muthanna)"),
            WordType(ISM_PLURAL, "Ism Plural", "Plural noun or adjective (Jam')"),
            WordType(FIAL_FORM_I, "Fi'l Form I", "Basic triliteral verb (Fa'ala)"),
            WordType(FIAL_FORM_II, "Fi'l Form II", "Intensive or causative verb (Fa''ala)"),
            WordType(FIAL_FORM_III, "Fi'l Form III", "Reciprocal or associated verb (Fā'ala)"),
            WordType(FIAL_FORM_IV, "Fi'l Form IV", "Causative or transitive verb (Af'ala)"),
            WordType(FIAL_FORM_V, "Fi'l Form V", "Reflexive of Form II (Tafa''ala)"),
            WordType(FIAL_FORM_VI, "Fi'l Form VI", "Reflexive or reciprocal of Form III (Tafā'ala)"),
            WordType(FIAL_FORM_VII, "Fi'l Form VII", "Passive or reflexive verb (Infa'ala)"),
            WordType(FIAL_FORM_VIII, "Fi'l Form VIII", "Reflexive or participatory verb (Ifta'ala)"),
            WordType(FIAL_FORM_IX, "Fi'l Form IX", "Verb denoting color or defect (If'alla)"),
            WordType(FIAL_FORM_X, "Fi'l Form X", "Requesting or seeking verb (Istaf'ala)"),
            WordType(HURF, "Harf", "Particle, preposition, or conjunction")
        )
    }
}

