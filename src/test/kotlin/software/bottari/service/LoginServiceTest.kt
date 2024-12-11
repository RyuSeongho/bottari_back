package software.bottari.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*

class LoginServiceTest {

    // 더미 클래스들
    class LoginRequest(val username: String, val password: String)
    class LoginResponse(val accessToken: String?, val error: String?, val success: Boolean)

    class LoginService {
        fun loginUser(request: LoginRequest): LoginResponse {
            // 실제 로그인 로직은 여기에서 처리됩니다.
            return if (request.username == "validUser" && request.password == "validPassword") {
                LoginResponse("mockAccessToken", null, true)
            } else {
                LoginResponse(null, "Invalid credentials", false)
            }
        }
    }


    // Mock 객체 생성
    private val loginService = mock(LoginService::class.java)
    private val mockRequest = mock(LoginRequest::class.java)
    private val mockResponse = mock(LoginResponse::class.java)

    @Test
    @DisplayName("로그인 성공")
    fun 로그인_성공() {
        // given: Mock 객체의 동작 설정
        `when`(mockRequest.username).thenReturn("validUser")
        `when`(mockRequest.password).thenReturn("validPassword")
        `when`(loginService.loginUser(mockRequest)).thenReturn(LoginResponse("mockAccessToken", null, true))

        // when: 서비스 메서드 호출
        val actualResponse = loginService.loginUser(mockRequest)

        // then: 반환값 검증
        assertThat(actualResponse.success).isTrue
        assertThat(actualResponse.accessToken).isEqualTo("mockAccessToken")
        assertThat(actualResponse.error).isNull()
    }

    @Test
    @DisplayName("로그인 인증 실패")
    fun 로그인_인증_실패_() {
        // given: Mock 객체의 동작 설정
        `when`(mockRequest.username).thenReturn("invalidUser")
        `when`(mockRequest.password).thenReturn("invalidPassword")
        `when`(loginService.loginUser(mockRequest)).thenReturn(LoginResponse(null, "Invalid credentials", false))

        // when: 서비스 메서드 호출
        val actualResponse = loginService.loginUser(mockRequest)

        // then: 반환값 검증
        assertThat(actualResponse.success).isFalse
        assertThat(actualResponse.error).isEqualTo("Invalid credentials")
    }
}
