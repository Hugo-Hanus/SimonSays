package com.helmo.simonsays.database;

import androidx.room.TypeConverter;

import java.util.UUID;

public class ScoreTypeConverters {
    @TypeConverter
    public String fromUuid(UUID uuid){
        return uuid.toString();
    }
    @TypeConverter
    public UUID toUuid(String uuid){
        return UUID.fromString(uuid);
    }
}
