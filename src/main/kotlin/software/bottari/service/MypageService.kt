package software.bottari.service

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import software.bottari.domain.Inquiry
import software.bottari.dto.request.InquiryRequestDto
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
    fun inquiry(mypageRequestDto: InquiryRequestDto): InquiryResponseDto {
        val member = memberRepository.findByName(mypageRequestDto.name)
            .orElseThrow { ApiException(ErrorDefine.USER_NOT_FOUND) }

        val inquiry= Inquiry(
            title = mypageRequestDto.title,
            content = mypageRequestDto.content,
            member =  member
        )
        return InquiryResponseDto.of(inquiryRepository.save(inquiry))
    }
}