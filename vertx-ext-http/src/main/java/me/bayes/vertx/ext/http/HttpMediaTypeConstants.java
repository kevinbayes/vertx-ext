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
 * Just a holder for any constants not already available in netty or vertx.
 * </p>
 * 
 * @author Kevin Bayes
 * @since 1.0.0
 */
public interface HttpMediaTypeConstants {
	
	String VALIDATE_MEDIA_TYPE_REGEX = 
			"^(application|audio|example|image|message|model|multipart|text|video|\\*)[\\/]{1}[a-zA-Z0-9\\-\\+\\.*]+";

	
	/**
	 * A simple enum holding all valid types of media types.
	 * 
	 * @author Kevin Bayes
	 * @since 1.0.0
	 */
	public static enum HttpMediaType {
		APPLICATION("application"),
		AUDIO("audio"),
		EXAMPLE("example"),
		IMAGE("image"),
		MESSAGE("message"),
		MODEL("model"),
		MULTIPART("multipart"),
		TEXT("text"),
		VIDEO("video"),
		WILDCARD("*");
		
		private String family;
		
		private HttpMediaType(String family) {
			this.family = family;
		}

		/**
		 * @return the family
		 */
		public String getFamily() {
			return family;
		}
		
		/**
		 * Lookup a valid media type given the media type as a string.
		 * 
		 * @param mediaType
		 * @return
		 */
		public static HttpMediaType forMediaType(String mediaType) {
			for(HttpMediaType family : HttpMediaType.values()) {
				if(mediaType.startsWith(family.family + "/")) {
					return family;
				}
			}
			return null;
		}

	}
}
