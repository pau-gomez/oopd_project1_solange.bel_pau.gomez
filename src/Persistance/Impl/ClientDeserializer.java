package Persistance.Impl;

import Buisness.Entities.Client;
import Buisness.Entities.CorporateClient;
import Buisness.Entities.OnlineClient;
import Buisness.Entities.PhoneNumber;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Custom Gson deserializer for Client objects.
 * Reads "client_type" and instantiates the correct subclass.
 */
public class ClientDeserializer implements JsonDeserializer<Client> {

    /**
     * Deserializes a JSON element into the correct Client subclass
     * based on the "client_type" field.
     *
     * @param json the JSON element to deserialize
     * @param typeOfT the type of the object to deserialize to
     * @param context the deserialization context
     * @return the deserialized Client object (OnlineClient, CorporateClient, or Client)
     * @throws JsonParseException if the JSON is malformed or missing required fields
     */
    @Override
    public Client deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        JsonObject obj = json.getAsJsonObject();

        // Skip malformed entries that are missing required fields
        if (!obj.has("client_type") || !obj.has("client_id")) {
            throw new JsonParseException("Malformed client entry: missing client_type or client_id");
        }

        String type = obj.get("client_type").getAsString();
        int clientId = obj.get("client_id").getAsInt();
        List<PhoneNumber> phones = parsePhoneNumbers(obj);

        switch (type) {
            case "online": {
                if (!obj.has("full_name") || !obj.has("address") || !obj.has("contact_email")) {
                    throw new JsonParseException("Malformed online client entry: missing required fields");
                }
                String fullName = obj.get("full_name").getAsString();
                String address  = obj.get("address").getAsString();
                String email    = obj.get("contact_email").getAsString();
                return new OnlineClient(clientId, fullName, phones, address, email);
            }
            case "corporate": {
                if (!obj.has("contact_name") || !obj.has("cif") || !obj.has("billing_address") || !obj.has("mailing_address")) {
                    throw new JsonParseException("Malformed corporate client entry: missing required fields");
                }
                String contactName    = obj.get("contact_name").getAsString();
                String cif            = obj.get("cif").getAsString();
                String billingAddress = obj.get("billing_address").getAsString();
                String mailingAddress = obj.get("mailing_address").getAsString();
                return new CorporateClient(clientId, contactName, phones,
                        cif, contactName, billingAddress, mailingAddress);
            }
            default: {
                if (!obj.has("full_name")) {
                    throw new JsonParseException("Malformed regular client entry: missing full_name");
                }
                String fullName = obj.get("full_name").getAsString();
                return new Client(clientId, type, fullName, phones);
            }
        }
    }

    /**
     * Parses the phone_numbers JSON array into a list of PhoneNumber objects.
     *
     * @param obj the JSON object containing the phone_numbers array
     * @return list of parsed PhoneNumber objects, or empty list if none present
     */
    private List<PhoneNumber> parsePhoneNumbers(JsonObject obj) {
        List<PhoneNumber> phones = new ArrayList<>();
        if (!obj.has("phone_numbers") || obj.get("phone_numbers").isJsonNull()) return phones;

        for (JsonElement el : obj.getAsJsonArray("phone_numbers")) {
            JsonObject p = el.getAsJsonObject();
            phones.add(new PhoneNumber(p.get("country_prefix").getAsString(), p.get("number").getAsString()));
        }
        return phones;
    }
}