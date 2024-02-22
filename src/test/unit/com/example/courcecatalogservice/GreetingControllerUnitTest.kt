package com.example.courcecatalogservice

import com.example.courcecatalogservice.controller.GreetingController
import com.example.courcecatalogservice.service.GreetingService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.reactive.server.WebTestClient

@WebMvcTest(controllers = [GreetingController::class])
@AutoConfigureWebTestClient
class GreetingControllerUnitTest {
    @Autowired
    lateinit var webTestClient: WebTestClient

    @MockkBean
    lateinit var greetingServiceMock: GreetingService


    @Test
    fun retrieveGreeting() {
        val name = "yobs"

        every {
            greetingServiceMock.greet(any())
        } returns "Hello, $name, Hello from default profile"

        val ressult = webTestClient.get()
            .uri("/v1/greeting/{name}", name)
            .exchange()
            .expectStatus().isOk
            .expectBody(String::class.java)
            .returnResult()

        Assertions.assertEquals("Hello, $name, Hello from default profile", ressult.responseBody)
    }
}