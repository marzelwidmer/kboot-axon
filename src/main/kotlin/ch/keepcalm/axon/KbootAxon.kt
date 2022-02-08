package ch.keepcalm.axon

import com.mongodb.client.MongoClient
import com.thoughtworks.xstream.XStream
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore
import org.axonframework.eventsourcing.eventstore.EventStorageEngine
import org.axonframework.eventsourcing.eventstore.EventStore
import org.axonframework.extensions.mongo.DefaultMongoTemplate
import org.axonframework.extensions.mongo.eventsourcing.eventstore.MongoEventStorageEngine
import org.axonframework.serialization.xml.XStreamSerializer
import org.axonframework.spring.config.AxonConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean


@SpringBootApplication
class KbootAxon {
    @Bean
    fun eventStore(storageEngine: EventStorageEngine?, configuration: AxonConfiguration): EmbeddedEventStore? {
        return EmbeddedEventStore.builder()
            .storageEngine(storageEngine)
            .messageMonitor(configuration.messageMonitor(EventStore::class.java, "eventStore"))
            .build()
    }
    // The `MongoEventStorageEngine` stores each event in a separate MongoDB document
    @Bean
    fun storageEngine(client: MongoClient?): EventStorageEngine? {
        return MongoEventStorageEngine.builder()
            .eventSerializer(
                XStreamSerializer.builder()
                    .xStream(SecureXStreamSerializer.xStream())
                    .build()

            )
            .snapshotSerializer(
                XStreamSerializer.builder()
                    .xStream(SecureXStreamSerializer.xStream())
                    .build()
            )
            .mongoTemplate(
                DefaultMongoTemplate
                    .builder()
                    .mongoDatabase(client)
                    .build()
            ).build()
    }
}
object SecureXStreamSerializer {
    fun xStream(): XStream {
        val xStream = XStream()
        xStream.classLoader = SecureXStreamSerializer::class.java.classLoader
        xStream.allowTypesByWildcard(
            arrayOf(
                "org.axonframework.**",
                "**",
            )
        )
        return XStreamSerializer.builder().xStream(xStream).build().xStream
    }
}

fun main(args: Array<String>) {
    runApplication<KbootAxon>(*args)
}

