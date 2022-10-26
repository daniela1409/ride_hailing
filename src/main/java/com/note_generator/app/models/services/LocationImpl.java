package com.note_generator.app.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.note_generator.app.models.dao.LocationDao;
import com.note_generator.app.models.dto.LocationDTO;
import com.note_generator.app.models.entity.Location;

@Service
public class LocationImpl implements ILocation {

	@Autowired
	private LocationDao locationDao;

	@Override
	public Location saveLocation(LocationDTO location) {
		Location locationCreated;
		try {
			locationCreated = new Location();
			locationCreated.setLatitude(location.getLatitude());
			locationCreated.setLongitude(location.getLongitude());
			this.save(locationCreated);
		}
		catch(Exception e) {
			locationCreated = null;
		}
		return locationCreated;
	}

	@Override
	public Location save(Location location) {
		return locationDao.save(location);
	}
}
