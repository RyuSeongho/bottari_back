package software.bottari.domain

import jakarta.persistence.*
import org.hibernate.annotations.DynamicUpdate

@DynamicUpdate
@Entity
data class Freight(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val title: String,

    @Column(nullable = false)
    val senderName: String,

    @Column(nullable = false)
    val senderAddress: String,

    @Column(nullable = false)
    val senderPhoneNumber: String,

    @Column(nullable = false)
    val receiverName: String,

    @Column(nullable = false)
    val receiverAddress: String,

    @Column(nullable = false)
    val receiverPhoneNumber: String,

    @Column(nullable = false)
    val receiver: String,

    @Column(nullable = false)
    val address: String,

    @Column(nullable = false)
    val isGoingAbroad: Boolean = false,

    @Column(nullable = false)
    val shippingStatus: String,

    @Column(nullable = false)
    val additionalDocumentFolderUrl: String? = null,

    @Column(nullable = false)
    val productType: String,

    @Column(nullable = false)
    val productPrice: Long,

    @Column(nullable = false)
    val productWeight: Float,

    @Column(nullable = true)
    val remark: String,

    @OneToOne(mappedBy = "freight", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val contract: Contract? = null
)