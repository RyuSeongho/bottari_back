package software.bottari.dto.response

import software.bottari.domain.Freight

data class ReservationResponseDto(
    val message: String
) {
    companion object {
        fun of(freight: Freight): ReservationResponseDto {
            val message = "${freight.senderName} 님으로부터 ${freight.receiverName} 로의 화물 예약이 완료되었습니다."
            return ReservationResponseDto(message)
        }
    }
}
