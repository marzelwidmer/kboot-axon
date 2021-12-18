package ch.keepcalm.axon.command

import ch.keepcalm.axon.coreapi.*
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate
import java.time.LocalDate
import java.util.*

@Aggregate
class AboAggregate {

    @AggregateIdentifier
    private lateinit var aboId: UUID

    var eMail: String? = null
    var startDatum: LocalDate? = null
    var endDatum: LocalDate? = null
    var recipe: String? = null

    constructor() // Needed for AXON

    @CommandHandler
    constructor(command: CreateAboCommand) {
        AggregateLifecycle.apply(
            AboCreatedEvent(aboId = command.aboId, eMail = command.eMail, startDatum = command.startDatum, endDatum = command.endDatum)
        )
    }

    @CommandHandler
    fun handle(command: SelectedRecipeCommand){
        AggregateLifecycle.apply(
            RecipeSelectedEvent(aboId = command.aboId, recipe= command.recipe)
        )
    }

    @CommandHandler
    fun handle(command: CreateAboCommand){
        AggregateLifecycle.apply(
            AboCanceledEvent(aboId = command.aboId, endDatum = command.endDatum)
        )
    }

    @EventSourcingHandler
    fun on(event: AboCreatedEvent){
    	aboId = event.aboId
    	startDatum = event.startDatum
        endDatum = event.endDatum
    }

    @EventSourcingHandler
    fun on(event: RecipeSelectedEvent){
        recipe = event.recipe
    }

    @EventSourcingHandler
    fun on(event: CancelAboCommand){
    	endDatum = event.endDatum
    }
}
