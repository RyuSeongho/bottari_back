package software.bottari.service

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import software.bottari.domain.Inquiry
import software.bottari.dto.request.InquiryRequestDto
import software.bottari.dto.response.GetInquiryResponseDto
import software.bottari.dto.response.InquiryResponseDto
import software.bottari.exception.ApiException
import software.bottari.exception.ErrorDefine
import software.bottari.repository.MemberRepository
import software.bottari.repository.InquiryRepository

@Service
@RequiredArgsConstructor
class MypageService (
    private val memberRepository: MemberRepository,
    private val inquiryRepository: InquiryRepository

){
    fun inquiry(inquiryRequestDto: InquiryRequestDto): InquiryResponseDto {
        val member = memberRepository.findByName(inquiryRequestDto.name)
            .orElseThrow { ApiException(ErrorDefine.USER_NOT_FOUND) }

        val inquiry= Inquiry(
            title = inquiryRequestDto.title,
            content = inquiryRequestDto.content,
            member =  member
        )
        return InquiryResponseDto.of(inquiryRepository.save(inquiry))
    }

    fun getInquiry(inquiryId: Long): GetInquiryResponseDto {
        val inquiry = inquiryRepository.findById(inquiryId)
            .orElseThrow{ ApiException(ErrorDefine.INQUIRY_NOT_FOUND) }
        return GetInquiryResponseDto.of(inquiry)
    }
}