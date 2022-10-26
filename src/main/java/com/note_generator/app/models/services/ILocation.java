package com.note_generator.app.models.services;

import java.util.Map;

import com.note_generator.app.models.dto.LocationDTO;
import com.note_generator.app.models.entity.Location;

public interface ILocation {

	public Location saveLocation(LocationDTO location);
	public Location save(Location location);
}
