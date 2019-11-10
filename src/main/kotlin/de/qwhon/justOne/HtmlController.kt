package de.qwhon.justOne

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import java.time.LocalDateTime
import java.time.format.DateTimeFormatterBuilder
import java.util.*

@Controller
class HtmlController(private val repository: EntryRepository) {

    var logger = LoggerFactory.getLogger(HtmlController::class.java)

    @GetMapping("/")
    fun entry(model: Model): String {
        model["title"] = "All entries" // shorthand for model.addAttribute("title", "Blog")
        val allEntries = repository.findAllByOrderByNameAsc()
        logger.info("found ${allEntries.count()} entries")
        model["entries"] = allEntries.map { it.render() }
        return "allEntries" // name of the template we want to use for rendering
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
