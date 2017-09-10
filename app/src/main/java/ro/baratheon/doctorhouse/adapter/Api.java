package ro.baratheon.doctorhouse.adapter;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import ro.baratheon.doctorhouse.entity.Category;
import ro.baratheon.doctorhouse.entity.Product;

public class Api {
    private String endPoint;

    public Api(String endPoint) {
        this.endPoint = endPoint;
    }

    public ArrayList<Product> getProducts() {
        try {
            ArrayList<Product> list = new ArrayList<Product>();
            URL url = new URL(this.endPoint);
            InputStreamReader reader = new InputStreamReader(url.openStream());
            ArrayList<JsonObject> jsonObjects = new Gson().fromJson(reader, new TypeToken<ArrayList<JsonObject>>() {
            }.getType());
            for (JsonObject jsonObject : jsonObjects) {
                JsonObject category = jsonObject.getAsJsonObject("category");
                JsonElement modelObject = jsonObject.get("model_object");
                JsonElement modelTexture = jsonObject.get("model_texture");

                list.add(
                        new Product(
                                jsonObject.get("id").getAsInt(),
                                jsonObject.get("name").getAsString(),
                                jsonObject.get("price").getAsDouble(),
                                jsonObject.get("description").getAsString(),
                                new Category(
                                        category.get("id").getAsInt(),
                                        category.get("name").getAsString()
                                ),
                                jsonObject.get("image").getAsString(),
                                modelObject != null ? modelObject.getAsString() : "",
                                modelTexture != null ? modelTexture.getAsString() : "",
                                jsonObject.get("is_ar_compatible").getAsBoolean()
                        )
                );
            }
            return list;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return new ArrayList<Product>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<Product>();
        }
    }
}
