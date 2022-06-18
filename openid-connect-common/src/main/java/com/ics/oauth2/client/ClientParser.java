package com.ics.oauth2.client;

import com.google.common.base.Strings;
import com.google.gson.JsonObject;
import com.ics.oauth2.exception.ParseException;
import com.ics.oauth2.id.ClientID;
import com.ics.oauth2.id.ClientSecret;
import com.ics.oauth2.utils.JsonUtils;

import java.net.URI;
import java.util.Date;

import static com.ics.oauth2.client.ClientDetailsParameters.*;

public class ClientParser {

    private ClientParser(){}

    public static ClientID parseClientID(final JsonObject jsonObject) throws ParseException {
        return new ClientID(JsonUtils.getAsString(jsonObject, CLIENT_ID));
    }

    public static Date parseClientIDIssuedAt(final JsonObject jsonObject) throws ParseException{
       return  JsonUtils.getAsDate(jsonObject, CLIENT_ID_ISSUED_AT);
    }

    public static ClientSecret parseClientSecret(final JsonObject jsonObject) throws ParseException{

        if (jsonObject.has(CLIENT_SECRET)){

            String val = JsonUtils.getAsString(jsonObject, CLIENT_SECRET);
            if (!Strings.isNullOrEmpty(val)) {
                Date expDate = null;

                if (jsonObject.has(CLIENT_SECRET_EXPIRES_AT)) {
                    expDate = JsonUtils.getAsDate(jsonObject, CLIENT_SECRET_EXPIRES_AT);
                }

                return new ClientSecret(val, expDate);
            }
        }
        return null;
    }

    public static URI parseRedirectURI(final JsonObject jsonObject) throws ParseException{
        return JsonUtils.getAsURI(jsonObject, CLIENT_REGISTER_URI);
    }


}
