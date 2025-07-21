package ru.yandex.practicum.telemetry.collector.source.kafka.handlers.hub;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.kafka.telemetry.event.ActionTypeAvro;
import ru.yandex.practicum.kafka.telemetry.event.OperationAvro;
import ru.yandex.practicum.kafka.telemetry.event.ConditionTypeAvro;
import ru.yandex.practicum.kafka.telemetry.event.DeviceActionAvro;
import ru.yandex.practicum.kafka.telemetry.event.ScenarioAddedEventAvro;
import ru.yandex.practicum.kafka.telemetry.event.ScenarioConditionAvro;
import ru.yandex.practicum.telemetry.collector.model.hub.HubEvent;
import ru.yandex.practicum.telemetry.collector.model.hub.ScenarioAddedEvent;
import ru.yandex.practicum.telemetry.collector.model.hub.HubEventType;
import ru.yandex.practicum.telemetry.collector.source.kafka.handlers.KafkaConfig;

@Service
public class ScenarioAddedEventHandler extends HubEventHandler<ScenarioAddedEventAvro> {
    public ScenarioAddedEventHandler(KafkaConfig.KafkaEventProducer producer, KafkaConfig kafkaTopics) {
        super(producer, kafkaTopics);
    }

    @Override
    public HubEventType getMessageType() {
        return HubEventType.SCENARIO_ADDED;
    }

    @Override
    protected ScenarioAddedEventAvro mapToAvro(HubEvent event) {
        var scenarioAddedEvent = (ScenarioAddedEvent) event;
        return new ScenarioAddedEventAvro(
                scenarioAddedEvent.getName(),
                scenarioAddedEvent.getConditions().stream().map(condition -> new ScenarioConditionAvro(
                        condition.getSensorId(),
                        ConditionTypeAvro.valueOf(condition.getType().name()),
                        OperationAvro.valueOf(condition.getOperation().name()),
                        condition.getValue()
                )).toList(),
                scenarioAddedEvent.getActions().stream().map(action -> new DeviceActionAvro(
                        action.getSensorId(),
                        ActionTypeAvro.valueOf(action.getType().name()),
                        action.getValue()
                )).toList()
        );
    }
}
