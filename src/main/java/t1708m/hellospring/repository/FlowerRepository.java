package t1708m.hellospring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import t1708m.hellospring.entity.Flower;

@Repository
public interface FlowerRepository extends JpaRepository<Flower, Integer> {

}
