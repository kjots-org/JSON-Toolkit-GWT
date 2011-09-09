/* 
 * Copyright © 2010 Karl J. Ots <kjots@kjots.org>
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kjots.json.object.gwt.client.impl;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayBoolean;
import com.google.gwt.core.client.JsArrayNumber;
import com.google.gwt.core.client.JsArrayString;

import org.kjots.json.object.shared.JsonArray;
import org.kjots.json.object.shared.JsonBooleanArray;
import org.kjots.json.object.shared.JsonBooleanMap;
import org.kjots.json.object.shared.JsonNumberArray;
import org.kjots.json.object.shared.JsonNumberMap;
import org.kjots.json.object.shared.JsonObject;
import org.kjots.json.object.shared.JsonObjectArray;
import org.kjots.json.object.shared.JsonObjectFactory;
import org.kjots.json.object.shared.JsonObjectMap;
import org.kjots.json.object.shared.JsonPropertyAdapter;
import org.kjots.json.object.shared.JsonStringArray;
import org.kjots.json.object.shared.JsonStringMap;

/**
 * GWT JSON Object Factory Implementation Base.
 * <p>
 * Created: 12th January 2010.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since json-object-0.5
 */
public abstract class GwtJsonObjectFactoryImplBase extends JsonObjectFactory {
  /**
   * JSON Object Instantiator.
   */
  protected interface JsonObjectInstantiator<T extends JsonObject> {
    /**
     * Create a new JSON object instance with the given underlying JSON object.
     *
     * @param object The underlying JSON object.
     * @return The JSON object.
     */
    public T newInstance(JavaScriptObject object);
  }
  
  /**
   * JSON Property Adaptor Instantiator.
   * <p>
   * Created: 15th May 2011.
   *
   * @since 1.2
   */
  protected interface JsonPropertyAdapterInstantiator<T extends JsonPropertyAdapter<?, ?>> {
    /**
     * Create a new JSON property adapter instance.
     *
     * @return The JSON property adapter.
     */
    public T newInstance();
  }
  
  /** The JSON object instantiators. */
  private final Map<String, JsonObjectInstantiator<?>> jsonObjectInstantiators = new HashMap<String, JsonObjectInstantiator<?>>();
  
  /** The JSON property adapter instantiators. */
  private final Map<Class<?>, JsonPropertyAdapterInstantiator<?>> jsonPropertyAdapterInstantiators = new HashMap<Class<?>, JsonPropertyAdapterInstantiator<?>>();
  
  /**
   * Create a new JSON object with the given underlying JSON object.
   *
   * @param <T> The type of the JSON object.
   * @param jsonObjectClass The class of the JSON object.
   * @param object The underlying JSON object.
   * @return The JSON object.
   */
  @Override
  public final <T extends JsonObject> T createJsonObject(Class<T> jsonObjectClass, Object object) {
    return this.getJsonObjectInstantiator(jsonObjectClass).newInstance((JavaScriptObject)object);
  }
  
  /**
   * Create a new JSON object with the given underlying JSON object.
   *
   * @param <T> The type of the JSON object.
   * @param jsonObjectClassName The name of the class of the JSON object.
   * @param object The underlying JSON object.
   * @return The JSON object.
   */
  @Override
  public final <T extends JsonObject> T createJsonObject(String jsonObjectClassName, Object object) {
    return this.<T>getJsonObjectInstantiator(jsonObjectClassName).newInstance((JavaScriptObject)object);
  }

  /**
   * Create a new JSON object.
   *
   * @param <T> The type of the JSON object.
   * @param jsonObjectClass The class of the JSON object.
   * @return The JSON object.
   */
  @Override
  public final <T extends JsonObject> T createJsonObject(Class<T> jsonObjectClass) {
    return this.getJsonObjectInstantiator(jsonObjectClass).newInstance(JavaScriptObject.createObject());
  }
  /**
   * Create a new JSON object.
   *
   * @param <T> The type of the JSON object.
   * @param jsonObjectClassName The name of the class of the JSON object.
   * @return The JSON object.
   */
  @Override
  public final <T extends JsonObject> T createJsonObject(String jsonObjectClassName) {
    return this.<T>getJsonObjectInstantiator(jsonObjectClassName).newInstance(JavaScriptObject.createObject());
  }
  
