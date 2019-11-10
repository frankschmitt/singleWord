package de.qwhon.justOne

import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.util.ResourceUtils



@Configuration
class EntryConfiguration {

    @Bean
    fun databaseInitializer(entryRepository: EntryRepository) = ApplicationRunner {
        val file = ResourceUtils.getFile("classpath:static/entries.txt")
        file.forEachLine {
            entryRepository.save(Entry(it))
        }
    }
}