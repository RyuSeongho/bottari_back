package software.bottari.domain

import jakarta.persistence.*
import org.hibernate.annotations.DynamicUpdate

@DynamicUpdate
@Entity
data class Reply(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val content: String,

    @OneToOne
    @JoinColumn(name = "inquiry_id", nullable = false)
    val inquiry: Inquiry
)