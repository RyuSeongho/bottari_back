package software.bottari.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import software.bottari.domain.Contract

@Repository
interface ContractRepository: JpaRepository<Contract, Long> {}
