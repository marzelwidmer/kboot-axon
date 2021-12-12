package ch.keepcalm.axon.readmodel

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface AboViewRepository : JpaRepository<AboView, UUID>
