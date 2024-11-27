package software.bottari.dto.request

data class UserLoginRequestDto(
    val email: String,
    val password: String
) {
    fun toEntity(): UserLoginRequestDto {
        return this.copy()
    }
}