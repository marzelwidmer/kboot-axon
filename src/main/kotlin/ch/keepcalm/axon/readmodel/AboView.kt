package ch.keepcalm.axon.readmodel

import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate
import java.util.*
import javax.persistence.*
//
//@Entity
//class AboView(
//    @Id val aboId : UUID? = null,
//    val eMail: String,
//    val startDatum: LocalDate,
//    var endDatum: LocalDate? = null,
//    var recipe: String? = null
//) //: AbstractEntity()
//
//

@Entity
class AboView(
    @Id val id: UUID,
    var eMail: String,
    var startDatum: LocalDate,
    var endeDatum: LocalDate?,
    var recipe: String?
)


