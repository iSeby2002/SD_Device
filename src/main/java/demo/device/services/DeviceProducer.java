package demo.device.services;

import demo.device.dtos.MessageDTO;
import demo.device.entities.Device;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceProducer {
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public DeviceProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(Device device, String message) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setMessage(message);
        messageDTO.setDeviceId(device.getId());
        messageDTO.setDescription(device.getDescription());
        messageDTO.setAddress(device.getAddress());
        messageDTO.setConsumption(device.getConsumption());
        messageDTO.setPersonId(device.getPersonId());
        rabbitTemplate.convertAndSend("device-monitoring-queue", messageDTO);
    }
}
