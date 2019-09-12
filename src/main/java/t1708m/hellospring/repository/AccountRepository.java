package t1708m.hellospring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import t1708m.hellospring.entity.Account;

public interface AccountRepository extends JpaRepository<Account, String> {
}
