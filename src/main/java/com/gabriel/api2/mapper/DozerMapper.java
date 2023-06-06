package com.gabriel.api2.mapper;

import java.util.ArrayList;
import java.util.List;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

public class DozerMapper {

	private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();

	public static <O, D> D parseObject(O Origin, Class<D> Destination) {
		return mapper.map(Origin, Destination);
	}

	public static <O, D> List<D> parseListObjects(List<O> Origin, Class<D> Destination) {
		List<D> destinationObjects = new ArrayList<D>();
		for (O o : Origin) {
			destinationObjects.add(mapper.map(o, Destination));
		}

		return destinationObjects;
	}
}
