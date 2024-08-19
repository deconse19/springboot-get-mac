package com.weasle.test.repo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.weasle.test.entity.Device;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    // Additional query methods if needed
}
