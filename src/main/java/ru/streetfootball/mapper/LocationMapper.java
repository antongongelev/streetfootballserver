package ru.streetfootball.mapper;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.stereotype.Component;
import ru.streetfootball.dto.LocationDTO;

@Component
public class LocationMapper implements Mapper<Point, LocationDTO> {

    private final GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);

    @Override
    public LocationDTO dtoFromEntity(Point point) {
        return new LocationDTO(point.getY(), point.getX());
    }

    @Override
    public Point entityFromDto(LocationDTO locationDTO) {
        return geometryFactory.createPoint(new Coordinate(locationDTO.getLongitude(), locationDTO.getLatitude()));
    }
}
