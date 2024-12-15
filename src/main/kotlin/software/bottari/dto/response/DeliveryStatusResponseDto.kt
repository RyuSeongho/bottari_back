package software.bottari.dto.response

import software.bottari.domain.DeliveryStatus

data class DeliveryStatusResponseDto (
    val pickup: Boolean,
    val ongoing: Boolean,
    val service: String,
    val companyIncharge: String,
    val manageIncharge: String,
    val finished: Boolean
){
    companion object{
        fun of(deliveryStatus: DeliveryStatus): DeliveryStatusResponseDto{
            return DeliveryStatusResponseDto(
                pickup = deliveryStatus.pickup,
                ongoing = deliveryStatus.ongoing,
                service = deliveryStatus.service,
                companyIncharge = deliveryStatus.companyInCharge,
                manageIncharge = deliveryStatus.managerInCharge,
                finished = deliveryStatus.finished
            )
        }
    }
}