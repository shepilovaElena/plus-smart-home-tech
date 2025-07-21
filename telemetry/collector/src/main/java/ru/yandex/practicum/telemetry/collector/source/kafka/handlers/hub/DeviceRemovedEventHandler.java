package ru.yandex.practicum.telemetry.collector.source.kafka.handlers.hub;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.kafka.telemetry.event.DeviceRemovedEventAvro;
import ru.yandex.practicum.telemetry.collector.model.hub.DeviceRemovedEvent;
import ru.yandex.practicum.telemetry.collector.model.hub.HubEvent;
import ru.yandex.practicum.telemetry.collector.model.hub.HubEventType;
import ru.yandex.practicum.telemetry.collector.source.kafka.handlers.KafkaConfig;

@Service
public class DeviceRemovedEventHandler extends HubEventHandler<DeviceRemovedEventAvro> {
    public DeviceRemovedEventHandler(KafkaConfig.KafkaEventProducer producer, KafkaConfig kafkaTopics) {
        super(producer, kafkaTopics);
    }

    @Override
    public HubEventType getMessageType() {
        return HubEventType.DEVICE_REMOVED;
    }

    @Override
    protected DeviceRemovedEventAvro mapToAvro(HubEvent event) {
        var deviceRemovedEvent = (DeviceRemovedEvent) event;
        return new DeviceRemovedEventAvro(
                deviceRemovedEvent.getId()
        );
    }
}
