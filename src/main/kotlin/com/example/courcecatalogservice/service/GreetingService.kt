package com.example.courcecatalogservice.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class GreetingService {

    @Value("\${message}")
    lateinit var message: String

    fun greet(name: String): String {
        return "Hello, $name, $message"
    }
}