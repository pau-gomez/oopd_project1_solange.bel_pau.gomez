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

    @Override
    public Client deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        JsonObject obj = json.getAsJsonObject();
        String type = obj.get("client_type").getAsString();
        int clientId = obj.get("client_id").getAsInt();
        List<PhoneNumber> phones = parsePhoneNumbers(obj);

        switch (type) {
            case "online": {
                String fullName = obj.get("full_name").getAsString();
                String address = obj.get("address").getAsString();
                String email = obj.get("contact_email").getAsString();
                return new OnlineClient(clientId, fullName, phones, address, email);
            }
            case "corporate": {
                String contactName = obj.get("contact_name").getAsString();
                String cif = obj.get("cif").getAsString();
                String billingAddress = obj.get("billing_address").getAsString();
                String mailingAddress = obj.get("mailing_address").getAsString();
                return new CorporateClient(clientId, contactName, phones, cif, contactName, billingAddress, mailingAddress);
            }
            default: {
                String fullName = obj.get("full_name").getAsString();
                return new Client(clientId, type, fullName, phones);
            }
        }
    }

    private List<PhoneNumber> parsePhoneNumbers(JsonObject obj) {
        List<PhoneNumber> phones = new ArrayList<>();
        if (!obj.has("phone_numbers") || obj.get("phone_numbers").isJsonNull()) return phones;

        for (JsonElement el : obj.getAsJsonArray("phone_numbers")) {
            JsonObject p = el.getAsJsonObject();
            phones.add(new PhoneNumber( p.get("country_prefix").getAsString(), p.get("number").getAsString()));
        }
        return phones;
    }
}