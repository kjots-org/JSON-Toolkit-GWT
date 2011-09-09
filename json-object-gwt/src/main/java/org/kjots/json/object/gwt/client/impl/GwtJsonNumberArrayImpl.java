/* 
 * Copyright © 2009 Karl J. Ots <kjots@kjots.org>
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

import com.google.gwt.core.client.JsArrayNumber;

import org.kjots.json.object.shared.JsonNumberArray;

/**
 * GWT JSON Number Array Implementation.
 * <p>
 * Created: 7th November 2009.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since json-object-0.1
 */
public class GwtJsonNumberArrayImpl extends GwtJsonArrayImpl implements JsonNumberArray {
  /**
   * Construct a new GWT JSON Number Array Implementation.
   *
   * @param jsNumberArray The JavaScript number array.
   */
  public GwtJsonNumberArrayImpl(JsArrayNumber jsNumberArray) {
    super(JsonNumberArray.class, jsNumberArray);
  }

  /**
   * Retrieve the numeric value of the element at the given index.
   *
   * @param index The index.
   * @return The numeric value.
   * @see #set(int, Number)
   */
  @Override
  public final Number get(int index) {
    return this.getNumberElement(index);
  }
  
  /**
   * Set the element at the given index to the given numeric value.
   *
   * @param index The index.
   * @param value The numeric value.
   * @see #get(int)
   */
  @Override
  public final void set(int index, Number value) {
    this.setNumberElement(index, value);
  }
  
  /**
   * Insert the given numeric value at the given index.
   *
   * @param index The index.
   * @param value The numeric value.
   */
  @Override
  public final void insert(int index, Number value) {
    this.insertNumberElement(index, value);
  }

  /**
   * Prepend the given numeric value.
   *
   * @param value The numeric value.
   */
  @Override
  public final void prepend(Number value) {
    this.prependNumberElement(value);
  }
  
  /**
   * Append the given numeric value.
   *
   * @param value The numeric value.
   */
  @Override
  public final void append(Number value) {
    this.appendNumberElement(value);
  }

  /**
   * Retrieve an iterator for the array.
   *
   * @return The iterator.
   */
  @Override
  public java.util.Iterator<Number> iterator() {
    return new Iterator(this);
  }
}
