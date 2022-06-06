package dev.sirosh.raskatbackend.entity;


import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
public enum PortType {
    TCP("tcp"),
    UDP("udp"),
    SCTP("sctp");
    private final String name;

    static final Map<String, PortType> STRING_TO_ENUM_MAP;

    static {
        Map<String, PortType> map = new ConcurrentHashMap<>();
        for (PortType instance : PortType.values()) {
            map.put(instance.toString().toLowerCase(), instance);
        }
        STRING_TO_ENUM_MAP = Collections.unmodifiableMap(map);
    }

    @JsonCreator
    public static PortType get(String value) {
        return Optional.ofNullable(STRING_TO_ENUM_MAP.get(value.toLowerCase()))
                .orElseThrow(() -> new IllegalArgumentException(value + " is not legal port type"));
    }

    @Override
    public String toString() {
        return name;
    }
}
