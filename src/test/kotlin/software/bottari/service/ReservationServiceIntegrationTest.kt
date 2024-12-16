package software.bottari.service

import jakarta.transaction.Transactional
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import software.bottari.domain.Member
import software.bottari.dto.request.ReservationRequestDto
import software.bottari.repository.FreightRepository
import software.bottari.repository.MemberRepository
import kotlin.test.Test

@SpringBootTest
@Transactional
class ReservationServiceIntegrationTest(
    @Autowired private val reservationService: ReservationService,
    @Autowired private val freightRepository: FreightRepository,
    @Autowired private val memberRepository: MemberRepository
) {

    @BeforeEach
    fun setUp() {
        // Given: 테스트 데이터 준비
        val testMember = Member(id = 100L, name = "test", email = "john@example.com", password = "1234", phoneNumber = "010-1234-1234")
        memberRepository.save(testMember)
    }

    @AfterEach
    fun tearDown() {
        // Clean-up: 테스트 이후 데이터 정리
        freightRepository.deleteAll()
        memberRepository.deleteAll()
    }

    @Test
    fun `should create a domestic reservation successfully`() {
        // Given: 요청 DTO 준비
        val requestDto = ReservationRequestDto(
            productType = "옷",
            productPrice = 100L,
            productWeight = 5.0f,
            clearanceNumber = "CN123456",
            remark = "Handle with care",
            quantity = 1,
            senderName = "test",
            senderAddress = "1234 Main St",
            senderNumber = "123-456-7890",
            receiverName = "Anfield",
            receiverAddress = "5678 Elm St",
            receiverNumber = 98765,
            receiverCountry = "USA",
            receiverCity = "Los Angeles",
            receiverState = "CA",
            receiverZipcode = 90001,
            shippingStatus = "before",
            additionalDoc = "None"
        )

        // When: 서비스 호출
        val responseDto = reservationService.domestic(requestDto)

        // Then: 반환 메시지 검증
        assertNotNull(responseDto)
        val expectedMessage = "John Doe 님으로부터 Jane Smith 로의 화물 예약이 완료되었습니다."
        assertEquals(expectedMessage, responseDto.message)

        // 데이터 검증
        val savedFreight = freightRepository.findAll().firstOrNull()
        assertNotNull(savedFreight)
        savedFreight?.apply {
            assertEquals("옷", productType)
            assertEquals("test", senderName)
            assertEquals("Anfield", receiverName)
            assertEquals("before", shippingStatus)
        }
    }
}
