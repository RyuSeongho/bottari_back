package software.bottari.domain

import jakarta.persistence.*
import org.hibernate.annotations.DynamicUpdate

@Entity
@DynamicUpdate
data class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, unique = true)
    val email: String,

    @Column(nullable = false)
    val password: String,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = true)
    val phoneNumber: String?,

    @Column(nullable = false)
    val termsAgreed: Boolean, // 약관 동의 여부

    @Column(nullable = false)
    val privacyAgreed: Boolean, // 개인정보 동의 여부

    @OneToMany(mappedBy = "member", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val inquiries: List<Inquiry> = listOf(),

    @OneToMany(mappedBy = "member", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val contracts: List<Contract> = listOf()
)