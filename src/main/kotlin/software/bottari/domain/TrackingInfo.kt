package software.bottari.domain

import jakarta.persistence.*
import org.hibernate.annotations.DynamicUpdate
import java.time.LocalDateTime

@DynamicUpdate
@Entity
data class TrackingInfo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val time: LocalDateTime, // 상태 발생 시간

    @Column(nullable = false)
    val position: String, // 현재 위치

    @Column(nullable = false)
    val trackingStatus: String, // 배송 상태 메시지

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_status_id", nullable = false)
    val deliveryStatus: DeliveryStatus // 배송 상태와 연결
)