package ru.yandex.practicum.telemetry.collector.source.kafka.handlers.hub;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.kafka.telemetry.event.DeviceAddedEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.DeviceTypeAvro;
import ru.yandex.practicum.telemetry.collector.model.hub.DeviceAddedEvent;
import ru.yandex.practicum.telemetry.collector.model.hub.HubEvent;
import ru.yandex.practicum.telemetry.collector.model.hub.HubEventType;
import ru.yandex.practicum.telemetry.collector.source.kafka.handlers.KafkaConfig;


@Service
public class DeviceAddedEventHandler extends HubEventHandler<DeviceAddedEventAvro> {
    public DeviceAddedEventHandler(KafkaConfig.KafkaEventProducer producer, KafkaConfig kafkaTopics) {
        super(producer, kafkaTopics);
    }

    @Override
    public HubEventType getMessageType() {
        return HubEventType.DEVICE_ADDED;
    }

    @Override
    protected DeviceAddedEventAvro mapToAvro(HubEvent event) {
        var deviceAddedEvent = (DeviceAddedEvent) event;
        return new DeviceAddedEventAvro(
                deviceAddedEvent.getId(),
                DeviceTypeAvro.valueOf(deviceAddedEvent.getDeviceType().name())
        );
    }
}
