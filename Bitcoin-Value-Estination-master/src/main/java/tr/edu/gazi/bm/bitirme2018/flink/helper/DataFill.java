package tr.edu.gazi.bm.bitirme2018.flink.helper;

import com.google.gson.*;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class DataFill<T extends Serializable> {


    public List<T> getDatalistEntity(Class<T> clazz, String strJson) {
        List<T> lst = new LinkedList<T>();
        try {
            GsonBuilder builder = new GsonBuilder();

            builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                @Override
                public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                        throws JsonParseException {
                    return new Date(json.getAsJsonPrimitive().getAsLong());
                }
            });

            Gson gson = builder.create();
            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(strJson);

            if (jsonElement instanceof JsonArray) {
                for (final JsonElement json : jsonElement.getAsJsonArray()) {
                    T entity = gson.fromJson(json, clazz);
                    lst.add(entity);
                }

            } else if (jsonElement instanceof JsonObject) {
                T entity = gson.fromJson(jsonElement, clazz);
                lst.add(entity);
            }
            return lst;

        } catch (Exception e) {
            e.printStackTrace();
            return new LinkedList<T>();
        }

    }

}

