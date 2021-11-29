package ch.keepcalm.axon.api

import ch.keepcalm.axon.coreapi.CreateAboCommand
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.queryhandling.QueryGateway
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.util.*
import java.util.concurrent.CompletableFuture


@RestController
@RequestMapping("/abos")
class AboRestApi(val commandGateway: CommandGateway, val queryGateway: QueryGateway) {

    @PutMapping(value = ["/{aboId}"])
    fun creatAbo(@PathVariable aboId: String, @RequestBody payload: CreateAboPayload): CompletableFuture<UUID> {
        return commandGateway.send(
            CreateAboCommand(aboId = UUID.fromString(aboId), eMail = payload.eMail, startDatum = payload.startDatum, endDatum = payload.endDatum)
        )
    }
}


data class CreateAboPayload(val eMail: String, val startDatum: LocalDate, val endDatum: LocalDate?)


