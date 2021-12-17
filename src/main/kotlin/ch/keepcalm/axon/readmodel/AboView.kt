package ch.keepcalm.axon.readmodel

import java.time.LocalDate
import java.util.*
import javax.persistence.*

@Entity
class AboView(
    @Id val id: UUID,
    var eMail: String,
    var startDatum: LocalDate,
    var endeDatum: LocalDate?,
    var recipe: String?
)


