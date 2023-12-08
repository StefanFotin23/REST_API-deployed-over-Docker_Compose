package acs.sprc.rest.utility;

import acs.sprc.rest.entities.Temperature;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

public class TemperatureSerializer extends JsonSerializer<Temperature> {
    @Override
    public void serialize(Temperature temperature, JsonGenerator jsonGenerator, SerializerProvider serializerProvider){
        try {
            jsonGenerator.writeStartObject();

            // Serialize fields excluding idOras
            jsonGenerator.writeNumberField("id", temperature.getId());
            jsonGenerator.writeNumberField("valoare", temperature.getValoare());
            jsonGenerator.writeObjectField("timestamp", temperature.getTimestamp());

            jsonGenerator.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}