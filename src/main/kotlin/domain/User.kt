package domain

import jakarta.persistence.*
import lombok.Getter
import lombok.RequiredArgsConstructor
import org.hibernate.annotations.DynamicUpdate


@Entity
@RequiredArgsConstructor
@Getter
@DynamicUpdate
@Table(name = "user_tb")
class User(

    @Column val email: String, // 로그인 아이디
    @Column val password: String, // 비밀번호
    @Column(columnDefinition = "TEXT")
    val name:String, // 이름

    ) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null
}


