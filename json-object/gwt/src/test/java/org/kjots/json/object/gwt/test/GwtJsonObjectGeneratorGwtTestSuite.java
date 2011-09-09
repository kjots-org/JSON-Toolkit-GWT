/* 
 * Copyright © 2011 Karl J. Ots <kjots@kjots.org>
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
package org.kjots.json.object.gwt.test;

import com.google.gwt.junit.tools.GWTTestSuite;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.kjots.json.object.gwt.client.GwtJsonObjectGeneratorAutoAdaptedPropertyGwtTest;
import org.kjots.json.object.gwt.client.GwtJsonObjectGeneratorBooleanGwtTest;
import org.kjots.json.object.gwt.client.GwtJsonObjectGeneratorExceptionGwtTest;
import org.kjots.json.object.gwt.client.GwtJsonObjectGeneratorFunctionGwtTest;
import org.kjots.json.object.gwt.client.GwtJsonObjectGeneratorGwtTest;
import org.kjots.json.object.gwt.client.GwtJsonObjectGeneratorNumberGwtTest;
import org.kjots.json.object.gwt.client.GwtJsonObjectGeneratorObjectGwtTest;
import org.kjots.json.object.gwt.client.GwtJsonObjectGeneratorPrimitiveGwtTest;
import org.kjots.json.object.gwt.client.GwtJsonObjectGeneratorStringGwtTest;

/**
 * GWT JSON Object Generator GWT Test Suite.
 * <p>
 * Created: 9th February 2011.
 *
 * @author <a href="mailto:kjots@kjots.org">Karl J. Ots &lt;kjots@kjots.org&gt;</a>
 * @since 1.1
 */
public class GwtJsonObjectGeneratorGwtTestSuite extends GWTTestSuite {
  /**
   * Create the test suite.
   *
   * @return The test suite.
   */
  public static Test suite() {
    GwtJsonObjectGeneratorGwtTestSuite suite = new GwtJsonObjectGeneratorGwtTestSuite();
    
    addTestSuites(suite);
    
    return suite;
  }
  
  /**
   * Add the test suites.
   *
   * @param suite The test suite.
   */
  public static void addTestSuites(TestSuite suite) {
    suite.addTestSuite(GwtJsonObjectGeneratorGwtTest.class);
    
    suite.addTestSuite(GwtJsonObjectGeneratorPrimitiveGwtTest.class);
    suite.addTestSuite(GwtJsonObjectGeneratorBooleanGwtTest.class);
    suite.addTestSuite(GwtJsonObjectGeneratorNumberGwtTest.class);
    suite.addTestSuite(GwtJsonObjectGeneratorStringGwtTest.class);
    suite.addTestSuite(GwtJsonObjectGeneratorObjectGwtTest.class);
    
    suite.addTestSuite(GwtJsonObjectGeneratorAutoAdaptedPropertyGwtTest.class);
    
    suite.addTestSuite(GwtJsonObjectGeneratorFunctionGwtTest.class);
    suite.addTestSuite(GwtJsonObjectGeneratorExceptionGwtTest.class);
  }
  
  /**
   * Construct a new GWT JSON Object Generator GWT Test Suite.
   * <p>
   * This constructor is declared <code>private</code> to prevent external
   * instantiation.
   */
  private GwtJsonObjectGeneratorGwtTestSuite() {
    super("GWT Json Object Generator GWT Test Suite");
  }
}
