package software.bottari.dto.response

import PageInfoResponseDto

data class ContractListResponseDto(
    val contractList: List<ContractResponseDto>,
    val pageInfo: PageInfoResponseDto
) {
    companion object {
        fun of(contractList: List<ContractResponseDto>, pageInfo: PageInfoResponseDto): ContractListResponseDto {
            return ContractListResponseDto(
                contractList = contractList,
                pageInfo = pageInfo
            )
        }
    }
}
