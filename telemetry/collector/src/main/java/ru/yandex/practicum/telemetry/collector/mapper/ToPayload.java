package ru.yandex.practicum.telemetry.collector.mapper;

import org.mapstruct.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Qualifier
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface ToPayload {
}
