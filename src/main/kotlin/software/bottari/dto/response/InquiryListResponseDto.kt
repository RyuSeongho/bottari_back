package software.bottari.dto.response

import PageInfoResponseDto

data class InquiryListResponseDto(
    val inquiryList: List<GetInquiryResponseDto>,
    val pageInfo: PageInfoResponseDto
) {
    companion object {
        fun of(inquiryList: List<GetInquiryResponseDto>, pageInfo: PageInfoResponseDto): InquiryListResponseDto {
            return InquiryListResponseDto(
                inquiryList = inquiryList,
                pageInfo = pageInfo
            )
        }
    }
}
