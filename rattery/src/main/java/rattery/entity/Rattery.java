package rattery.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Rattery {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ratteryId;
	private String businessName;
	private String streetAddress;
	private String city;
	private String state;
	private String zip;
	private String phone;
	private String email;
	private String website;

	@OneToMany(mappedBy = "rattery", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Rat> rats = new HashSet<>();
}
