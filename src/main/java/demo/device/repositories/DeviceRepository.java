package demo.device.repositories;

import demo.device.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DeviceRepository extends JpaRepository<Device, UUID> {
    Device findDeviceById(UUID id);
    List<Device> findDevicesByPersonId(UUID id);
}
