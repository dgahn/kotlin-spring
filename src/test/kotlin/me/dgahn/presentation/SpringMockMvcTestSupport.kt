package me.dgahn.presentation

import io.kotest.matchers.shouldBe
import me.dgahn.SpringTestSupport
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@AutoConfigureMockMvc
abstract class SpringMockMvcTestSupport : SpringTestSupport() {

    @Autowired
    protected lateinit var mockMvc: MockMvc

    protected fun mockMvcPostTest(
        uri: String,
        requestContent: String,
        responseContent: String,
        status: HttpStatus = HttpStatus.CREATED
    ) {
        mockMvc.perform(
            MockMvcRequestBuilders
                .post(uri)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestContent)
        )
            .andExpect { result -> result.response.status shouldBe status.value() }
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(MockMvcResultMatchers.content().string(responseContent))
    }
}
