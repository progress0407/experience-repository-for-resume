package io.philo.resume

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ResumeApplication

fun main(args: Array<String>) {
	runApplication<ResumeApplication>(*args)
}
