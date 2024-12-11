package software.bottari.service

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.ExchangeFilterFunction
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

class CargoRequestTest {

    private val webClient = WebClient.builder()
        .filters { exchangeFilterFunctions ->
            exchangeFilterFunctions.add(ExchangeFilterFunction.ofRequestProcessor { clientRequest ->
                println("Request: ${clientRequest.method()} ${clientRequest.url()}")
                Mono.just(clientRequest)
            })
            exchangeFilterFunctions.add(ExchangeFilterFunction.ofResponseProcessor { clientResponse ->
                println("Response status: ${clientResponse.statusCode()}")
                Mono.just(clientResponse)
            })
        }
        .build()

    data class ApiResponse(
        val isSuccess: Boolean,
        val responseDto: Any?,
        val error: Any?
    )

    @Test
    @DisplayName("선박 물류 예약 테스트")
    fun `test cargo request response`() {
        // Given: Request data
        val url = "https://port-0-backend-rm6l2llw4xv3nr.sel5.cloudtype.app/user/sendfile/1"

        val requestData = mapOf(
            "num" to "Bottari to DTO Request Test ",
            "name" to "AAAAAAA",
            "cargo_quantity" to 100,
            "cargo_type" to "Container",
            "cargo_weight" to 500,
            "price" to 10000,
            "sender" to "Hong gildong",
            "receiver" to "AAAAAA",
            "shipScheduleId" to 1
        )

        // When: Sending POST request
        val response: ApiResponse? = webClient.post()
            .uri(url)
            .contentType(MediaType.APPLICATION_JSON)
            .body(Mono.just(requestData), Map::class.java)
            .retrieve()
            .bodyToMono(ApiResponse::class.java)
            .block()

        // Then: Check response
        println("Response: $response")
        assertTrue(response?.isSuccess == true) {
            "Expected success to be true, but was ${response?.isSuccess}"
        }
    }
}