package com.paulograbin.oeditor

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class OeditorApplication

fun main(args: Array<String>) {
    SpringApplication.run(OeditorApplication::class.java, *args)
}
