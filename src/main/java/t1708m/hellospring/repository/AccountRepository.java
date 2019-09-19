package t1708m.hellospring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import t1708m.hellospring.entity.Account;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, String> {
    Page<Account> findAllByStatusAndCreatedAtMLSGreaterThanEqual(int status, long createdAtMLS, Pageable pageable);

    @Query("select a from Account as a where a.status = :status and a.createdAtMLS >= :createdAtMLS")
    Page<Account> findAllActiveAccount(@Param("status") int status, @Param("createdAtMLS") long createdAtMLS, Pageable pageable);
}
