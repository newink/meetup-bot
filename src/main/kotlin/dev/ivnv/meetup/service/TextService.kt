package dev.ivnv.meetup.service

import dev.ivnv.meetup.database.Lang
import freemarker.template.Configuration
import java.io.StringWriter

enum class TemplateKey(val templateName: String) {
    REGISTRATION_SUCCESSFUL("registration-successful"), ERROR("error")
}

class TextService(private val freemarkerConfig: Configuration) {

    fun render(langKey: Lang, key: TemplateKey): String {
        val template = freemarkerConfig.getTemplate("$langKey/${key.templateName}.md")
        val stringWriter = StringWriter()
        template.process(mapOf<String, String>(), stringWriter)
        return stringWriter.toString()
    }
}

