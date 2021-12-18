package ch.keepcalm.axon.coreapi

import java.time.LocalDate
import java.util.*

data class AboCreatedEvent(
    val aboId: UUID,
    val eMail: String,
    val startDatum: LocalDate, val endDatum: LocalDate?
)

data class RecipeSelectedEvent(
    val aboId: UUID,
    val recipe: String
)

data class AboCanceledEvent(
    var aboId: UUID,
    var endDatum: LocalDate
)
