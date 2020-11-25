package de.stvehb.loki.parser;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * This adapter allows deserialization of <i>json objects</i> into <i>java arrays</i> while
 * injecting the <i>key</i> of every entry into a specific <i>field</i> of the <i>target class</i>.
 *
 * <p>
 *     Example: Given is the following json structure:
 *     <pre>
 *     {@code
 *        {
 *     		"carBrands": {
 *     			"audi": { "country": "Germany", "headquarter": "Ingolstadt" },
 *     			"Volkswagen": { "country": "Germany", "headquarter": "Wolfsburg" },
 *     			"Tesla": { "country": "USA", "headquarter": "Palo Alto" },
 *     			"Honda": { "country": "Japan", "headquarter": "Tokio" }
 *            }
 *        }
 *     }
 *     </pre>
 *     
 *     This adapter would transform the json structure into the following <i>java code</i>:
 *     <pre>
 *     {@code
 *         private CarBrand[] carBrands;
 *     }
 *     </pre>
 *     <pre>
 *     {@code
 *         class CarBrand {
 *             String name; // <-- this field is the injected one, it gets the value of the key; example: Audi
 *             String country;
 *             String headquarter;
 *         }
 *     }
 *     </pre>
 * </p>
 */
@RequiredArgsConstructor
public class ObjectToArrayAdapter<T> implements JsonDeserializer<T>, JsonSerializer<T[]> {

	private final Class<?> clazz;
	private final Field keyField;

	@Override
	@SneakyThrows
	@SuppressWarnings("unchecked")
	public T deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
		this.keyField.setAccessible(true);

		// Iterate over every json object-property
		return (T) jsonElement.getAsJsonObject().entrySet().stream().map(entry -> {
			try {
				// Deserialize content of every entry to target class
				Object instance = context.deserialize(entry.getValue(), this.clazz);

				// Set the keyField to the key of the entry
				this.keyField.set(instance, entry.getKey());
				return instance;
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				return null;
			}
		}).collect(
			Collectors.toList()
		).toArray((T[]) Array.newInstance(this.clazz, 0));
	}

	@Override
	public JsonElement serialize(T[] objects, Type type, JsonSerializationContext context) {
		JsonObject jsonObject = new JsonObject();

		Arrays.stream(objects).forEach(entry -> {
			try {
				jsonObject.add(
					(String) this.keyField.get(entry),
					context.serialize(entry)
				);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		});

		return jsonObject;
	}

}
