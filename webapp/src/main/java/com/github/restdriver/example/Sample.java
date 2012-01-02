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

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/")
public class Sample {
    
    private final EchoClient echoClient;
    
    public Sample(EchoClient echoClient) {
        this.echoClient = echoClient;
    }
    
    @GET
    @Path("/hello")
    public final String getSomething(@QueryParam("name") String name) {
        if (name == null) {
            return "Hello world";
        } else {
            return "Hello " + name;
        }
    }
    
    @GET
    @Path("/person")
    @Produces(MediaType.APPLICATION_JSON)
    public final String produceJson() {
        return "{\"name\":\"Jeff\",\"age\":42}";
    }
    
    @GET
    @Path("/person")
    @Produces(MediaType.APPLICATION_XML)
    public final String produceXml() {
        return "<person name='Jeff' age='42' />";
    }
    
    @POST
    @Path("/echo")
    public final String echo(String content) {
        return echoClient.echo(content);
    }
    
}
