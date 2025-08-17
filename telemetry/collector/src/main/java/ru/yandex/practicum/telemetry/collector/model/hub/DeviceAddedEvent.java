package ru.yandex.practicum.telemetry.collector.model.hub;

import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@ToString(callSuper = true)
public class DeviceAddedEvent extends DeviceRemovedEvent {
    @NotNull
    private String id;
    @NotNull
    private DeviceType deviceType;

    public HubEventType getType() {
        return HubEventType.DEVICE_ADDED;
    }
}
