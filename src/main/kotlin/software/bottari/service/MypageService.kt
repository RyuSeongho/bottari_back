package software.bottari.service

import lombok.RequiredArgsConstructor
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import software.bottari.domain.DeliveryStatus
import software.bottari.domain.Inquiry
import software.bottari.dto.request.InquiryRequestDto
import software.bottari.dto.response.*
import software.bottari.exception.ApiException
import software.bottari.exception.ErrorDefine
import software.bottari.repository.*

@Service
@RequiredArgsConstructor
class MypageService(
    private val memberRepository: MemberRepository,
    private val inquiryRepository: InquiryRepository,
    private val deliveryStatusRepository: DeliveryStatusRepository,
    private val trackingInfoRepository: TrackingInfoRepository,
    private val contractRepository: ContractRepository

){
    fun inquiry(inquiryRequestDto: InquiryRequestDto): InquiryResponseDto {
        val member = memberRepository.findByName(inquiryRequestDto.name)
            .orElseThrow { ApiException(ErrorDefine.USER_NOT_FOUND) }

        val inquiry= Inquiry(
            title = inquiryRequestDto.title,
            content = inquiryRequestDto.content,
            member =  member
        )
        return InquiryResponseDto.of(inquiryRepository.save(inquiry))
    }

    fun getInquiry(inquiryId: Long): GetInquiryResponseDto {
        val inquiry = inquiryRepository.findById(inquiryId)
            .orElseThrow{ ApiException(ErrorDefine.INQUIRY_NOT_FOUND) }
        return GetInquiryResponseDto.of(inquiry)
    }

    fun getInquiryList(memberName: String, page: Int): InquiryListResponseDto {
        val pageable = PageRequest.of(page, 10) // 페이지와 크기 설정
        val inquiryPage = inquiryRepository.findAllByMemberName(memberName, pageable)

        if (inquiryPage.isEmpty) {
            throw ApiException(ErrorDefine.INQUIRY_NOT_FOUND)
        }

        val inquiriesDto = inquiryPage.content.map { inquiry ->
            GetInquiryResponseDto.of(inquiry)
        }
        val pageInfo = PageInfoResponseDto.of(inquiryPage)

        return InquiryListResponseDto.of(
            inquiryList = inquiriesDto,
            pageInfo = pageInfo
        )
    }

    fun getStatus(orderId: Long): StatusResponseDto {
        val deliveryStatus = deliveryStatusRepository.findByContractId(orderId)
            .orElseThrow{ ApiException(ErrorDefine.CONTRACT_NOT_FOUND)}

        val trackingInfos = trackingInfoRepository.findAllByDeliveryStatus(deliveryStatus)
        val trackingList = trackingInfos.map{ trackingInfo ->
            TrackingInfoResponseDto.of(trackingInfo)
        }
        val deliveryInfo = DeliveryStatusResponseDto.of(deliveryStatus)
        return StatusResponseDto.of(
            trackingList = trackingList,
            deliveryInfo = deliveryInfo
        )
    }
    fun getContractList(memberName: String, page: Int): ContractListResponseDto {
        val pageable = PageRequest.of(page, 10) // 페이지와 크기 설정
        val contractPage = contractRepository.findAllByMemberName(memberName, pageable)

        if (contractPage.isEmpty) {
            throw ApiException(ErrorDefine.INQUIRY_NOT_FOUND)
        }

        val contractsDto = contractPage.content.map { contract ->
            ContractResponseDto.of(contract)
        }
        val pageInfo = PageInfoResponseDto.of(contractPage)

        return ContractListResponseDto.of(
            contractList = contractsDto,
            pageInfo = pageInfo
        )
    }

}