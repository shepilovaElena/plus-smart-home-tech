package ru.yandex.practicum.telemetry.collector.source.kafka.handlers.sensor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecordBase;
import ru.yandex.practicum.telemetry.collector.model.sensor.SensorEvent;
import ru.yandex.practicum.telemetry.collector.source.kafka.handlers.KafkaConfig;

@Slf4j
@RequiredArgsConstructor
public abstract class SensorEventHandler<T extends SpecificRecordBase> implements SensorEventHandlerInterface {
    protected final KafkaConfig.KafkaEventProducer producer;
    protected final KafkaConfig topics;

    protected abstract T mapToAvro(SensorEvent event);

    @Override
    public void handle(SensorEvent event) {
        T avroEvent = mapToAvro(event);
        String topic = topics.getProducer().getTopics().get(KafkaConfig.TopicType.SENSORS_EVENTS);
        log.info("Handling event of type {}. Will send to topic {}", getMessageType(), topic);
        producer.send(topic, event.getId(), avroEvent);
    }
}
