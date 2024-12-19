package demo.device.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "device")
public class Device {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column
    private String description;

    @Column
    private String address;

    @Column
    private Long consumption;

    @Column(nullable = false)
    private UUID personId;

    public Device(String description, String address, Long consumption, UUID personId) {
        this.description = description;
        this.address = address;
        this.consumption = consumption;
        this.personId = personId;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", consumption=" + consumption +
                ", personId=" + personId +
                '}';
    }
}
