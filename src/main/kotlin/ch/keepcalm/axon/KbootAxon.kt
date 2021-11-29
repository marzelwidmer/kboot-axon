package ch.keepcalm.axon

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.*

@SpringBootApplication
class KbootAxon

fun main(args: Array<String>) {
	runApplication<KbootAxon>(*args)
	println(UUID.randomUUID())
}
