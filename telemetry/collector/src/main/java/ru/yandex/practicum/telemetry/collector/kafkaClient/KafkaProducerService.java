package ru.yandex.practicum.telemetry.collector.kafkaClient;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Component;
import ru.practicum.kafka.GeneralAvroSerializer;
import ru.yandex.practicum.kafka.telemetry.event.HubEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.SensorEventAvro;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Component
public class KafkaProducerService {
    public Producer<String, SensorEventAvro> createSensorEventProducer() {
        return new KafkaProducer<>(addProperties());
    }

    public Producer<String, HubEventAvro> createHubEventProducer() {
        return new KafkaProducer<>(addProperties());
    }

    private Properties addProperties() {
        Properties appProperties = new Properties();
        try (InputStream input = getClass().getClassLoader()
                .getResourceAsStream("application.yml")) {
            if (input == null) {
                throw new RuntimeException("Unable to find application");
            }
            appProperties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Error loading properties", e);
        }

        Properties kafkaProps = new Properties();
        kafkaProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                appProperties.getProperty("kafka.producer.bootstrap-servers", "localhost:9092"));
        kafkaProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                appProperties.getProperty("kafka.producer.key-serializer", StringSerializer.class.getName()));
        kafkaProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                appProperties.getProperty("kafka.producer.value-serializer", GeneralAvroSerializer.class.getName()));

        return kafkaProps;
    }
}
