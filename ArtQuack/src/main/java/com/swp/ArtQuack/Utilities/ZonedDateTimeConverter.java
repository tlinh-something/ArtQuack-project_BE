package com.swp.ArtQuack.Utilities;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ZonedDateTimeConverter implements AttributeConverter<ZonedDateTime, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(ZonedDateTime attribute) {
        if (attribute != null) {
            return Timestamp.valueOf(attribute.toLocalDateTime());
        }
        return null;
    }

    @Override
    public ZonedDateTime convertToEntityAttribute(Timestamp dbData) {
        if (dbData != null) {
            return ZonedDateTime.ofInstant(dbData.toInstant(), ZoneId.systemDefault());
        }
        return null;
    }
}
