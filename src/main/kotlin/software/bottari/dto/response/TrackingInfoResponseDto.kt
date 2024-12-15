package software.bottari.dto.response

import software.bottari.domain.TrackingInfo
import java.time.LocalDateTime

data class TrackingInfoResponseDto(
    val time: LocalDateTime,
    val position: String,
    val trackingStatus: String
) {
    companion object {
        fun of(trackingInfo: TrackingInfo): TrackingInfoResponseDto {
            return TrackingInfoResponseDto(
                time = trackingInfo.time,
                position = trackingInfo.position,
                trackingStatus = trackingInfo.trackingStatus
            )
        }
    }
}