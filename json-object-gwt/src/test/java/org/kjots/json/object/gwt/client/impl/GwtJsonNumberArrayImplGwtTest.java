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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayNumber;

import org.kjots.json.object.gwt.client.GwtJsonObjectGwtTestBase;
import org.kjots.json.object.shared.JsonNumberArray;
import org.kjots.json.object.shared.impl.JsonNumberArrayImplTestBase;

/**
 * GWT JSON Number Array Implementation GWT Test.
 * <p>
 * Created: 7th December 2009.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since json-object-0.1
 */
public class GwtJsonNumberArrayImplGwtTest extends GwtJsonObjectGwtTestBase {
  /** The JSON number array implementation test delegate. */
  private final JsonNumberArrayImplTestBase<JavaScriptObject> jsonNumberArrayImplTestDelegate = new JsonNumberArrayImplTestBase<JavaScriptObject>() {;
    @Override
    protected JsonNumberArray createJsonNumberArray(JavaScriptObject array) {
      return new GwtJsonNumberArrayImpl((JsArrayNumber)array.cast());
    }

    @Override
    protected JavaScriptObject createUnderlyingJsonArray() {
      return JavaScriptObject.createArray();
    }
    
    @Override
    protected final native int getArrayLength(JavaScriptObject array) /*-{
      return array.length;
    }-*/;
    
    @Override
    protected final native Number getNumberElement(JavaScriptObject array, int elementIndex) /*-{
      var jsElementValue = array[elementIndex];
      
      return jsElementValue != null ? @java.lang.Double::valueOf(D)(jsElementValue) : null;
    }-*/;
    
    @Override
    protected final native void setNumberElement(JavaScriptObject array, int elementIndex, Number elementValue) /*-{
      var jsElementValue = elementValue != null ? elementValue.@java.lang.Number::doubleValue()() : null;
      
      array[elementIndex] = jsElementValue;
    }-*/;
  };
  
  /**
   * @see JsonNumberArrayImplTestBase#testGet()
   */
  public void testGet() {
    this.jsonNumberArrayImplTestDelegate.testGet();
  }

  /**
   * @see JsonNumberArrayImplTestBase#testSet()
   */
  public void testSet() {
    this.jsonNumberArrayImplTestDelegate.testSet();
  }

  /**
   * @see JsonNumberArrayImplTestBase#testInsert()
   */
  public void testInsert() {
    this.jsonNumberArrayImplTestDelegate.testInsert();
  }

  /**
   * @see JsonNumberArrayImplTestBase#testPrepend()
   */
  public void testPrepend() {
    this.jsonNumberArrayImplTestDelegate.testPrepend();
  }

  /**
   * @see JsonNumberArrayImplTestBase#testAppend()
   */
  public void testAppend() {
    this.jsonNumberArrayImplTestDelegate.testAppend();
  }
}
