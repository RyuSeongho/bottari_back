package software.bottari.service

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import software.bottari.domain.Freight
import software.bottari.dto.request.ReservationRequestDto
import software.bottari.dto.response.ReservationResponseDto
import software.bottari.exception.ApiException
import software.bottari.exception.ErrorDefine
import software.bottari.repository.MemberRepository
import software.bottari.repository.FreightRepository

@Service
@RequiredArgsConstructor
class ReservationService (
    private val freightRepository: FreightRepository,
    private val memberRepository: MemberRepository
){
    fun domestic(reservationRequestDto: ReservationRequestDto) : ReservationResponseDto {
        val user = memberRepository.findByName(reservationRequestDto.senderName)
            .orElseThrow { ApiException(ErrorDefine.USER_NOT_FOUND) }

        val freight = Freight(
            productType = reservationRequestDto.productType,
            productPrice = reservationRequestDto.productPrice,
            productWeight = reservationRequestDto.productWeight,
            clearanceNumber = reservationRequestDto.clearanceNumber,
            remark = reservationRequestDto.remark,
            quantity = reservationRequestDto.quantity,
            senderName = reservationRequestDto.senderName,
            senderAddress = reservationRequestDto.senderAddress,
            senderNumber = reservationRequestDto.senderNumber,
            receiverName = reservationRequestDto.receiverName,
            receiverAddress = reservationRequestDto.receiverAddress,
            receiverNumber = reservationRequestDto.receiverNumber,
            receiverCountry = reservationRequestDto.receiverCountry,
            receiverCity = reservationRequestDto.receiverCity,
            receiverState = reservationRequestDto.receiverState,
            receiverZipcode = reservationRequestDto.receiverZipcode,
            additionalDoc = reservationRequestDto.additionalDoc
        )

        // Reservation 엔티티 저장
        return ReservationResponseDto.of(freightRepository.save(freight))
    }
}