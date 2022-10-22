package com.note_generator.app.models.services;

import java.util.Map;

import com.note_generator.app.models.entity.Location;

public interface ILocation {

	public Location saveLocation(Map<String, String> location);
	public void save(Location location);
}
