package ru.yandex.practicum.telemetry.collector.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.kafka.telemetry.event.HubEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.SensorEventAvro;
import ru.yandex.practicum.telemetry.collector.kafkaClient.KafkaProducerService;
import ru.yandex.practicum.telemetry.collector.kafkaClient.Topics;
import ru.yandex.practicum.telemetry.collector.model.hub.HubEvent;
import ru.yandex.practicum.telemetry.collector.model.sensor.SensorEvent;

@Service
@RequiredArgsConstructor
@Slf4j
public class CollectorServiceImpl implements CollectorService {
    private final HubEventHandler hubHandler;
    private final SensorEventHandler sensorHandler;
    private final KafkaProducerService producerService;

    @Override
    public void sendSensorEvent(SensorEvent sensorEvent) {
        Producer<String, SensorEventAvro> sensorProducer = producerService.createSensorEventProducer();
        SensorEventAvro avro = sensorHandler.mapToAvro(sensorEvent);
        sensorProducer.send(new ProducerRecord<>(Topics.HUB_TOPIC, avro.getHubId(), avro));
    }

    @Override
    public void sendHubEvent(HubEvent hubEvent) {
        Producer<String, HubEventAvro> hubProducer = producerService.createHubEventProducer();
        HubEventAvro avro = hubHandler.mapToAvro(hubEvent);
        hubProducer.send(new ProducerRecord<>(Topics.SENSOR_TOPIC, avro.getHubId(), avro));
    }
}
