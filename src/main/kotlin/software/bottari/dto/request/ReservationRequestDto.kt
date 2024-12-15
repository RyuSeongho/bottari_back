package software.bottari.dto.request


data class ReservationRequestDto(
    val productType: String,
    val productPrice: Long,
    val productWeight: Float,
    val senderName: String,
    val clearanceNumber: String,
    val remark: String,
    val quantity: Int,
    val senderAddress: String,
    val senderNumber: String,
    val receiverName: String,
    val receiverAddress: String,
    val receiverNumber: Int,
    val receiverCountry : String,
    val receiverCity : String,
    val receiverState : String,
    val receiverZipcode : Int,
    val shippingStatus: String,
    val additionalDoc: String
){
    fun toEntity(): ReservationRequestDto {
        return this.copy()
    }

}