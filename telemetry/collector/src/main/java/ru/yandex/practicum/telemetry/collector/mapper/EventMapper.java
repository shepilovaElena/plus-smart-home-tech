package ru.yandex.practicum.telemetry.collector.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.yandex.practicum.kafka.telemetry.event.ClimateSensorEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.DeviceAddedEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.DeviceRemovedEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.HubEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.LightSensorEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.MotionSensorEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.ScenarioAddedEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.ScenarioRemovedEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.SensorEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.SwitchSensorAvro;
import ru.yandex.practicum.kafka.telemetry.event.TemperatureSensorEventAvro;
import ru.yandex.practicum.telemetry.collector.model.hub.DeviceAddedEvent;
import ru.yandex.practicum.telemetry.collector.model.hub.DeviceRemovedEvent;
import ru.yandex.practicum.telemetry.collector.model.hub.ScenarioAddedEvent;
import ru.yandex.practicum.telemetry.collector.model.hub.ScenarioRemovedEvent;
import ru.yandex.practicum.telemetry.collector.model.sensor.ClimateSensorEvent;
import ru.yandex.practicum.telemetry.collector.model.sensor.LightSensorEvent;
import ru.yandex.practicum.telemetry.collector.model.sensor.MotionSensorEvent;
import ru.yandex.practicum.telemetry.collector.model.sensor.SwitchSensorEvent;
import ru.yandex.practicum.telemetry.collector.model.sensor.TemperatureSensorEvent;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface EventMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "hubId", source = "hubId")
    @Mapping(target = "timestamp", source = "timestamp")
    @Mapping(target = "payload", source = "event", qualifiedBy = ToPayload.class)
    SensorEventAvro toAvro(ClimateSensorEvent event);

    @ToPayload
    @Mapping(target = "temperatureC", source = "temperatureC")
    @Mapping(target = "humidity", source = "humidity")
    @Mapping(target = "co2Level", source = "co2Level")
    ClimateSensorEventAvro toPayload(ClimateSensorEvent event);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "hubId", source = "hubId")
    @Mapping(target = "timestamp", source = "timestamp")
    @Mapping(target = "payload", source = "event", qualifiedBy = ToPayload.class)
    SensorEventAvro toAvro(LightSensorEvent event);

    @ToPayload
    @Mapping(target = "linkQuality", source = "linkQuality")
    @Mapping(target = "luminosity", source = "luminosity")
    LightSensorEventAvro toPayload(LightSensorEvent event);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "hubId", source = "hubId")
    @Mapping(target = "timestamp", source = "timestamp")
    @Mapping(target = "payload", source = "event", qualifiedBy = ToPayload.class)
    SensorEventAvro toAvro(MotionSensorEvent event);

    @ToPayload
    @Mapping(target = "linkQuality", source = "linkQuality")
    @Mapping(target = "motion", source = "motion")
    @Mapping(target = "voltage", source = "voltage")
    MotionSensorEventAvro toPayload(MotionSensorEvent event);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "hubId", source = "hubId")
    @Mapping(target = "timestamp", source = "timestamp")
    @Mapping(target = "payload", source = "event", qualifiedBy = ToPayload.class)
    SensorEventAvro toAvro(SwitchSensorEvent event);

    @ToPayload
    @Mapping(target = "state", source = "state")
    SwitchSensorAvro toPayload(SwitchSensorEvent event);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "hubId", source = "hubId")
    @Mapping(target = "timestamp", source = "timestamp")
    @Mapping(target = "payload", source = "event", qualifiedBy = ToPayload.class)
    SensorEventAvro toAvro(TemperatureSensorEvent event);

    @ToPayload
    @Mapping(target = "temperatureC", source = "temperatureC")
    @Mapping(target = "temperatureF", source = "temperatureF")
    TemperatureSensorEventAvro toPayload(TemperatureSensorEvent event);

    @Mapping(target = "hubId", source = "hubId")
    @Mapping(target = "timestamp", source = "timestamp")
    @Mapping(target = "payload", source = "event", qualifiedBy = ToPayload.class)
    HubEventAvro toAvro(DeviceAddedEvent event);

    @ToPayload
    @Mapping(target = "id", source = "id")
    @Mapping(target = "type", source = "deviceType")
    DeviceAddedEventAvro toPayload(DeviceAddedEvent event);

    @Mapping(target = "hubId", source = "hubId")
    @Mapping(target = "timestamp", source = "timestamp")
    @Mapping(target = "payload", source = "event", qualifiedBy = ToPayload.class)
    HubEventAvro toAvro(DeviceRemovedEvent event);

    @ToPayload
    @Mapping(target = "id", source = "id")
    DeviceRemovedEventAvro toPayload(DeviceRemovedEvent event);

    @Mapping(target = "hubId", source = "hubId")
    @Mapping(target = "timestamp", source = "timestamp")
    @Mapping(target = "payload", source = "event", qualifiedBy = ToPayload.class)
    HubEventAvro toAvro(ScenarioAddedEvent event);

    @ToPayload
    @Mapping(target = "name", source = "name")
    @Mapping(target = "conditions", source = "conditions")
    @Mapping(target = "actions", source = "actions")
    ScenarioAddedEventAvro toPayload(ScenarioAddedEvent event);

    @Mapping(target = "hubId", source = "hubId")
    @Mapping(target = "timestamp", source = "timestamp")
    @Mapping(target = "payload", source = "event", qualifiedBy = ToPayload.class)
    HubEventAvro toAvro(ScenarioRemovedEvent event);

    @ToPayload
    @Mapping(target = "name", source = "name")
    ScenarioRemovedEventAvro toPayload(ScenarioRemovedEvent event);
}
