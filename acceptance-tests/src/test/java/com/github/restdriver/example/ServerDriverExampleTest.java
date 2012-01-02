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

import static com.github.restdriver.example.TestDriver.*;
import static com.github.restdriver.serverdriver.Matchers.*;
import static com.github.restdriver.serverdriver.RestServerDriver.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import com.github.restdriver.serverdriver.http.Url;
import com.github.restdriver.serverdriver.matchers.HasResponseBody;
import com.github.restdriver.serverdriver.matchers.HasStatusCode;

public class ServerDriverExampleTest {

    /**
     * The simplest use-case for the server driver, a nice get call with a check of the response body.
     * 
     * We provide a {@link HasResponseBody} matcher which will take any Hamcrest string matcher and
     * will attempt to match the content of the response against it.
     */
    @Test
    public void getWithBodyCheck() {
        assertThat(get(baseUrl() + "/hello"), hasResponseBody(is("Hello world")));
    }
    
    /**
     * Rather than simply accept a String for the url parameter when performing a get, post, etc.
     * we can provide any Object and it will have its toString() method called. This allows us to provide
     * a {@link Url} class which helps build from a base URL and adding path segments and querystring parameters.
     */
    @Test
    public void getUsingUrlClass() {
        assertThat(get(hello()), hasResponseBody(containsString("world")));
    }
    
    /**
     * In addition to {@link HasResponseBody} we've provided a number of Hamcrest matchers which will help
     * with checking parts of responses.
     * 
     * In this case we use the {@link HasStatusCode} matcher to check that the status code of the response
     * is 200.
     */
    @Test
    public void getWithStatusCodeCheck() {
        assertThat(get(hello()), hasStatusCode(200));
    }
    
    /**
     * The {@link Url} instance that is returned from the {@link TestDriver#hello()} utility method can
     * have query parameters added to it.
     */
    @Test
    public void getUsingQueryParam() {
        Url url = hello().withParam("name", "Jeff");
        assertThat(getting(url), hasResponseBody(is("Hello Jeff")));
    }
    
}
