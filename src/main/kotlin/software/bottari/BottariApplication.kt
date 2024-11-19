package software.bottari

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BottariApplication

fun main(args: Array<String>) {
	runApplication<BottariApplication>(*args)
}
