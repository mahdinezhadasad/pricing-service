package com.udacity.vehicles.api;


//import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
//import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import com.udacity.vehicles.domain.car.Car;
import com.udacity.vehicles.service.CarService;

import java.net.URISyntaxException;
import javax.validation.Valid;

import org.springframework.hateoas.CollectionModel;
//import org.springframework.hateoas.Resource;
// import org.springframework.hateoas.Resources;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implements a REST-based controller for the Vehicles API.
 */
@RestController
@RequestMapping("/cars")
class CarController {

    private final CarService carService;
    private final CarResourceAssembler assembler;

    CarController(CarService carService, CarResourceAssembler assembler) {
        this.carService = carService;
        this.assembler = assembler;
    }

    /**
     * Creates a list to store any vehicles.
     * @return list of vehicles
     */
    @GetMapping
   ResponseEntity<CollectionModel<CarRepresentation>> list(){


        return ResponseEntity.ok(assembler.toCollectionModel(carService.list()));
    }

    /**
     * Gets information of a specific car by ID.
     * @param id the id number of the given vehicle
     * @return all information for the requested vehicle
     */
    @GetMapping("/{id}")
    ResponseEntity<?> get(@PathVariable Long id) {
        /**
         * TODO: Use the `findById` method from the Car Service to get car information.
         * TODO: Use the `assembler` on that car and return the resulting output.
         *   Update the first line as part of the above implementing.
         */
        Car car = carService.findById(id);


        if(car == null){


            return ResponseEntity.notFound().build();
        }
        CarRepresentation carRepresentation = assembler.toModel(car);
        return ResponseEntity.ok(carRepresentation);
    }

    /**
     * Posts information to create a new vehicle in the system.
     * @param car A new vehicle to add to the system.
     * @return response that the new vehicle was added to the system
     * @throws URISyntaxException if the request contains invalid fields or syntax
     */
    @PostMapping
    ResponseEntity<?> post(@Valid @RequestBody Car car) throws URISyntaxException {
        /**
         * TODO: Use the `save` method from the Car Service to save the input car.
         * TODO: Use the `assembler` on that saved car and return as part of the response.
         *   Update the first line as part of the above implementing.
         */

        car = carService.save(car);
        CarRepresentation carRepresentation = assembler.toModel(new Car());
        Link link = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(CarController.class)
                        .post(car))
                .withSelfRel();
        return ResponseEntity.created(link.toUri()).body(carRepresentation);

    }

    /**
     * Updates the information of a vehicle in the system.
     * @param id The ID number for which to update vehicle information.
     * @param car The updated information about the related vehicle.
     * @return response that the vehicle was updated in the system
     */
    @PutMapping("/{id}")
    ResponseEntity<?> put(@PathVariable Long id, @Valid @RequestBody Car car) {
        /**
         * TODO: Set the id of the input car object to the `id` input.
         * TODO: Save the car using the `save` method from the Car service
         * TODO: Use the `assembler` on that updated car and return as part of the response.
         *   Update the first line as part of the above implementing.
         */
     car.setId(id);
     CarRepresentation carRepresentation = assembler.toModel(new Car());
     return ResponseEntity.ok(carRepresentation);

    }

    /**
     * Removes a vehicle from the system.
     * @param id The ID number of the vehicle to remove.
     * @return response that the related vehicle is no longer in the system
     */
    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable Long id) {
        /**
         * TODO: Use the Car Service to delete the requested vehicle.
         */
        this.carService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
