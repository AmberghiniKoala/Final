package rattery.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import rattery.controller.model.RatteryData;
import rattery.controller.model.RatteryData.RatData;
import rattery.controller.model.RatteryData.VarietyData;
import rattery.service.RatteryService;

@RestController
@RequestMapping("/rattery")
@Slf4j
public class RatteryController {
	@Autowired
	private RatteryService ratteryService;

	@PostMapping("/rattery")
	@ResponseStatus(code = HttpStatus.CREATED)
	public RatteryData createRattery(@RequestBody RatteryData ratteryData) {
		log.info("Creating rattery {}", ratteryData);

		return ratteryService.createRattery(ratteryData);
	}
	
	@PostMapping("/{ratteryId}/rat")
	@ResponseStatus(code = HttpStatus.CREATED)
	public RatData createRat(@PathVariable Long ratteryId, @RequestBody RatData ratData) {
		log.info("Creating rat {}", ratData);
		
		return ratteryService.createRat(ratteryId, ratData);
	}
	
	@PostMapping("/variety")
	@ResponseStatus(code = HttpStatus.CREATED)
	public VarietyData createVariety(@RequestBody VarietyData varietyData) {
		log.info("Creating variety {}", varietyData);
		
		return ratteryService.createVariety(varietyData);
	}

	@GetMapping("/rattery/{ratteryId}")
	public RatteryData readRattery(@PathVariable Long ratteryId) {
		log.info("Retrieving rattery {}", ratteryId);

		return ratteryService.retrieveRatteryById(ratteryId);
	}

	@GetMapping("/rattery")
	public List<RatteryData> readAllRatteries() {
		log.info("Retrieving all ratteries");

		return ratteryService.retrieveAllLoctions();
	}

	@PutMapping("/rattery/{ratteryId}")
	public RatteryData updateRattery(@PathVariable Long ratteryId, @RequestBody RatteryData ratteryData) {
		ratteryData.setRatteryId(ratteryId);
		log.info("Updating rattery {}", ratteryData);

		return ratteryService.createRattery(ratteryData);
	}

	@DeleteMapping("/rattery/{ratteryId}")
	public Map<String, String> deleteRattery(@PathVariable Long ratteryId) {
		log.info("Deleting rattery " + ratteryId);
		ratteryService.deleteRattery(ratteryId);

		return Map.of("Message: ", "Rattery " + ratteryId + " has been deleted.");
	}
}
