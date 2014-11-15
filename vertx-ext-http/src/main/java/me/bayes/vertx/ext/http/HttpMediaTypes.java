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
 * <p>
 * Common types used in applications. Please add as you need them.
 * </p>
 * 
 * <p>
 * Reference: <a href="http://www.iana.org/assignments/media-types/media-types.xhtml">http://www.iana.org/assignments/media-types/media-types.xhtml</a>
 * </p>
 * 
 * @author Kevin Bayes
 * @since 1.0.0
 */
public enum HttpMediaTypes {
	//Wild card types
	ANY_SUB_TYPE_JSON(new HttpMediaType("application", "json")),
	
	//Application
	APPLICATION_JSON(new HttpMediaType("application", "json")),
	APPLICATION_XML(new HttpMediaType("application", "xml", true)),
	
	//Text
	TEXT_XML(new HttpMediaType("text", "xml", true)),
	TEXT_HTML(new HttpMediaType("text", "html", true));


	private HttpMediaType mediaType;
	
	private HttpMediaTypes(HttpMediaType mediaType) {
		this.mediaType = mediaType;
	}
	
	public HttpMediaType getMediaType() {
		return mediaType;
	}
	
	public static HttpMediaTypes fromMediaTypeString(String mediaTypeString) {

		for(HttpMediaTypes type : HttpMediaTypes.values()) {
			if(mediaTypeString.contains(type.getMediaType().toString())) {
				return type;
			}
		}

		return null;
	}
	
}
