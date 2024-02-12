package rattery.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import rattery.RatteryApplication;
import rattery.controller.model.RatteryData;

@SpringBootTest(webEnvironment = WebEnvironment.NONE, classes = RatteryApplication.class)
@ActiveProfiles("test")
@Sql(scripts = { "classpath:schema.sql", "classpath:data.sql" })
@SqlConfig(encoding = "utf-8")
class RatteryControllerTest extends RatteryServiceTestSupport {

	@Test
	void testInsertRattery() {
		// Given: A request to add a rattery
		RatteryData request = buildInsertRattery(1);
		RatteryData expected = buildInsertRattery(1);

		// When: rattery is inserted into the rattery table
		RatteryData result = insertRattery(request);

		// Then: the result is the same as the request
		assertThat(result).isEqualTo(expected);

		// And: there is only one row in the table.
		assertThat(rowsInRatteryTable()).isOne();

	}

	@Test
	void testRetrieveRattery() {
		// Given: A rattery request
		RatteryData request = insertRattery(buildInsertRattery(1));
		RatteryData expected = buildInsertRattery(1);

		// When: rattery is retrieved by ratteryId
		RatteryData result = retrieveRattery(request.getRatteryId());

		// Then: result rattery is equal to expected rattery.
		assertThat(result).isEqualTo(expected);
	}

	@Test
	void testRetrieveAllRatteries() {
		// Given: Two ratteries
		List<RatteryData> expected = insertTwoRatteries();

		// When: all ratteries are requested
		List<RatteryData> result = retrieveAllRatteries();

		// Then: resulting ratteries are equal to expected ratteries
		assertThat(sorted(result)).isEqualTo(sorted(expected));

		// And: all rows are returned.
	}

	@Test
	void testUpdateRattery() {
		// Given: A rattery with an update request
		insertRattery(buildInsertRattery(1));
		RatteryData expected = buildUpdateRattery();

		// When: rattery is updated
		RatteryData result = updateRattery(expected);

		// Then: result rattery is equal to expected rattery
		assertThat(result).isEqualTo(expected);

		// And: there is only one row in the table.
		assertThat(rowsInRatteryTable()).isOne();
	}

	@Test
	void testDeleteRatteryContainingRats() {
		// Given: A rattery containing two rats
		RatteryData rattery = insertRattery(buildInsertRattery(1));
		Long ratteryId = rattery.getRatteryId();

		insertRat(1);
		insertRat(2);

		assertThat(rowsInRatteryTable()).isOne();
		assertThat(rowsInRatTable()).isEqualTo(2);
		assertThat(rowsInRatVarietyTable()).isEqualTo(6);

		int varietyRows = rowsInVarietyTable();

		// When: the rattery is deleted
		deleteRattery(ratteryId);

		// Then: rattery, rats, rat_varieties are deleted
		assertThat(rowsInRatteryTable()).isZero();
		assertThat(rowsInRatTable()).isZero();
		assertThat(rowsInRatVarietyTable()).isZero();

		// And: rows of varieties persist.
		assertThat(rowsInVarietyTable()).isEqualTo(varietyRows);
	}

}
