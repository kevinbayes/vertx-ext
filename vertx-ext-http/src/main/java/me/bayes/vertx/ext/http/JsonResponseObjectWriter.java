/**
 *    Copyright 2014 Bayes Technologies
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package me.bayes.vertx.ext.http;

import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>
 * Write an object to response body as json.
 * </p>
 * 
 * TODO: Put a more comprehensive solution in place.
 * 
 * @author Kevin Bayes
 * @since 1.0.0
 */
public class JsonResponseObjectWriter implements ResponseObjectWriter {
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	/* (non-Javadoc)
	 * @see me.bayes.vertx.ext.http.ResponseObjectWriter#writeResponse(org.vertx.java.core.http.HttpServerResponse, java.lang.Object)
	 */
	public void writeResponse(HttpServerResponse response, Object object) throws JsonProcessingException {
		if(object instanceof JsonObject) {
			response.end(((JsonObject)object).toString());
		} else {
			response.end(mapper.writeValueAsString(object));
		}
	}

}
