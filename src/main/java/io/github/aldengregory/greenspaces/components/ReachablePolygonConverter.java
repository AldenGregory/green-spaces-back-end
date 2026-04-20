package io.github.aldengregory.greenspaces.components;

import java.util.List;

import org.springframework.stereotype.Component;

import io.github.aldengregory.greenspaces.dtos.ReachablePolygonResponseDTO;
import io.github.aldengregory.greenspaces.dtos.ReachablePolygonResultDTO;
import io.github.aldengregory.greenspaces.dtos.ReachablePolygonResponseDTO.PolygonGeometryResponseDTO;
import io.github.aldengregory.greenspaces.dtos.ReachablePolygonResultDTO.PolygonGeometryResultDTO;

@Component
public class ReachablePolygonConverter {
    public ReachablePolygonResultDTO fromReachablePoylgonResponseDTO(ReachablePolygonResponseDTO response) {
        // For this app, requests are only made for one feature.
        PolygonGeometryResponseDTO geometryDTO = response.features().get(0).geometry();

        // For this app, requests are only made for one polygon.
        List<List<List<Double>>> polygonCoordinates = geometryDTO.coordinates().get(0);

        // Create geometry GeoJSON for geometry.
        PolygonGeometryResultDTO resultGeometry = new PolygonGeometryResultDTO(
            "Polygon",
            polygonCoordinates
        );

        return new ReachablePolygonResultDTO(
            "Feature",
            resultGeometry
        );
    }
}
