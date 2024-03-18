package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.Car;
import com.mycompany.myapp.repository.CarRepository;
import com.mycompany.myapp.service.CarService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.mycompany.myapp.domain.Car}.
 */
@Service
@Transactional
public class CarServiceImpl implements CarService {

    private final Logger log = LoggerFactory.getLogger(CarServiceImpl.class);

    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Car save(Car car) {
        log.debug("Request to save Car : {}", car);
        return carRepository.save(car);
    }

    @Override
    public Car update(Car car) {
        log.debug("Request to update Car : {}", car);
        return carRepository.save(car);
    }

    @Override
    public Optional<Car> partialUpdate(Car car) {
        log.debug("Request to partially update Car : {}", car);

        return carRepository
            .findById(car.getId())
            .map(existingCar -> {
                if (car.getName() != null) {
                    existingCar.setName(car.getName());
                }

                return existingCar;
            })
            .map(carRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Car> findAll(Pageable pageable) {
        log.debug("Request to get all Cars");
        return carRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Car> findOne(Long id) {
        log.debug("Request to get Car : {}", id);
        return carRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Car : {}", id);
        carRepository.deleteById(id);
    }
}
