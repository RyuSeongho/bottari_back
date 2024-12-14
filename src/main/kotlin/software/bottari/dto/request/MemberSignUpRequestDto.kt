package software.bottari.dto.request

data class MemberSignUpRequestDto(
    val email: String,
    val password: String,
    val name: String,
    val phoneNumber: String

) {
    fun toEntity(): MemberSignUpRequestDto {
        return this.copy()
    }
}