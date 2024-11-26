package service

import dto.request.UserLoginRequestDto
import dto.request.UserSignUpRequestDto
import dto.response.UserLoginResponseDto
import exception.ApiException
import exception.ErrorDefine
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import repository.UserRepository

@Service
@RequiredArgsConstructor
class UserService(
    private val userRepository: UserRepository,
) {

    fun login(userLoginRequestDto: UserLoginRequestDto): UserLoginResponseDto {
        val user = userRepository.findByEmail(userLoginRequestDto.email)
            ?.orElseThrow { ApiException(ErrorDefine.USER_NOT_FOUND) }

        if (user?.password == userLoginRequestDto.password) {
            return UserLoginResponseDto.of(user)
        } else {
            throw ApiException(ErrorDefine.INVALID_CREDENTIALS)
        }
    }


    fun signup(userSignUpRequestDto: UserSignUpRequestDto): Boolean {
        val user = userRepository.findByEmail(userSignUpRequestDto.email)
            ?.orElseThrow { ApiException(ErrorDefine.DUPLICATE_USER_EMAIL) }

        if (user != null) {
            userRepository.save(user)
        }
        return true
    }
}
