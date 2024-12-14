package software.bottari.domain

import jakarta.persistence.*
import org.hibernate.annotations.DynamicUpdate

@DynamicUpdate
@Entity
data class Payment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id", nullable = false)
    val contract: Contract,

    @Column(nullable = false)
    val fee: Long,

    @Column(nullable = false)
    val confirmed: Boolean
)