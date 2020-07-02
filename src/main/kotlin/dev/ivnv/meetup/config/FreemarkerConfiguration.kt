package dev.ivnv.meetup.config

import freemarker.template.Configuration
import freemarker.template.TemplateExceptionHandler

class FreemarkerConfiguration {

    fun configure(): Configuration {
        val cfg = Configuration(Configuration.VERSION_2_3_30)
        cfg.setClassLoaderForTemplateLoading(this.javaClass.classLoader, "templates")
        cfg.defaultEncoding = "UTF-8"
        cfg.templateExceptionHandler = TemplateExceptionHandler.RETHROW_HANDLER
        cfg.wrapUncheckedExceptions = true
        cfg.fallbackOnNullLoopVariable = false

        return cfg
    }
}
