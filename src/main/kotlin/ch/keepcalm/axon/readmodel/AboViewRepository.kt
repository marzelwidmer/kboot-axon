package ch.keepcalm.axon.readmodel

import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface AboViewRepository : MongoRepository<AboView, UUID>
