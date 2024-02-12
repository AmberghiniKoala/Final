package rattery.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Variety {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long varietyId;
	private String varietyType;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(mappedBy = "varieties", cascade = CascadeType.PERSIST)
	private Set<Rat> rats = new HashSet<>();
}
