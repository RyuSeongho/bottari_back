package dto.response

import domain.User

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