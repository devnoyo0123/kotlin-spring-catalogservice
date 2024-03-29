package com.example.courcecatalogservice.controller

import com.example.courcecatalogservice.service.GreetingService
import mu.KLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/greeting")
class GreetingController(val greetingService: GreetingService) {

    companion object : KLogging()

    @GetMapping("/{name}")
    fun retrieveGreeting(@PathVariable("name") name: String): String {
        logger.info("Name is $name")
        return greetingService.greet(name)
    }
}