package ru.yandex.practicum.telemetry.collector.model.hub;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DeviceAction {
    @NotBlank
    private String sensorId;
    @NotBlank
    private ActionType type;
    @NotBlank
    private Integer value;
}
