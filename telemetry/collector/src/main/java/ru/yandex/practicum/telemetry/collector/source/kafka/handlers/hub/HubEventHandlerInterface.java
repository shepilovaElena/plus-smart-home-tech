package ru.yandex.practicum.telemetry.collector.source.kafka.handlers.hub;

import ru.yandex.practicum.telemetry.collector.model.hub.HubEvent;
import ru.yandex.practicum.telemetry.collector.model.hub.HubEventType;

public interface HubEventHandlerInterface {
    HubEventType getMessageType();

    void handle(HubEvent event);
}