  /**
   * Create a new JSON array.
   *
   * @param <T> The type of the JSON array.
   * @param jsonArrayClass The class of the JSON array.
   * @return The JSON array.
   */
  @Override
  public final <T extends JsonArray> T createJsonArray(Class<T> jsonArrayClass) {
    return this.getJsonObjectInstantiator(jsonArrayClass).newInstance(JavaScriptObject.createArray());
  }
  
  /**
   * Create a new JSON array.
   *
   * @param <T> The type of the JSON array.
   * @param jsonArrayClassName The name of the class of the JSON array.
   * @return The JSON array.
   */
  @Override
  public final <T extends JsonArray> T createJsonArray(String jsonArrayClassName) {
    return this.<T>getJsonObjectInstantiator(jsonArrayClassName).newInstance(JavaScriptObject.createArray());
  }
  
  /**
   * Retrieve the JSON property adapter with the given class.
   *
   * @param <T> The type of the JSON property adapter.
   * @param jsonPropertyAdapterClass The class of the JSON property adapter.
   * @return The JSON property adapter.
   */
  @Override
  public final <T extends JsonPropertyAdapter<?, ?>> T getJsonPropertyAdapter(Class<T> jsonPropertyAdapterClass) {
    return this.<T>getJsonPropertyAdapterInstantiator(jsonPropertyAdapterClass).newInstance();
  }
  
  /**
   * Construct a new GWT JSON Object Factory Implementation Base.
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  protected GwtJsonObjectFactoryImplBase() {
    this.registerJsonObjectInstantiator(JsonObject.class, new JsonObjectInstantiator<JsonObject>() {
      @Override
      public final JsonObject newInstance(JavaScriptObject jsObject) {
        return new GwtJsonObjectImpl(jsObject);
      }
    });
    
    this.registerJsonObjectInstantiator(JsonArray.class, new JsonObjectInstantiator<JsonArray>() {
      @Override
      public final JsonArray newInstance(JavaScriptObject jsObject) {
        return new GwtJsonArrayImpl(jsObject);
      }
    });
    
    this.registerJsonObjectInstantiator(JsonBooleanArray.class, new JsonObjectInstantiator<JsonBooleanArray>() {
      @Override
      public final JsonBooleanArray newInstance(JavaScriptObject jsObject) {
        return new GwtJsonBooleanArrayImpl((JsArrayBoolean)jsObject.cast());
      }
    });
    
    this.registerJsonObjectInstantiator(JsonNumberArray.class, new JsonObjectInstantiator<JsonNumberArray>() {
      @Override
      public final JsonNumberArray newInstance(JavaScriptObject jsObject) {
        return new GwtJsonNumberArrayImpl((JsArrayNumber)jsObject.cast());
      }
    });
    
    this.registerJsonObjectInstantiator(JsonStringArray.class, new JsonObjectInstantiator<JsonStringArray>() {
      @Override
      public final JsonStringArray newInstance(JavaScriptObject jsObject) {
        return new GwtJsonStringArrayImpl((JsArrayString)jsObject.cast());
      }
    });
    
    this.registerJsonObjectInstantiator(JsonObjectArray.class, new JsonObjectInstantiator<JsonObjectArray>() {
      @Override
      public final JsonObjectArray newInstance(JavaScriptObject jsObject) {
        return new GwtJsonObjectArrayImpl((JsArray<?>)jsObject.cast());
      }
    });
    
    this.registerJsonObjectInstantiator(JsonBooleanMap.class, new JsonObjectInstantiator<JsonBooleanMap>() {
      @Override
      public final JsonBooleanMap newInstance(JavaScriptObject jsObject) {
        return new GwtJsonBooleanMapImpl(jsObject);
      }
    });
    
    this.registerJsonObjectInstantiator(JsonNumberMap.class, new JsonObjectInstantiator<JsonNumberMap>() {
      @Override
      public final JsonNumberMap newInstance(JavaScriptObject jsObject) {
        return new GwtJsonNumberMapImpl(jsObject);
      }
    });
    
    this.registerJsonObjectInstantiator(JsonStringMap.class, new JsonObjectInstantiator<JsonStringMap>() {
      @Override
      public final JsonStringMap newInstance(JavaScriptObject jsObject) {
        return new GwtJsonStringMapImpl(jsObject);
      }
    });
    
    this.registerJsonObjectInstantiator(JsonObjectMap.class, new JsonObjectInstantiator<JsonObjectMap>() {
      @Override
      public final JsonObjectMap newInstance(JavaScriptObject jsObject) {
        return new GwtJsonObjectMapImpl(jsObject);
      }
    });
  }
  
  /**
   * Register the given instantiator for JSON objects with the given class. 
   *
   * @param <T> The type of the JSON object.
   * @param jsonObjectClass The class of the JSON object.
   * @param jsonObjectInstantiator The JSON object instantiator.
   */
  protected final <T extends JsonObject> void registerJsonObjectInstantiator(Class<T> jsonObjectClass, JsonObjectInstantiator<T> jsonObjectInstantiator) {
    this.jsonObjectInstantiators.put(jsonObjectClass.getName(), jsonObjectInstantiator);
  }
  
