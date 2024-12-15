package software.bottari.dto.response

data class StatusResponseDto(
    val trackingList: List<TrackingInfoResponseDto>,
    val deliveryInfo: DeliveryStatusResponseDto
) {
    companion object {
        fun of(trackingList: List<TrackingInfoResponseDto>, deliveryInfo: DeliveryStatusResponseDto): StatusResponseDto {
            return StatusResponseDto(
                trackingList = trackingList,
                deliveryInfo = deliveryInfo
            )
        }
    }
}