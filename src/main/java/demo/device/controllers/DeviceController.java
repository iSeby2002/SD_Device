package demo.device.controllers;

import demo.device.entities.Device;
import demo.device.services.DeviceProducer;
import demo.device.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = {
        "http://frontend.localhost",
        "http://user.localhost",
        "http://device.localhost",
        "http://monitoring.localhost",
        "http://chat.localhost",
        "http://localhost",
        "http://localhost:3000",
        "http://localhost:8080",
        "http://localhost:8081",
        "http://localhost:8082",
        "http://localhost:8083"})
@RequestMapping("/device")
public class DeviceController {
    private final DeviceService deviceService;
    private final DeviceProducer deviceProducer;

    @Autowired
    public DeviceController(DeviceService deviceService, DeviceProducer deviceProducer) {
        this.deviceService = deviceService;
        this.deviceProducer = deviceProducer;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addDevice(@RequestBody Device device) {
        String msg = deviceService.addDevice(device);
        deviceProducer.sendMessage(device, "add");
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateDevice(@RequestBody Device device) {
        String msg = deviceService.updateDevice(device);
        if(msg.equals("Device updated successfully!")){
            deviceProducer.sendMessage(device, "update");
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{deviceId}")
    public ResponseEntity<String> deleteDevice(@PathVariable UUID deviceId) {
        String msg = deviceService.deleteDevice(deviceId);
        if(msg.equals("Device deleted successfully!")){
            Device device = new Device(deviceId, null, null, null, null);
            deviceProducer.sendMessage(device, "delete");
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteAllDevices/{personId}")
    public ResponseEntity<String> deleteAllDevices(@PathVariable UUID personId) {
        String msg = deviceService.deleteAllDevices(personId);
        if(msg.equals("Devices deleted successfully!")){
            Device device = new Device(null, null, null, null, personId);
            deviceProducer.sendMessage(device, "deleteAll");
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(msg, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/getDevices")
    public ResponseEntity<List<Device>> getDevices() {
        List<Device> devices = deviceService.getDevices();
        return new ResponseEntity<>(devices, HttpStatus.OK);
    }

    @GetMapping("/getDevices/{personId}")
    public ResponseEntity<List<Device>> getDevicesByPersonId(@PathVariable UUID personId) {
        List<Device> devices = deviceService.getDevicesByPersonId(personId);
        return new ResponseEntity<>(devices, HttpStatus.OK);
    }
}
