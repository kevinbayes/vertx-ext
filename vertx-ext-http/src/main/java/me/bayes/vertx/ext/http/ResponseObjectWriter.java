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

/**
 * A {@link ResponseObjectWriter} takes an object and makes the body of the HTTP Response
 * the object represented as the type specified in the header Content-Type.
 * 
 * @author Kevin Bayes
 * @since 1.0.0
 */
public interface ResponseObjectWriter {
	
	/**
	 * @param response http response object
	 * @param object to form the body of the response.
	 */
	void writeResponse(HttpServerResponse response, Object object) throws Exception;

}
