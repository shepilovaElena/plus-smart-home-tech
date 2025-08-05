package ru.yandex.practicum.telemetry.collector.exception;

public class SensorHandlerNotFound extends RuntimeException {
    public SensorHandlerNotFound(String message) {
        super(message);
    }
}
