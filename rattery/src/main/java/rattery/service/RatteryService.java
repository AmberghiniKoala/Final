package rattery.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rattery.controller.model.RatteryData;
import rattery.controller.model.RatteryData.RatData;
import rattery.controller.model.RatteryData.VarietyData;
import rattery.dao.RatDao;
import rattery.dao.RatteryDao;
import rattery.dao.VarietyDao;
import rattery.entity.Rat;
import rattery.entity.Rattery;
import rattery.entity.Variety;

@Service
public class RatteryService {

	@Autowired
	private RatteryDao ratteryDao;
	@Autowired
	private RatDao ratDao;
	@Autowired
	private VarietyDao varietyDao;

	@Transactional(readOnly = false)
	public RatteryData createRattery(RatteryData ratteryData) {
		Rattery rattery = ratteryData.toRattery();
		Rattery dbRattery = ratteryDao.save(rattery);

		return new RatteryData(dbRattery);
	}
	
	@Transactional(readOnly = false)
	public RatData createRat(Long ratteryId, RatData ratData) {
		Rattery rattery = findRatteryById(ratteryId);
		Rat rat = ratData.toRat();
		rat.setRattery(rattery);
		rattery.getRats().add(rat);
		Rat dbRat = ratDao.save(rat);
		
		return new RatData(dbRat);
	}

	@Transactional(readOnly = false)
	public VarietyData createVariety(VarietyData varietyData) {
		Variety variety = varietyData.toVariety();
		Variety dbVariety = varietyDao.save(variety);
		
		return new VarietyData(dbVariety);
	}

	@Transactional(readOnly = true)
	public RatteryData retrieveRatteryById(Long ratteryId) {
		Rattery rattery = findRatteryById(ratteryId);

		return new RatteryData(rattery);
	}

	private Rattery findRatteryById(Long ratteryId) {

		return ratteryDao.findById(ratteryId)
				.orElseThrow(() -> new NoSuchElementException("Rattery " + ratteryId + "doesn't exist"));
	}

	@Transactional(readOnly = true)
	public List<RatteryData> retrieveAllLoctions() {

		//@formatter:off
		return ratteryDao.findAll().stream().map(RatteryData::new).toList();
		//@formatter:on
	}

	@Transactional(readOnly = false)
	public void deleteRattery(Long ratteryId) {
		Rattery rattery = findRatteryById(ratteryId);
		ratteryDao.delete(rattery);
	}

}
