package de.qwhon.justOne

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import java.time.LocalDateTime
import java.time.format.DateTimeFormatterBuilder
import java.util.*

@Controller
class HtmlController(private val repository: EntryRepository) {

    @GetMapping("/")
    fun entry(model: Model): String {
        model["title"] = "All entries" // shorthand for model.addAttribute("title", "Blog")
        model["entries"] = repository.findAllByOrderByNameAsc().map { it.render() }
        return "justOne" // name of the template we want to use for rendering
    }

 }

fun Entry.render() = RenderedEntry(
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
        val name: String,
        val addedAt: String)
