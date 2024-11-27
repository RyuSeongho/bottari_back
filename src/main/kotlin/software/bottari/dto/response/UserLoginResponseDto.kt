package software.bottari.dto.response

import software.bottari.domain.User

data class UserLoginResponseDto(
    val name: String,

) {
    companion object {
        fun of(user: User): UserLoginResponseDto {
            return UserLoginResponseDto(
                name = user.name

            )
        }
    }
}