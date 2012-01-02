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

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * HTTP implementation of an {@link EchoClient}.
 */
public class HttpEchoClient implements EchoClient {
    
    private final String baseUrl;
    
    public HttpEchoClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }
    
    @Override
    public String echo(String content) {
        HttpPost post = new HttpPost(baseUrl + "/echo");
        
        try {
            post.setEntity(new StringEntity(content));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        
        HttpClient client = new DefaultHttpClient();
        
        HttpResponse response;
        
        try {
            response = client.execute(post);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        HttpEntity entity = response.getEntity();
        
        String received;
        
        try {
            received = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        return received;
    }
    
}
