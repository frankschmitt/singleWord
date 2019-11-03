package de.qwhon.justOne

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull

@DataJpaTest
class RepositoriesTests @Autowired constructor(
        val entityManager: TestEntityManager,
        val entryRepository: EntryRepository) {

    @Test
    fun findByNameShouldFindEntryThatWasAddedPreviously() {
        val zufall = Entry("Zufall")
        entityManager.persist(zufall)
        entityManager.flush()
        val found = entryRepository.findByIdOrNull(zufall.id!!)
        assertThat(found).isEqualTo(zufall)
    }

}