package software.bottari.dto.response

import software.bottari.domain.Inquiry

data class GetInquiryResponseDto (
    val title: String,
    val content: String,
    val inquiryId: Long,
    val answerStatus: Boolean
){
    companion object {
        fun of(inquiry: Inquiry): GetInquiryResponseDto {
            return GetInquiryResponseDto(
                title = inquiry.title,
                content = inquiry.content,
                inquiryId = inquiry.id,
                answerStatus = inquiry.reply!=null
            )
        }
    }

}