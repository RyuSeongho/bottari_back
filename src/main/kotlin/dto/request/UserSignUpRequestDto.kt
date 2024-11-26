package dto.request

data class UserSignUpRequestDto(
    val email: String,
    val password: String,
    val name: String

) {
    fun toEntity(): UserSignUpRequestDto {
        return this.copy()
    }
}