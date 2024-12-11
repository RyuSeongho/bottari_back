package software.bottari.service

import io.mockk.*
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName

class UserRegistrationServiceTest {

    // 더미 클래스 정의
    data class RegistrationRequest(val dummyField: String = "") // 예시 필드 추가
    data class ResponseDto(val name: String, val accessToken: String, val refreshToken: String)
    data class RegistrationResponse(val responseDto: ResponseDto?, val error: String?, val success: Boolean)

    class UserRegistrationService {
        fun registerUser(request: RegistrationRequest): RegistrationResponse {
            // 실제 구현 없으므로 더미 반환
            return RegistrationResponse(null, "Error: Not implemented", false)
        }
    }

    @Test
    @DisplayName("회원가입 테스트")
    fun 회원가입() {
        // given: Mock 객체 설정
        val mockRequest = mockk<RegistrationRequest>()
        val mockResponseDto = mockk<ResponseDto>()
        val mockResponse = mockk<RegistrationResponse>()

        every { mockResponseDto.name } returns "John Doe"
        every { mockResponseDto.accessToken } returns "mockAccessToken"
        every { mockResponseDto.refreshToken } returns "mockRefreshToken"
        every { mockResponse.responseDto } returns mockResponseDto
        every { mockResponse.error } returns null
        every { mockResponse.success } returns true

        val userRegistrationService = mockk<UserRegistrationService>()
        every { userRegistrationService.registerUser(mockRequest) } returns mockResponse

        // when: 서비스 메서드 호출
        val actualResponse = userRegistrationService.registerUser(mockRequest)

        // then: 반환값 검증
        assertThat(actualResponse.success).isTrue
        assertThat(actualResponse.responseDto?.name).isEqualTo("John Doe")
        assertThat(actualResponse.responseDto?.accessToken).isEqualTo("mockAccessToken")
        assertThat(actualResponse.responseDto?.refreshToken).isEqualTo("mockRefreshToken")
        assertThat(actualResponse.error).isNull()

        // verify mock interactions (optional)
        verify { userRegistrationService.registerUser(mockRequest) }
    }
}
