package rattery.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

import rattery.controller.model.RatteryData;
import rattery.entity.Rattery;

public class RatteryServiceTestSupport {

	private static final String RAT_TABLE = "rat";

	private static final String RAT_VARIETY_TABLE = "rat_variety";

	private static final String VARIETY_TABLE = "variety";

	private static final String LOCATION_TABLE = "rattery";

	private static final String INSERT_RAT_1_SQL = """
			insert into rat (rattery_id, unique_id, age, price, temperament, mother_variety, father_variety)
			values (1, '101', 6, 25.00, 'curious, playful', 'top-eared silvermane', 'dumbo-eared self')
			""";

	private static final String INSERT_RAT_2_SQL = """
			insert into rat (rattery_id, unique_id, age, price, temperament, mother_variety, father_variety)
			values (1, '105', 6, 25.00, 'shy, calm', 'top-eared silvermane', 'dumbo-eared self')
			""";

	private static final String INSERT_VARIETIES_1_SQL = """
			insert into rat_variety (rat_id, variety_id)
			values (1, 1), (1, 5), (1, 15)
			""";

	private static final String INSERT_VARIETIES_2_SQL = """
			insert into rat_variety (rat_id, variety_id)
			values (2, 2), (2, 5), (2, 20)
			""";

	//@formatter:off
	private RatteryData insertAddress1 = new RatteryData(
		1L,
		"The Berry Rat Patch Rattery",
		"",
		"Middleton",
		"ID",
		"",
		"208.283.3788",
		"theberryratpatch@gmail.com",
		"facebook.com/theberryratpatch");
	
	private RatteryData insertAddress2 = new RatteryData(
		2L,
		"Blossoming Mischief Rattery",
		"",
		"Hayden",
		"ID",
		"83835",
		"",
		"blossomingmischeifrattery@gmail.com",
		"facebook.com/blossomingmischeif");
	
	private RatteryData updateAddress1 = new RatteryData(
			1L,
			"Precious Pearls Rattery",
			"",
			"Nampa",
			"ID",
			"83651",
			"208.899.7851",
			"preciouspearlsrattery@gmail.com",
			"https://www.facebook.com/profile.php?id=100085554667441");
	//@formatter:on

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private RatteryController ratteryController;

	protected RatteryData buildInsertRattery(int choice) {

		return choice == 1 ? insertAddress1 : insertAddress2;
	}

	protected int rowsInRatteryTable() {

		return JdbcTestUtils.countRowsInTable(jdbcTemplate, LOCATION_TABLE);
	}

	protected RatteryData insertRattery(RatteryData request) {
		Rattery rattery = request.toRattery();
		RatteryData ratteryData = new RatteryData(rattery);

		ratteryData.setRatteryId(null);

		return ratteryController.createRattery(ratteryData);
	}

	protected RatteryData retrieveRattery(Long ratteryId) {

		return ratteryController.readRattery(ratteryId);
	}

	protected List<RatteryData> insertTwoRatteries() {
		RatteryData rattery1 = insertRattery(buildInsertRattery(1));
		RatteryData rattery2 = insertRattery(buildInsertRattery(2));

		return List.of(rattery1, rattery2);
	}

	protected List<RatteryData> retrieveAllRatteries() {

		return ratteryController.readAllRatteries();
	}

	protected List<RatteryData> sorted(List<RatteryData> result) {
		List<RatteryData> input = new LinkedList<>(result);
		input.sort((rattery1, rattery2) -> (int) (rattery1.getRatteryId() - rattery2.getRatteryId()));

		return input;
	}

	protected RatteryData updateRattery(RatteryData expected) {
		return ratteryController.updateRattery(expected.getRatteryId(), expected);
	}

	protected RatteryData buildUpdateRattery() {
		return updateAddress1;
	}

	protected void insertRat(int choice) {
		String ratSql = choice == 1 ? INSERT_RAT_1_SQL : INSERT_RAT_2_SQL;
		String varietiesSql = choice == 1 ? INSERT_VARIETIES_1_SQL : INSERT_VARIETIES_2_SQL;

		jdbcTemplate.update(ratSql);
		jdbcTemplate.update(varietiesSql);

	}

	protected int rowsInVarietyTable() {
		return JdbcTestUtils.countRowsInTable(jdbcTemplate, VARIETY_TABLE);
	}

	protected int rowsInRatVarietyTable() {
		return JdbcTestUtils.countRowsInTable(jdbcTemplate, RAT_VARIETY_TABLE);
	}

	protected int rowsInRatTable() {
		return JdbcTestUtils.countRowsInTable(jdbcTemplate, RAT_TABLE);
	}

	protected void deleteRattery(Long ratteryId) {
		ratteryController.deleteRattery(ratteryId);
	}

}
