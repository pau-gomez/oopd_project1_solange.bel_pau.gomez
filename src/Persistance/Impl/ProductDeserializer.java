package Persistance.Impl;

import Buisness.Entities.*;
import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * Custom Gson deserializer for Product objects that reads the product_type field and creates the correct Product subclass based on it.
 */
public class ProductDeserializer implements JsonDeserializer<Product> {

    @Override
    public Product deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        JsonObject obj = json.getAsJsonObject();
        String type = obj.get("product_type").getAsString();

        switch (type) {
            case "glasses":
                return new Glasses(
                        obj.get("product_id").getAsString(),
                        obj.get("product_name").getAsString(),
                        obj.get("brand").getAsString(),
                        obj.get("model").getAsString()
                );
            case "contact_lenses":
                return new ContactLenses(
                        obj.get("product_id").getAsString(),
                        obj.get("product_name").getAsString(),
                        obj.get("brand").getAsString(),
                        obj.get("model").getAsString(),
                        obj.get("graduation").getAsDouble(),
                        obj.get("number_of_lenses").getAsInt(),
                        obj.get("uv_filter").getAsBoolean()
                );
            case "consumable":
                return new Consumable(
                        obj.get("product_id").getAsString(),
                        obj.get("product_name").getAsString(),
                        obj.get("brand").getAsString(),
                        obj.get("model").getAsString(),
                        obj.get("volume").getAsInt(),
                        obj.get("expiration_date").getAsString(),
                        obj.get("preservatives").getAsBoolean()
                );
            case "service":
                return new Service(
                        obj.get("product_id").getAsString(),
                        obj.get("product_name").getAsString(),
                        obj.get("duration_hours").getAsDouble()
                );
            default:
                throw new JsonParseException("Unknown product type: " + type); // TODO: throw ok?
        }
    }
}