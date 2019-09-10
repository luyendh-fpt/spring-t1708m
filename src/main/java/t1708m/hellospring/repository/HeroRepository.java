package t1708m.hellospring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import t1708m.hellospring.entity.Hero;

import java.util.List;

public interface HeroRepository extends JpaRepository<Hero, Long> {
    List<Hero> findAllByNameAndStatus(String name, int status);
}