  /**
   * Register the given instantiator for JSON property adapters with the given class.
   *
   * @param <T> The type of the JSON property adapter.
   * @param jsonPropertyAdapterClass The class of the JSON property adapter.
   * @param jsonPropertyAdapterInstantiator The JSON property adapter instantiator.
   */
  protected final <T extends JsonPropertyAdapter<?, ?>> void registerJsonPropertyAdapterInstantiator(Class<T> jsonPropertyAdapterClass, JsonPropertyAdapterInstantiator<T> jsonPropertyAdapterInstantiator) {
    this.jsonPropertyAdapterInstantiators.put(jsonPropertyAdapterClass, jsonPropertyAdapterInstantiator);
  }
  
  /**
   * Retrieve the instantiator for JSON objects with the given class. 
   *
   * @param <T> The type of the JSON object.
   * @param jsonObjectClass The class of the JSON object.
   * @return The JSON object instantiator.
   */
  @SuppressWarnings("unchecked")
  private <T extends JsonObject> JsonObjectInstantiator<T> getJsonObjectInstantiator(Class<T> jsonObjectClass) {
    return (JsonObjectInstantiator<T>)this.jsonObjectInstantiators.get(jsonObjectClass.getName());
  }
  
  /**
   * Retrieve the instantiator for JSON objects with the given class name. 
   *
   * @param <T> The type of the JSON object.
   * @param jsonObjectClassName The name of the  class of the JSON object.
   * @return The JSON object instantiator.
   */
  @SuppressWarnings("unchecked")
  private <T extends JsonObject> JsonObjectInstantiator<T> getJsonObjectInstantiator(String jsonObjectClassName) {
    if (!this.jsonObjectInstantiators.containsKey(jsonObjectClassName)) {
      throw new IllegalArgumentException(jsonObjectClassName);
    }
    
    return (JsonObjectInstantiator<T>)this.jsonObjectInstantiators.get(jsonObjectClassName);
  }
  
  /**
   * Retrieve the instantiator for JSON property adapters with the given class.
   *
   * @param <T> The type of the JSON property adapter.
   * @param jsonPropertyAdapterClass The class of the JSON property adapter.
   * @return The JSON property adapter instantiator.
   */
  @SuppressWarnings("unchecked")
  private <T extends JsonPropertyAdapter<?, ?>> JsonPropertyAdapterInstantiator<T> getJsonPropertyAdapterInstantiator(Class<T> jsonPropertyAdapterClass) {
    if (!this.jsonPropertyAdapterInstantiators.containsKey(jsonPropertyAdapterClass)) {
      throw new IllegalArgumentException(jsonPropertyAdapterClass.getName());
    }
    
    return (JsonPropertyAdapterInstantiator<T>)this.jsonPropertyAdapterInstantiators.get(jsonPropertyAdapterClass);
  }
}
