package ru.yandex.practicum.telemetry.collector.source.kafka.handlers.sensor;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.kafka.telemetry.event.MotionSensorEventAvro;
import ru.yandex.practicum.telemetry.collector.model.sensor.MotionSensorEvent;
import ru.yandex.practicum.telemetry.collector.model.sensor.SensorEvent;
import ru.yandex.practicum.telemetry.collector.model.sensor.SensorEventType;
import ru.yandex.practicum.telemetry.collector.source.kafka.handlers.KafkaConfig;

@Service
public class MotionSensorEventHandler extends SensorEventHandler<MotionSensorEventAvro> {
    public MotionSensorEventHandler(KafkaConfig.KafkaEventProducer producer, KafkaConfig kafkaTopics) {
        super(producer, kafkaTopics);
    }

    @Override
    public SensorEventType getMessageType() {
        return SensorEventType.MOTION_SENSOR_EVENT;
    }

    @Override
    protected MotionSensorEventAvro mapToAvro(SensorEvent event) {
        var motionEvent = (MotionSensorEvent) event;
        return new MotionSensorEventAvro(
                motionEvent.getLinkQuality(),
                motionEvent.getMotion(),
                motionEvent.getVoltage()
        );
    }
}
