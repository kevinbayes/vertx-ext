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

/**
 * <p>The classes as specified in the link below.</p>
 * <ul>
 * <li><a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html">HTTP/1.1 documentation</a></li>
 * </ul> 
 * 
 * @author Kevin Bayes
 * @since 1.0.0
 */
public enum StatusClass {
	INFORMATIONAL,
	SUCCESSFUL,
	REDIRECTION,
	CLIENT_ERROR,
	SERVER_ERROR,
	NOT_RECOGNIZED;
	
	
	public static StatusClass getByStatus(int statusCode) {
		switch (statusCode / 100) {
        case 1:
            return StatusClass.INFORMATIONAL;
        case 2:
            return StatusClass.SUCCESSFUL;
        case 3:
            return StatusClass.REDIRECTION;
        case 4:
            return StatusClass.CLIENT_ERROR;
        case 5:
            return StatusClass.SERVER_ERROR;
        default:
            return StatusClass.NOT_RECOGNIZED;
		}
	}

}
