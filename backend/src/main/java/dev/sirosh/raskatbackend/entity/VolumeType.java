package dev.sirosh.raskatbackend.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
public enum VolumeType {
    FileSystem("fileSystem");
    private final String name;

    static final Map<String, VolumeType> STRING_TO_ENUM_MAP;

    static {
        Map<String, VolumeType> map = new ConcurrentHashMap<>();
        for (VolumeType instance : VolumeType.values()) {
            map.put(instance.toString().toLowerCase(), instance);
        }
        STRING_TO_ENUM_MAP = Collections.unmodifiableMap(map);
    }

    @JsonCreator
    public static VolumeType get(String value) {
        return Optional.ofNullable(STRING_TO_ENUM_MAP.get(value.toLowerCase()))
                .orElseThrow(() -> new IllegalArgumentException(value + " is not legal volume type"));
    }

    @Override
    public String toString() {
        return name;
    }
}
