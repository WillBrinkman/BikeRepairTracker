package com.willbrinkman.BikeRepairTracker.models.repositories;

import com.willbrinkman.BikeRepairTracker.models.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import javax.transaction.Transactional;

@Repository
@Transactional
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Integer> {
}
