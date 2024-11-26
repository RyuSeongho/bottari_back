package controller

import dto.request.UserLoginRequestDto
import dto.request.UserSignUpRequestDto
import dto.response.ResponseDto
import dto.response.UserLoginResponseDto
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import service.UserService

@RequiredArgsConstructor
@RestController
class UserController(
    private val userService: UserService
) {

    @PostMapping("/login")
    fun login(@RequestBody userLoginRequestDto: UserLoginRequestDto): ResponseDto<UserLoginResponseDto> {
        return ResponseDto(userService.login(userLoginRequestDto))
    }

    @PostMapping("/signup")
    fun signup(@RequestBody userSignUpRequestDto: UserSignUpRequestDto): ResponseDto<Boolean> {
        return ResponseDto(userService.signup(userSignUpRequestDto))
    }
}