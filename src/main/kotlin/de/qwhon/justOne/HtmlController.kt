package de.qwhon.justOne

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HtmlController {

    @GetMapping("/")
    fun word(model: Model): String {
        model["title"] = "Just One" // shorthand for model.addAttribute("title", "Blog")
        return "justOne" // name of the template we want to use for rendering
    }

}