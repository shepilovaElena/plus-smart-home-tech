package ru.yandex.practicum.telemetry.collector.errors;

public class SensorHandlerNotFoundException extends RuntimeException {
    public SensorHandlerNotFoundException(String message) {
        super(message);
    }
}
