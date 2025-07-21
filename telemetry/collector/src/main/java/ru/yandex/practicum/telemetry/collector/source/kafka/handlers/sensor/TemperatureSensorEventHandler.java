package ru.yandex.practicum.telemetry.collector.source.kafka.handlers.sensor;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.kafka.telemetry.event.TemperatureSensorEventAvro;
import ru.yandex.practicum.telemetry.collector.model.sensor.SensorEvent;
import ru.yandex.practicum.telemetry.collector.model.sensor.TemperatureSensorEvent;
import ru.yandex.practicum.telemetry.collector.model.sensor.SensorEventType;
import ru.yandex.practicum.telemetry.collector.source.kafka.handlers.KafkaConfig;

@Service
public class TemperatureSensorEventHandler extends SensorEventHandler<TemperatureSensorEventAvro> {
    public TemperatureSensorEventHandler(KafkaConfig.KafkaEventProducer producer, KafkaConfig kafkaTopics) {
        super(producer, kafkaTopics);
    }

    @Override
    public SensorEventType getMessageType() {
        return SensorEventType.TEMPERATURE_SENSOR_EVENT;
    }

    @Override
    protected TemperatureSensorEventAvro mapToAvro(SensorEvent event) {
        var temperatureEvent = (TemperatureSensorEvent) event;
        return new TemperatureSensorEventAvro(
                temperatureEvent.getTemperatureC(),
                temperatureEvent.getTemperatureF()
        );
    }
}
