package software.bottari.domain

import jakarta.persistence.*
import org.hibernate.annotations.DynamicUpdate

@DynamicUpdate
@Entity
data class Contract(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @OneToMany
    @JoinColumn(name = "payment_id", nullable = false)
    val payments: List<Payment> = emptyList(),

    @OneToOne
    @JoinColumn(name = "freight_id", nullable = false)
    val freight: Freight,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val member: Member,

    @OneToOne(mappedBy = "contract", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val deliveryStatus: DeliveryStatus? = null // 배송 상태와 1:1 관계
)

