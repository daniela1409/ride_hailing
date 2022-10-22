package com.note_generator.app.models.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.note_generator.app.models.dao.LocationDao;
import com.note_generator.app.models.entity.Location;

@Service
public class LocationImpl implements ILocation {

	@Autowired
	private LocationDao locationDao;

	@Override
	public Location saveLocation(Map<String, String> location) {
		Location locationCreated;
		try {
			locationCreated = new Location();
			locationCreated.setLatitude(location.get("latitude"));
			locationCreated.setLongitude(location.get("longitude"));
			this.save(locationCreated);
		}
		catch(Exception e) {
			locationCreated = null;
		}
		return locationCreated;
	}

	@Override
	public void save(Location location) {
		locationDao.save(location);
	}
}
