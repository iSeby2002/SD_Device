package demo.device.services;

import demo.device.entities.Device;
import demo.device.repositories.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DeviceService {
    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public String addDevice(Device device) {
        deviceRepository.save(device);
        return "Device added successfully!";
    }

    public String updateDevice(Device device) {
        Device existingDevice = deviceRepository.findDeviceById(device.getId());
        if(existingDevice != null){
            existingDevice.setDescription(device.getDescription());
            existingDevice.setAddress(device.getAddress());
            existingDevice.setConsumption(device.getConsumption());
            deviceRepository.save(existingDevice);
            return "Device updated successfully!";
        }else{
            return "Device not found!";
        }
    }

    public String deleteDevice(UUID deviceId) {
        Device existingDevice = deviceRepository.findDeviceById(deviceId);
        if(existingDevice != null) {
            deviceRepository.delete(existingDevice);
            return "Device deleted successfully!";
        }else{
            return "Device not found!";
        }
    }

    public String deleteAllDevices(UUID personId) {
        List<Device> existingDevices = deviceRepository.findDevicesByPersonId(personId);
        if (existingDevices.isEmpty()) {
            return "No devices found to delete!";
        }
        deviceRepository.deleteAll(existingDevices);
        return "Devices deleted successfully!";
    }

    public List<Device> getDevices() {
        return deviceRepository.findAll();
    }

    public List<Device> getDevicesByPersonId(UUID personId) {
        return deviceRepository.findDevicesByPersonId(personId);
    }
}
