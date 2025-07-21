package ru.yandex.practicum.telemetry.collector.source.kafka.handlers.sensor;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.kafka.telemetry.event.LightSensorEventAvro;
import ru.yandex.practicum.telemetry.collector.model.sensor.LightSensorEvent;
import ru.yandex.practicum.telemetry.collector.model.sensor.SensorEvent;
import ru.yandex.practicum.telemetry.collector.model.sensor.SensorEventType;
import ru.yandex.practicum.telemetry.collector.source.kafka.handlers.KafkaConfig;

@Service
public class LightSensorEventHandler extends SensorEventHandler<LightSensorEventAvro> {
    public LightSensorEventHandler(KafkaConfig.KafkaEventProducer producer, KafkaConfig kafkaTopics) {
        super(producer, kafkaTopics);
    }

    @Override
    public SensorEventType getMessageType() {
        return SensorEventType.LIGHT_SENSOR_EVENT;
    }

    @Override
    protected LightSensorEventAvro mapToAvro(SensorEvent event) {
        var lightEvent = (LightSensorEvent) event;
        return new LightSensorEventAvro(
                lightEvent.getLinkQuality(),
                lightEvent.getLuminosity()
        );
    }
}
