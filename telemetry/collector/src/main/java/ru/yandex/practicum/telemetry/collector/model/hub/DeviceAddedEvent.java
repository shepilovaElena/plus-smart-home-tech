package ru.yandex.practicum.telemetry.collector.model.hub;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeviceAddedEvent extends DeviceRemovedEvent {
    @NotNull
    private String id;
    @NotNull
    private DeviceType deviceType;

    public HubEventType getType() {
        return HubEventType.DEVICE_ADDED;
    }
}
