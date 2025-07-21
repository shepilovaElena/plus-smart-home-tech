package ru.yandex.practicum.telemetry.collector.errors;

public class HubHandlerNotFoundException extends RuntimeException {
    public HubHandlerNotFoundException(String message) {
        super(message);
    }
}
