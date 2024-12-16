package software.bottari.service

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.ExchangeFilterFunction
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientResponseException
import reactor.core.publisher.Mono
import kotlin.test.junit5.JUnit5Asserter.fail

class OrderRequestTest {

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

//    @Test
//    @DisplayName("주문 정보 테스트(더미) - 성공 케이스")
//    fun `test order dummy request response`() {
//        // Given: Request data
//        val url = "http://124.49.226.94:8080/message/send"
//
//        val requestData = mapOf(
//            "relay" to "LAND"
//        )
//
//        // When: Sending POST request
//        val responseString: String? = webClient.post()
//            .uri(url)
//            .contentType(MediaType.APPLICATION_JSON)
//            .body(Mono.just(requestData), Map::class.java)
//            .retrieve()
//            .bodyToMono(String::class.java)  // 응답을 String으로 받음
//            .block()
//
//        // Then: Check if the response contains "수신완료 메시지 전송 성공"
//        println("Response status: 200 OK")
//        println("Response: $responseString")
//
//        // 응답 메시지가 "수신완료 메시지 전송 성공"이면 성공으로 간주
//        if (responseString != null) {
//            assertTrue(responseString.contains("수신완료 메시지 전송 성공")) {
//                "Expected response to contain '수신완료 메시지 전송 성공', but was $responseString"
//            }
//        }
//    }


    @Test
    @DisplayName("주문 정보 테스트 - 성공 케이스")
    fun `test order request response`() {
        // Given: Request data
        val url = "http://124.49.226.94:8080/message/send"

        val requestData = mapOf(
            "relay" to "LAND",
            "order_info" to mapOf(
                "shipping_info" to mapOf(
                    "tracking_number" to "TRK987654",
                    "origin_address" to mapOf(
                        "country" to "KR",
                        "city" to "서울",
                        "detail" to "서울특별시 중구 필동로1길 30",
                        "postal_code" to "90001"
                    ),
                    "destination_address" to mapOf(
                        "destination_country" to "KR",
                        "city" to "고양",
                        "detail" to "경기도 고양시 일산동구 동국로 32",
                        "postal_code" to "04510"
                    ),
                    "sender" to mapOf(
                        "name" to "동국이",
                        "email" to "sender@example.com",
                        "phone" to "010-0000-0000",
                        "customs_id" to "SENDER123"
                    ),
                    "receiver" to mapOf(
                        "name" to "동국이",
                        "email" to "receiver@example.com",
                        "phone" to "010-0000-0000",
                        "customs_id" to "RECEIVER456"
                    )
                )
            )
        )

        // When: Sending POST request
        val responseString: String? = webClient.post()
            .uri(url)
            .contentType(MediaType.APPLICATION_JSON)
            .body(Mono.just(requestData), Map::class.java)
            .retrieve()
            .bodyToMono(String::class.java)  // 응답을 String으로 받음
            .block()

        // Then: Check if the response contains "수신완료 메시지 전송 성공"
        println("Response status: 200 OK")
        println("Response: $responseString")

        // 응답 메시지가 "수신완료 메시지 전송 성공"이면 성공으로 간주
        if (responseString != null) {
            assertTrue(responseString.contains("수신완료 메시지 전송 성공")) {
                "Expected response to contain '수신완료 메시지 전송 성공', but was $responseString"
            }
        }
    }

    @Test
    @DisplayName("주문 정보 테스트 - 필수 형식 누락 시 실패")
    fun `test order request with invalid data`() {
        // Given: Request data
        val url = "http://124.49.226.94:8080/message/send"

        val requestData = mapOf(
            "relay" to "",
            "order_info" to mapOf(
                "shipping_info" to mapOf(
                    "tracking_number" to "",
                    "origin_address" to mapOf(
                        "country" to "",
                        "city" to "",
                        "detail" to "",
                        "postal_code" to ""
                    ),
                    "destination_address" to mapOf(
                        "destination_country" to "",
                        "city" to "",
                        "detail" to "",
                        "postal_code" to ""
                    ),
                    "sender" to mapOf(
                        "name" to "",
                        "email" to "",
                        "phone" to "",
                        "customs_id" to ""
                    ),
                    "receiver" to mapOf(
                        "name" to "",
                        "email" to "",
                        "phone" to "",
                        "customs_id" to ""
                    )
                )
            )
        )

        try {
            // When: Sending POST request
            val response: CargoRequestTest.ApiResponse? = webClient.post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(requestData), Map::class.java)
                .retrieve()
                .bodyToMono(CargoRequestTest.ApiResponse::class.java)
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
    @DisplayName("주문 정보 테스트 - 잘못된 relay 기입 오류")
    fun `test1 order request with invalid data`() {
        // Given: Request data
        val url = "http://124.49.226.94:8080/message/send"

        val requestData = mapOf(
            "relay" to "LAAN",
            "order_info" to mapOf(
                "shipping_info" to mapOf(
                    "tracking_number" to "",
                    "origin_address" to mapOf(
                        "country" to "",
                        "city" to "",
                        "detail" to "",
                        "postal_code" to ""
                    ),
                    "destination_address" to mapOf(
                        "destination_country" to "",
                        "city" to "",
                        "detail" to "",
                        "postal_code" to ""
                    ),
                    "sender" to mapOf(
                        "name" to "",
                        "email" to "",
                        "phone" to "",
                        "customs_id" to ""
                    ),
                    "receiver" to mapOf(
                        "name" to "",
                        "email" to "",
                        "phone" to "",
                        "customs_id" to ""
                    )
                )
            )
        )

        try {
            // When: Sending POST request
            val response: CargoRequestTest.ApiResponse? = webClient.post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(requestData), Map::class.java)
                .retrieve()
                .bodyToMono(CargoRequestTest.ApiResponse::class.java)
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
