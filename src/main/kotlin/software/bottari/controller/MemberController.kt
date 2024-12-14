package software.bottari.controller

import software.bottari.dto.request.MemberLoginRequestDto
import software.bottari.dto.request.MemberSignUpRequestDto
import software.bottari.dto.response.ResponseDto
import software.bottari.dto.response.MemberLoginResponseDto
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import software.bottari.service.MemberService

@RequiredArgsConstructor
@RestController
class MemberController(
    private val memberService: MemberService
) {

    @PostMapping("/login")
    fun login(@RequestBody memberLoginRequestDto: MemberLoginRequestDto): ResponseDto<MemberLoginResponseDto> {
        return ResponseDto(memberService.login(memberLoginRequestDto))
    }

    @PostMapping("/signup")
    fun signup(@RequestBody userSignUpRequestDto: MemberSignUpRequestDto): ResponseDto<Boolean> {
        return ResponseDto(memberService.signup(userSignUpRequestDto))
    }
}