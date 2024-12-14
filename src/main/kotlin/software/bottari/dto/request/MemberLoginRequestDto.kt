package software.bottari.dto.request

data class MemberLoginRequestDto(
    val email: String,
    val password: String
) {
    fun toEntity(): MemberLoginRequestDto {
        return this.copy()
    }
}