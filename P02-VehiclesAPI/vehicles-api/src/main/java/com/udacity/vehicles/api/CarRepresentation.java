package com.udacity.vehicles.api;


import com.udacity.vehicles.domain.Condition;
import com.udacity.vehicles.domain.Location;
import com.udacity.vehicles.domain.car.Details;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;
import java.time.LocalDateTime;
@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@Relation(itemRelation = "car", collectionRelation = "cars")
public class CarRepresentation extends RepresentationModel<CarRepresentation> {
    private final Long id;
    private final LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Condition condition;
    private Details details;
    private Location location;
    private String price;
}