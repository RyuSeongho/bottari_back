package software.bottari.dto.response

import software.bottari.domain.Contract
import software.bottari.domain.TrackingInfo

data class ContractResponseDto(
    val senderName: String,
    val receiverName: String,
    val shippingStatus: String,
    val pickup: Boolean,
    val ongoing: Boolean,
    val service: String,
    val finished: Boolean,
    val cancel: String,
){
    companion object {
        fun of(contract: Contract): ContractResponseDto {
            val pickup = contract.deliveryStatus?.pickup ?: false
            val ongoing = contract.deliveryStatus?.ongoing ?: false
            val service = contract.deliveryStatus?.service ?: "Unknown"
            val finished = contract.deliveryStatus?.finished ?: false
            val cancel = if (!ongoing) "possible" else "impossible"

            return ContractResponseDto(
                senderName = contract.freight.senderName,
                receiverName = contract.freight.receiverName,
                shippingStatus = contract.freight.shippingStatus,
                pickup = pickup,
                ongoing = ongoing,
                service = service,
                finished = finished,
                cancel = cancel
            )
        }
    }

}