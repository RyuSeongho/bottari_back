package software.bottari.dto.request

data class InquiryRequestDto (
    val name: String,
    val title: String,
    val content: String
){
    fun toEntity(): InquiryRequestDto {
        return this.copy()
    }
}