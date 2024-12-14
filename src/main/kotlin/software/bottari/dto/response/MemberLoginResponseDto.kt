package software.bottari.dto.response

import software.bottari.domain.Member

data class MemberLoginResponseDto(
    val name: String,

) {
    companion object {
        fun of(member: Member): MemberLoginResponseDto {
            return MemberLoginResponseDto(
                name = member.name

            )
        }
    }
}