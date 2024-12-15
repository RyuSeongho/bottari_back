package software.bottari.controller

import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.*
import software.bottari.dto.request.InquiryRequestDto
import software.bottari.dto.response.GetInquiryResponseDto
import software.bottari.dto.response.InquiryResponseDto
import software.bottari.dto.response.ResponseDto
import software.bottari.service.MypageService

@RequiredArgsConstructor
@RequestMapping("/mypage")
@RestController
class MypageController(
    private val mypageService: MypageService,
) {
    @PostMapping("/inquiry")
    fun inquiry(@RequestBody mypageRequestDto: InquiryRequestDto): ResponseDto<InquiryResponseDto> {
        return ResponseDto(mypageService.inquiry(mypageRequestDto))
    }

    @GetMapping("/inquiry")
    fun getInquiry(@RequestParam(value = "inquiryId") inquiryId: Long): ResponseDto<GetInquiryResponseDto> {
        return ResponseDto(mypageService.getInquiry(inquiryId))
    }
}