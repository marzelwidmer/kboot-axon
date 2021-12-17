package ch.keepcalm.axon.readmodel

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate
import java.util.*

@Document
class AboView(
    @Id val id: UUID,
    var eMail: String,
    var startDatum: LocalDate,
    var endeDatum: LocalDate?,
    var recipe: String?
)
