package rattery.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import rattery.entity.Rat;

public interface RatDao extends JpaRepository<Rat, Long> {

}
