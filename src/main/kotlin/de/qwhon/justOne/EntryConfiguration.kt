package de.qwhon.justOne

import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class EntryConfiguration {

    @Bean
    fun databaseInitializer(entryRepository: EntryRepository) = ApplicationRunner {

        val zufall = entryRepository.save(Entry("Zufall"))

    }
}