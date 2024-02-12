package rattery.controller.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import rattery.entity.Rattery;
import rattery.entity.Rat;
import rattery.entity.Variety;

@Data
@NoArgsConstructor
public class RatteryData {
	private Long ratteryId;
	private String businessName;
	private String streetAddress;
	private String city;
	private String state;
	private String zip;
	private String phone;
	private String email;
	private String website;
	private Set<RatData> rats = new HashSet<>();

	public RatteryData(Rattery rattery) {
		this.ratteryId = rattery.getRatteryId();
		this.businessName = rattery.getBusinessName();
		this.streetAddress = rattery.getStreetAddress();
		this.city = rattery.getCity();
		this.state = rattery.getState();
		this.zip = rattery.getZip();
		this.phone = rattery.getPhone();
		this.email = rattery.getEmail();
		this.website = rattery.getWebsite();

		for (Rat rat : rattery.getRats()) {
			this.rats.add(new RatData(rat));
		}
	}

	public RatteryData(Long ratteryId, String businessName, String streetAddress, String city, String state,
			String zip, String phone, String email, String website) {
		this.ratteryId = ratteryId;
		this.businessName = businessName;
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phone = phone;
		this.email = email;
		this.website = website;
	}

	public Rattery toRattery() {
		Rattery rattery = new Rattery();

		rattery.setRatteryId(ratteryId);
		rattery.setBusinessName(businessName);
		rattery.setStreetAddress(streetAddress);
		rattery.setCity(city);
		rattery.setState(state);
		rattery.setZip(zip);
		rattery.setPhone(phone);
		rattery.setEmail(email);
		rattery.setWebsite(website);

		for (RatData ratData : rats) {
			rattery.getRats().add(ratData.toRat());
		}

		return rattery;
	}

	@Data
	@NoArgsConstructor
	public static class RatData {
		private Long ratId;
		private String uniqueId;
		private String gender;
		private Boolean fixed;
		private String age;
		private BigDecimal price;
		private String temperament;
		private String motherVariety;
		private String fatherVariety;
		private Set<VarietyData> varieties = new HashSet<>();

		public RatData(Rat rat) {
			this.ratId = rat.getRatId();
			this.uniqueId = rat.getUniqueId();
			this.gender = rat.getGender();
			this.fixed = rat.getFixed();
			this.age = rat.getAge();
			this.price = rat.getPrice();
			this.temperament = rat.getTemperament();
			this.motherVariety = rat.getMotherVariety();
			this.fatherVariety = rat.getFatherVariety();

			for (Variety variety : rat.getVarieties()) {
				this.varieties.add(new VarietyData(variety));
			}
		}

		public Rat toRat() {
			Rat rat = new Rat();

			rat.setRatId(ratId);
			rat.setUniqueId(uniqueId);
			rat.setGender(gender);
			rat.setFixed(fixed);
			rat.setAge(age);
			rat.setPrice(price);
			rat.setTemperament(temperament);
			rat.setMotherVariety(motherVariety);
			rat.setFatherVariety(fatherVariety);

			for (VarietyData varietyData : varieties) {
				rat.getVarieties().add(varietyData.toVariety());
			}

			return rat;
		}
	}

	@Data
	@NoArgsConstructor
	public static class VarietyData {
		private Long varietyId;
		private String varietyType;

		public VarietyData(Variety variety) {
			this.varietyId = variety.getVarietyId();
			this.varietyType = variety.getVarietyType();
		}

		public Variety toVariety() {
			Variety variety = new Variety();

			variety.setVarietyId(varietyId);
			variety.setVarietyType(varietyType);

			return variety;
		}
	}
}
