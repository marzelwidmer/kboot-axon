package ch.keepcalm.axon.coreapi

import org.axonframework.commandhandling.RoutingKey
import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.time.LocalDate
import java.util.*

data class CreateAboCommand(
    @RoutingKey val aboId: UUID,
    val eMail: String,
    val startDatum: LocalDate,
    val endDatum: LocalDate?
)

data class SelectedRecipeCommand(
    @TargetAggregateIdentifier val aboId: UUID,
    val recipe: String
)

data class CancelAboCommand(@TargetAggregateIdentifier val aboId: UUID)
