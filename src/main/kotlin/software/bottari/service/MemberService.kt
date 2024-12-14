package software.bottari.service

import software.bottari.dto.request.MemberLoginRequestDto
import software.bottari.dto.request.MemberSignUpRequestDto
import software.bottari.dto.response.MemberLoginResponseDto
import software.bottari.exception.ApiException
import software.bottari.exception.ErrorDefine
import lombok.RequiredArgsConstructor
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import software.bottari.repository.MemberRepository
import software.bottari.domain.Member


@Service
@RequiredArgsConstructor
class MemberService(
    private val memberRepository: MemberRepository,
    private val passwordEncoder: PasswordEncoder
) {

    fun login(memberLoginRequestDto: MemberLoginRequestDto): MemberLoginResponseDto {
        val member = memberRepository.findByEmail(memberLoginRequestDto.email)
            .orElseThrow { ApiException(ErrorDefine.USER_NOT_FOUND) }

        if (member != null && passwordEncoder.matches(memberLoginRequestDto.password, member.password)) {
            return MemberLoginResponseDto.of(member)
        } else {
            throw ApiException(ErrorDefine.INVALID_CREDENTIALS)
        }
    }


    fun signup(memberSignUpRequestDto: MemberSignUpRequestDto): Boolean {
        val existingMember = memberRepository.findByEmail(memberSignUpRequestDto.email)
        if (existingMember.isPresent) {
            throw ApiException(ErrorDefine.DUPLICATE_USER_EMAIL)
        }

        // 새로운 Member 엔터티 생성 및 저장
        val newMember = Member(
            email = memberSignUpRequestDto.email,
            password = passwordEncoder.encode(memberSignUpRequestDto.password),
            name = memberSignUpRequestDto.name,
            phoneNumber = memberSignUpRequestDto.phoneNumber
        )
        memberRepository.save(newMember)
        return true
    }
}
