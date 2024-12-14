package software.bottari.domain

import jakarta.persistence.*
import org.hibernate.annotations.DynamicUpdate

@DynamicUpdate
@Entity
data class DeliveryStatus(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val pickup: Boolean, // 픽업 완료 여부

    @Column(nullable = false)
    val ongoing: Boolean, // 진행 중 여부

    @Column(nullable = false)
    val service: String, // 현재 서비스 단계

    @Column(nullable = false)
    val companyInCharge: String, // 배송 책임 회사

    @Column(nullable = false)
    val managerInCharge: String, // 배송 책임자

    @Column(nullable = false)
    val finished: Boolean, // 배송 완료 여부

    @OneToMany(mappedBy = "deliveryStatus", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val trackingInfos: List<TrackingInfo> = listOf(),

    @OneToOne
    @JoinColumn(name = "contract_id", nullable = false)
    val contract: Contract // 픽업 예약(Contract)와 1:1 관계
)