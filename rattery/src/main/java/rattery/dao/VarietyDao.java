package rattery.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import rattery.entity.Variety;

public interface VarietyDao extends JpaRepository<Variety, Long> {

}
