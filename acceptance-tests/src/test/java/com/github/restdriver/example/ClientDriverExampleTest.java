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

import static com.github.restdriver.clientdriver.RestClientDriver.*;
import static com.github.restdriver.example.TestDriver.*;
import static com.github.restdriver.serverdriver.Matchers.*;
import static com.github.restdriver.serverdriver.RestServerDriver.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Rule;
import org.junit.Test;

import com.github.restdriver.clientdriver.ClientDriverRequest.Method;
import com.github.restdriver.clientdriver.ClientDriverRule;
import com.github.restdriver.serverdriver.http.response.Response;

public class ClientDriverExampleTest {
    
    /**
     * We use JUnit's {@link Rule} annotation to simplify the setup/teardown of the client driver.
     * 
     * At the end of the test the rule will verify that the requests performed against the client driver
     * match the expectations made against it.
     * 
     * Note - in this example we ask the client driver to listen on a specific port, one which has been
     * passed in via the system properties (or via a default). This enables us to package the running
     * webapp to use the correct port in its own dependency injection.
     */
    @Rule
    public ClientDriverRule clientDriver = new ClientDriverRule(clientDriverPort());
    
    /**
     * This test performs a post to our fictional echo service. Expecting to receive a POST request
     * with a specific body and pass the same string back out.
     */
    @Test
    public void easyEcho() {
        
        clientDriver.addExpectation(
                onRequestTo("/service/echo").withMethod(Method.POST).withBody("hello", "text/plain"),
                giveResponse("hello"));
        
        Response response = post(echo(), body("hello", "text/plain"));
        
        assertThat(response, hasStatusCode(200));
        assertThat(response, hasResponseBody(is("hello")));
        
    }
    
}
