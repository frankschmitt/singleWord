package de.qwhon.justOne

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatterBuilder
import java.util.*

@Controller
class HtmlController(private val repository: EntryRepository) {

    var logger = LoggerFactory.getLogger(HtmlController::class.java)

    @GetMapping("/")
    fun allEntries(model: Model): String {
        model["title"] = "All entries" // shorthand for model.addAttribute("title", "Blog")
        val allEntries = repository.findAllByOrderByNameAsc()
        logger.info("found ${allEntries.count()} entries")
        model["entries"] = allEntries.map { it.render() }
        return "allEntries" // name of the template we want to use for rendering
    }

    @GetMapping("/entry/{id}")
    fun entry(@PathVariable id: Long, model: Model): String {
        val entry : Optional<Entry> = repository.findById(id)
        val rendered = when (entry.isPresent) {
            true -> entry.get().render()
            false -> RenderedEntry(-1, "<n/a>", "n/a")
        }
        model["title"] = "Just one entry"
        model["name"] = rendered.name
        model["addedAt"] = rendered.addedAt
        return "justOne"
    }

    @GetMapping("/randomEntry")
    fun entry(model: Model): String {
        val allEntries = repository.findAllByOrderByNameAsc().shuffled()
        val entry = allEntries.firstOrNull()
        val rendered = when (entry) {
            null -> RenderedEntry(-1, "<n/a>", "n/a")
            else -> entry.render()
        }
        model["title"] = "Random entry"
        model["name"] = rendered.name
        model["addedAt"] = rendered.addedAt
        return "justOne"
    }

 }

fun Entry.render() = RenderedEntry(
        id ?: -1,
        name,
        addedAt.format()
)

private fun LocalDateTime.format(): String {
  val formatter =  DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd")
          .toFormatter(Locale.ENGLISH)
   return this.format(formatter)
}

data class RenderedEntry(
        val id: Long,
        val name: String,
        val addedAt: String)
