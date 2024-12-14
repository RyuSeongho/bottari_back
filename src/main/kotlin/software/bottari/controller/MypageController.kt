package software.bottari.controller

import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import software.bottari.dto.request.InquiryRequestDto
import software.bottari.dto.response.InquiryResponseDto
import software.bottari.dto.response.ResponseDto
import software.bottari.service.MypageService

@RequiredArgsConstructor
@RestController
class MypageController(
    private val mypageService: MypageService,
) {
    @PostMapping("/inquiry")
    fun inquiry(@RequestBody mypageRequestDto: InquiryRequestDto): ResponseDto<InquiryResponseDto> {
        return ResponseDto(mypageService.inquiry(mypageRequestDto))
    }
}