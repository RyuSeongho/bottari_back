package software.bottari.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import software.bottari.domain.Freight

@Repository
interface FreightRepository: JpaRepository<Freight, Long> {

}
