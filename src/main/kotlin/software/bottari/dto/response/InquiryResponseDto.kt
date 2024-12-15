package software.bottari.dto.response

import software.bottari.domain.Inquiry

data class InquiryResponseDto (
    val message: String
){
    companion object {
        fun of(inquiry: Inquiry): InquiryResponseDto {
            val message = "${inquiry.id}번 문의사항이 접수되었습니다."

            return InquiryResponseDto(message)
        }
    }
}