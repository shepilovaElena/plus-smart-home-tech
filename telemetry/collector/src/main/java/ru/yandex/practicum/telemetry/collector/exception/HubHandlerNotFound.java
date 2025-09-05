package ru.yandex.practicum.telemetry.collector.exception;

public class HubHandlerNotFound extends RuntimeException {
    public HubHandlerNotFound(String message) {
        super(message);
    }
}
