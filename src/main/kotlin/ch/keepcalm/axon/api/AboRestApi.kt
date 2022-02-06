package ch.keepcalm.axon.api

import FindAboQuery
import ch.keepcalm.axon.coreapi.CreateAboCommand
import ch.keepcalm.axon.coreapi.SelectedRecipeCommand
import ch.keepcalm.axon.readmodel.AboView
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.messaging.responsetypes.ResponseTypes
import org.axonframework.queryhandling.QueryGateway
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.util.*
import java.util.concurrent.CompletableFuture

@RestController
@RequestMapping("/abos")
class AboRestApi(val commandGateway: CommandGateway, val queryGateway: QueryGateway) {

    @PostMapping(value = ["/create/{aboId}"])
    fun creatAbo(@PathVariable aboId: String, @RequestBody payload: CreateAboPayload): CompletableFuture<UUID> {
        return commandGateway.send(
            CreateAboCommand(
                aboId = UUID.fromString(aboId), eMail = payload.eMail, startDatum = payload.startDatum, endDatum = payload.endDatum
            )
        )
    }

    @PostMapping(value = ["/selectrecipe/{aboId}"])
    fun selectReceipt(@PathVariable aboId: String, @RequestBody payload: SelectReceiptPayload): CompletableFuture<UUID> {
        return commandGateway.send(
            SelectedRecipeCommand(
                aboId = UUID.fromString(aboId),
                recipe = payload.recipe
            )
        )
    }

    @GetMapping(value = ["/{aboId}"])
    fun getAbo(@PathVariable aboId: String): CompletableFuture<AboView>? {
        return queryGateway.query(FindAboQuery(aboId = UUID.fromString(aboId)),
            ResponseTypes.instanceOf(AboView::class.java)
        )
    }

}

data class CreateAboPayload(val eMail: String, val startDatum: LocalDate, val endDatum: LocalDate?)
data class SelectReceiptPayload(val recipe: String)


