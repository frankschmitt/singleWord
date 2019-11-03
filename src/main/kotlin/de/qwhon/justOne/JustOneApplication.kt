package de.qwhon.justOne

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class JustOneApplication

fun main(args: Array<String>) {
	runApplication<JustOneApplication>(*args) {
		// setBannerMode(Banner.Mode.OFF)
	}
}



