/**
 * Copyright © 2010-2011 Nokia
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.restdriver.example;

import static com.github.restdriver.serverdriver.RestServerDriver.*;

import com.github.restdriver.serverdriver.http.Url;

/**
 * Typically we create a helper class to house logic which builds test URLs.
 * 
 * It helps to make the tests readable.
 */
public final class TestDriver {
    
    private static final String DEFAULT_BASE_URL = "http://localhost:8080/example";
    
    private TestDriver() {
    }
    
    /**
     * We expose the base URL of the service which is either taken from a system property or uses the
     * hard-coded default.
     */
    public static String baseUrl() {
        return System.getProperty("baseUrl", DEFAULT_BASE_URL);
    }
    
    public static int clientDriverPort() {
        return Integer.valueOf(System.getProperty("clientDriverPort", "8081"));
    }
    
    /**
     * An example of using the {@link Url} class to create a URL.
     */
    public static Url hello() {
        return url(baseUrl()).withPath("hello"); 
    }
    
    public static Url person() {
        return url(baseUrl()).withPath("person");
    }
    
    public static Url echo() {
        return url(baseUrl()).withPath("echo");
    }
    
}
