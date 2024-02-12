package rattery.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Rat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ratId;
	private String uniqueId;
	private String gender;
	private Boolean fixed;
	private String age;
	private BigDecimal price;
	private String temperament;
	private String motherVariety;
	private String fatherVariety;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rattery_id")
	private Rattery rattery;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "rat_variety", joinColumns = @JoinColumn(name = "rat_id"), inverseJoinColumns = @JoinColumn(name = "variety_id"))
	private Set<Variety> varieties = new HashSet<>();
}
