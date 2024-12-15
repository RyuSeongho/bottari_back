package software.bottari.controller

import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import software.bottari.dto.request.ReservationRequestDto
import software.bottari.dto.response.ReservationResponseDto
import software.bottari.dto.response.ResponseDto
import software.bottari.service.ReservationService

@RequiredArgsConstructor
@RequestMapping("reservation")
@RestController
class ReservationController (
    private val reservationService: ReservationService
) {

    @PostMapping("/domestic")
    fun domestic(@RequestBody reservationRequestDto: ReservationRequestDto): ResponseDto<ReservationResponseDto> {
        return ResponseDto(reservationService.domestic(reservationRequestDto))
    }
}