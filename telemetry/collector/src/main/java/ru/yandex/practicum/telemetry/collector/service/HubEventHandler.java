package ru.yandex.practicum.telemetry.collector.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.telemetry.collector.exception.HubHandlerNotFound;
import ru.yandex.practicum.kafka.telemetry.event.HubEventAvro;
import ru.yandex.practicum.telemetry.collector.mapper.EventMapper;
import ru.yandex.practicum.telemetry.collector.model.hub.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class HubEventHandler {

    private final EventMapper eventMapper;

    public HubEventAvro mapToAvro(HubEvent event) {
        log.info("Mapping HubEvent to Avro: {}", event);

        return switch (event) {
            case DeviceAddedEvent e -> eventMapper.toAvro(e);
            case DeviceRemovedEvent e -> eventMapper.toAvro(e);
            case ScenarioAddedEvent e -> eventMapper.toAvro(e);
            case ScenarioRemovedEvent e -> eventMapper.toAvro(e);
            default -> {
                log.warn("Unsupported HubEvent type: {}", event.getType());
                throw new HubHandlerNotFound("Unsupported HubEvent: " + event.getType());
            }
        };
    }
}

