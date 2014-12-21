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

import java.util.regex.Pattern;

import static me.bayes.vertx.ext.http.HttpMediaTypes.*;

/**
 * <p>
 * A factory to create a response writer based on a media type string.
 * </p>
 * 
 * @author Kevin Bayes
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public abstract class ResponseObjectWriterFactory {
	
	private static Pattern PATTERN = Pattern.compile(HttpMediaTypeConstants.VALIDATE_MEDIA_TYPE_REGEX);
	
	private ResponseObjectWriterFactory() { }
	
	/**
	 * @param mediaTypeString type to try find a response object writer
	 * @return a response object writer or null
	 */
	public static ResponseObjectWriter getInstance(String mediaTypeString) {
		
		if(!PATTERN.matcher(mediaTypeString).find()) {
			return null;
		}
		
		final HttpMediaTypes mediaType = HttpMediaTypes.fromMediaTypeString(mediaTypeString);

		System.out.print("--" + mediaTypeString + "--");
		System.out.print("--" + mediaType + "--");
		
		switch(mediaType) {
		case APPLICATION_JSON:
		case ANY_SUB_TYPE_JSON:
			return new JsonResponseObjectWriter();
		case TEXT_HTML:
			return new HtmlResponseObjectWriter();
		default:
			return null;
		}
	}
}