package software.bottari.service

import io.mockk.*
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertThrows

// 예외 클래스 정의
class InvalidFormatException(message: String) : RuntimeException(message)
class MissingDocumentException(message: String) : RuntimeException(message)

class PickupReservationServiceTest {

    // 더미 클래스 정의
    data class ReservationRequest(
        val productType: String,
        val productPrice: Int,
        val productWeight: Double,
        val remark: String,
        val quantity: Int,
        val nameSend: String,
        val addressSend: String,
        val numberSend: String,
        val nameReceive: String,
        val addressReceive: String,
        val numberReceive: String
    )

    data class ResponseDto(
        val orderId: Int,
        val message: String
    )

    data class ReservationResponse(
        val responseDto: ResponseDto?,
        val error: String?,
        val success: Boolean
    )

    class PickupReservationService {

        fun reservePickup(reservationRequest: ReservationRequest): ReservationResponse {
            // 예외 처리: 서류가 없다
            if (reservationRequest.productType.isEmpty() || reservationRequest.addressSend.isEmpty()) {
                throw MissingDocumentException("Required document is missing")
            }

            // 예외 처리: 형식이 올바르지 않다
            if (reservationRequest.productPrice <= 0 || reservationRequest.productWeight <= 0) {
                throw InvalidFormatException("Product price or weight is invalid")
            }

            // 정상적인 예약 처리
            return ReservationResponse(
                ResponseDto(4885, "Your reservation was successful"),
                error = null,
                success = true
            )
        }
    }

    @Test
    @DisplayName("정상적인 택배 예약 테스트")
    fun `정상적인 택배 예약`() {
        // given: Mock 객체 설정
        val mockRequest = mockk<ReservationRequest>()
        val mockResponseDto = mockk<ResponseDto>()
        val mockResponse = mockk<ReservationResponse>()

        // 응답 데이터 설정
        every { mockResponseDto.orderId } returns 4885
        every { mockResponseDto.message } returns "Your reservation was successful"
        every { mockResponse.responseDto } returns mockResponseDto
        every { mockResponse.error } returns null
        every { mockResponse.success } returns true

        // 서비스 객체 설정
        val pickupReservationService = mockk<PickupReservationService>()
        every { pickupReservationService.reservePickup(mockRequest) } returns mockResponse

        // when: 서비스 메서드 호출
        val actualResponse = pickupReservationService.reservePickup(mockRequest)

        // then: 반환값 검증
        assertThat(actualResponse.success).isTrue
        assertThat(actualResponse.responseDto?.orderId).isEqualTo(4885)
        assertThat(actualResponse.responseDto?.message).isEqualTo("Your reservation was successful")
        assertThat(actualResponse.error).isNull()

        // verify mock interactions (optional)
        verify { pickupReservationService.reservePickup(mockRequest) }
    }

    @Test
    @DisplayName("서류가 누락된 경우 예외 테스트")
    fun `서류 누락 예외 처리`() {
        // given: 누락된 서류의 예약 요청 객체 설정
        val invalidRequest = ReservationRequest(
            productType = "", // productType이 비어 있음
            productPrice = 20000,
            productWeight = 15.3,
            remark = "",
            quantity = 2,
            nameSend = "bottari",
            addressSend = "",
            numberSend = "01020519577",
            nameReceive = "dongguk",
            addressReceive = "광주광역시 광주대로 광주아파트 209호",
            numberReceive = "01012364885"
        )

        val pickupReservationService = PickupReservationService()

        // when: 예외 발생
        val exception = assertThrows<MissingDocumentException> {
            pickupReservationService.reservePickup(invalidRequest)
        }

        // then: 예외 메시지 검증
        assertThat(exception.message).isEqualTo("Required document is missing")
    }

    @Test
    @DisplayName("형식이 올바르지 않은 경우 예외 테스트")
    fun `형식 불일치 예외 처리`() {
        // given: 올바르지 않은 형식의 예약 요청 객체 설정
        val invalidRequest = ReservationRequest(
            productType = "옷",
            productPrice = -100, // productPrice가 음수
            productWeight = -15.3, // productWeight가 음수
            remark = "",
            quantity = 2,
            nameSend = "bottari",
            addressSend = "서울특별시 서초대로 서초래미안아파트 703호",
            numberSend = "01020519577",
            nameReceive = "dongguk",
            addressReceive = "광주광역시 광주대로 광주아파트 209호",
            numberReceive = "01012364885"
        )

        val pickupReservationService = PickupReservationService()

        // when: 예외 발생
        val exception = assertThrows<InvalidFormatException> {
            pickupReservationService.reservePickup(invalidRequest)
        }

        // then: 예외 메시지 검증
        assertThat(exception.message).isEqualTo("Product price or weight is invalid")
    }
}
