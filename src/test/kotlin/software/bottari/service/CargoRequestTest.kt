package software.bottari.service

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.ExchangeFilterFunction
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientResponseException
import reactor.core.publisher.Mono
import kotlin.test.junit5.JUnit5Asserter.fail

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

    @Test
    @DisplayName("존재하지 않는 shipScheduleId로 요청 시 실패")
    fun `test cargo request with invalid shipScheduleId`() {
        // Given: Request data
        val url = "https://port-0-backend-rm6l2llw4xv3nr.sel5.cloudtype.app/user/sendfile/1"
        val requestData = mapOf(
            "num" to "Bottari to DTO Request Test",
            "name" to "AAAAAAA",
            "cargo_quantity" to 100,
            "cargo_type" to "Container",
            "cargo_weight" to 500,
            "price" to 10000,
            "sender" to "Hong gildong",
            "receiver" to "AAAAAA",
            "shipScheduleId" to 999 // 존재하지 않는 ID
        )

        try {
            // When: Sending POST request
            val response: ApiResponse? = webClient.post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(requestData), Map::class.java)
                .retrieve()
                .bodyToMono(ApiResponse::class.java)
                .block()

            // Then: Check response
            // 예상치 못한 성공 응답이 온 경우 테스트 실패
            fail("Expected BadRequest, but received response: $response")
        } catch (ex: WebClientResponseException.BadRequest) {
            // 예상한 400 Bad Request 예외 -> 테스트 성공
            println("Expected exception occurred: ${ex.message}")
            assertTrue(true)
        }
    }

    @Test
    @DisplayName("필수 필드 누락 시 실패")
    fun `test cargo request with missing required fields`() {
        // Given: Request data
        val url = "https://port-0-backend-rm6l2llw4xv3nr.sel5.cloudtype.app/user/sendfile/1"
        val requestData = mapOf(
            "num" to "Bottari to DTO Request Test",
            // name 필드 누락
            "cargo_quantity" to 100,
            "cargo_type" to "Container",
            "cargo_weight" to 500,
            "price" to 10000,
            "sender" to "Hong gildong",
            "receiver" to "AAAAAA",
            "shipScheduleId" to 1
        )


        try {
            // When: Sending POST request
            val response: ApiResponse? = webClient.post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(requestData), Map::class.java)
                .retrieve()
                .bodyToMono(ApiResponse::class.java)
                .block()
            // Then: Check response
            // 예상치 못한 성공 응답이 온 경우 테스트 실패
            fail("Expected BadRequest, but received response: $response")
        } catch (ex: WebClientResponseException.BadRequest) {
            // 예상한 400 Bad Request 예외 -> 테스트 성공
            println("Expected exception occurred: ${ex.message}")
            assertTrue(true)
        }
    }

    @Test
    @DisplayName("잘못된 데이터 타입(숫자여야 하는데 문자열 전송)으로 요청 시 실패")
    fun `test cargo request with invalid data types`() {
        // Given: Request data
        val url = "https://port-0-backend-rm6l2llw4xv3nr.sel5.cloudtype.app/user/sendfile/1"
        val requestData = mapOf(
            "num" to "Bottari to DTO Request Test",
            "name" to "AAAAAAA",
            "cargo_quantity" to "invalid_number", // 숫자여야 하는데 문자열 전송
            "cargo_type" to "Container",
            "cargo_weight" to 500,
            "price" to 10000,
            "sender" to "Hong gildong",
            "receiver" to "AAAAAA",
            "shipScheduleId" to 1
        )


        try {
            // When: Sending POST request
            val response: ApiResponse? = webClient.post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(requestData), Map::class.java)
                .retrieve()
                .bodyToMono(ApiResponse::class.java)
                .block()
            // Then: Check response
            // 예상치 못한 성공 응답이 온 경우 테스트 실패
            fail("Expected BadRequest, but received response: $response")
        } catch (ex: WebClientResponseException.BadRequest) {
            // 예상한 400 Bad Request 예외 -> 테스트 성공
            println("Expected exception occurred: ${ex.message}")
            assertTrue(true)
        }
    }
}
