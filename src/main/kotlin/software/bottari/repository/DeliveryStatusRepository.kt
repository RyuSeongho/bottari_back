package software.bottari.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import software.bottari.domain.DeliveryStatus
import java.util.*

@Repository
interface DeliveryStatusRepository: JpaRepository<DeliveryStatus, Long> {
    fun findByContractId(orderId: Long): Optional<DeliveryStatus>
}