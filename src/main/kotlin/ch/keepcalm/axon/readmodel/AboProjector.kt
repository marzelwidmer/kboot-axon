package ch.keepcalm.axon.readmodel

import FindAboQuery
import ch.keepcalm.axon.coreapi.AboCreatedEvent
import ch.keepcalm.axon.coreapi.RecipeSelectedEvent
import org.axonframework.eventhandling.EventHandler
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component
import java.util.*

@Component
class AboProjector(val repository: AboViewRepository) {

    @QueryHandler
    fun handle(query: FindAboQuery): AboView? {
        return repository.findById(query.aboId).orElse(null)
    }

    @EventHandler
    fun handle(event: AboCreatedEvent) {
        val aboView = AboView(
            id = event.aboId, eMail = event.eMail, startDatum = event.startDatum, endeDatum = event.endDatum, null)
        repository.save(aboView)
    }

    @EventHandler
    fun handle(event: RecipeSelectedEvent) {
        repository.findById(event.aboId).ifPresent { aboView ->
            aboView.recipe = event.recipe
        }
    }

//    @EventHandler
//    fun handle(event: AboCanceledEvent) {
//        repository.findById(event.aboId).ifPresent { aboView ->
//            aboView.endeDatum = event.endeDatum
//        }
//    }

}

//@Component
//class AboProjector (val repository: AboViewRepository){
//
//    @QueryHandler
//    fun handle(query: FindAboQuery) : AboView {
//        return repository.findById(query.aboId).orElse(null)
//    }
//
//    @EventSourcingHandler
//    fun on(event: AboCreatedEvent){
//    	AboView(eMail = event.eMail, startDatum = event.startDatum, endDatum = event.endDatum).run {
//            repository.save(this)
//        }
//    }
//
//    @EventSourcingHandler
//    fun on(event: RecipeSelectedEvent){
//        repository.findById(event.aboId).ifPresent { aboView ->
//            aboView.recipe = event.recipe
//        }
//    }
//}

