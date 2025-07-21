package ru.yandex.practicum.telemetry.collector.model.hub;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@ToString
public class DeviceRemovedEvent extends HubEvent {
    @NotBlank
    private String id;

    public HubEventType getType() {
        return HubEventType.DEVICE_REMOVED;
    }
}
