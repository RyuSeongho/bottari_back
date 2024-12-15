package software.bottari.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import software.bottari.domain.DeliveryStatus
import software.bottari.domain.TrackingInfo

@Repository
interface TrackingInfoRepository: JpaRepository<TrackingInfo,Long> {
    @Query("SELECT i FROM TrackingInfo i where i.deliveryStatus = :deliveryStatus")
    fun findAllByDeliveryStatus(@Param("deliveryStatus")deliveryStatus: DeliveryStatus): List<TrackingInfo>
}