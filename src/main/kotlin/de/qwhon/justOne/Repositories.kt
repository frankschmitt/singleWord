package de.qwhon.justOne

import org.springframework.data.repository.CrudRepository

interface EntryRepository : CrudRepository<Entry, Long> {
    fun findByName(name: String): Entry?
    fun findAllByOrderByNameAsc(): Iterable<Entry>
}
