package rattery.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import rattery.entity.Rattery;

public interface RatteryDao extends JpaRepository<Rattery, Long> {

}
