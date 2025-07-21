package ru.yandex.practicum.telemetry.collector.source.kafka.handlers.sensor;


import ru.yandex.practicum.telemetry.collector.model.sensor.SensorEvent;
import ru.yandex.practicum.telemetry.collector.model.sensor.SensorEventType;

public interface SensorEventHandlerInterface {
    SensorEventType getMessageType();

    void handle(SensorEvent event);
}
