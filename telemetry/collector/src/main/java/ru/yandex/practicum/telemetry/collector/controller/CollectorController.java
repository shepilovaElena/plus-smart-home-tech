package ru.yandex.practicum.telemetry.collector.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.telemetry.collector.errors.HubHandlerNotFoundException;
import ru.yandex.practicum.telemetry.collector.errors.SensorHandlerNotFoundException;
import ru.yandex.practicum.telemetry.collector.model.hub.HubEvent;
import ru.yandex.practicum.telemetry.collector.model.hub.HubEventType;
import ru.yandex.practicum.telemetry.collector.model.sensor.SensorEvent;
import ru.yandex.practicum.telemetry.collector.model.sensor.SensorEventType;
import ru.yandex.practicum.telemetry.collector.source.kafka.handlers.hub.HubEventHandler;
import ru.yandex.practicum.telemetry.collector.source.kafka.handlers.sensor.SensorEventHandler;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/events", consumes = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class CollectorController {
    private final Map<SensorEventType, SensorEventHandler> sensorEventHandlers;
    private final Map<HubEventType, HubEventHandler> hubEventHandlers;

    public CollectorController(Set<SensorEventHandler> sensorEventHandlers, Set<HubEventHandler> hubEventHandlers) {
        this.sensorEventHandlers = sensorEventHandlers.stream()
                .collect(Collectors.toMap(SensorEventHandler::getMessageType, Function.identity()));
        this.hubEventHandlers = hubEventHandlers.stream()
                .collect(Collectors.toMap(HubEventHandler::getMessageType, Function.identity()));
    }

    @PostMapping("/sensors")
    public void sensors(@Valid @RequestBody SensorEvent event) {
        log.info("Получено событие сенсора: type={}, deviceId={}", event.getType(), event.getId());

        SensorEventHandler handler = sensorEventHandlers.get(event.getType());
        if (handler == null) {
            log.error("SensorEventHandler не найден для типа: {}", event.getType());
            throw new SensorHandlerNotFoundException("Sensor event not found: " + event.getType());
        }

        log.debug("Handler found: {}", handler.getClass().getSimpleName());
        handler.handle(event);
    }

    @PostMapping("/hubs")
    public void hubs(@Valid @RequestBody HubEvent event) {
        log.info("Получено событие хаба: type={}, hubId={}", event.getType(), event.getHubId());

        HubEventHandler handler = hubEventHandlers.get(event.getType());
        if (handler == null) {
            log.error("HubEventHandler не найден для типа: {}", event.getType());
            throw new HubHandlerNotFoundException("The hub event was not found: " + event.getType());
        }

        log.debug("Handler found: {}", handler.getClass().getSimpleName());
        handler.handle(event);
    }
}
