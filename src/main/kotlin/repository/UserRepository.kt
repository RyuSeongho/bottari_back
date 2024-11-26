package repository

import domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByName(name: String): Optional<User?>?
    fun findByEmail(name: String): Optional<User?>?

}