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

import static org.junit.Assert.*;

import java.util.regex.Pattern;

import me.bayes.vertx.ext.http.HttpMediaTypeConstants.HttpMediaType;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Testing to see if the constants perform as expected.
 * 
 * @author Kevin Bayes
 * @since 1.0.0
 */
public class HttpMediaTypeConstantsTest {
	
	private static Pattern pattern;
	
	@BeforeClass
	public static void initClass() {
		pattern = Pattern.compile(HttpMediaTypeConstants.VALIDATE_MEDIA_TYPE_REGEX);
	}

	@Test
	public void testMediaTypeRegularExpressionWithValidMediaTypes() {
		for(HttpMediaTypes type : HttpMediaTypes.values()) {
			assertTrue(pattern.matcher(type.getMediaType().toString()).find());
		}
	}
	
	
	@Test
	public void testMediaTypeRegularExpressionWithInvalidMediaTypes() {
		
		final String[] invalidTypes = {"something/else", " test this", "AnotherRubbish/One"};
		
		for(String type : invalidTypes) {
			assertFalse(pattern.matcher(type).find());
		}
		
	}
	
	@Test
	public void testMediaTypeWithValidMediaTypes() {
		
		final String[] invalidTypes = {"application/*", "text/*", 
			"*/*", "audio/*", 
			"example/*", "image/*", 
			"message/*", "model/*",
			"multipart/*", "video/*"};
		
		for(String type : invalidTypes) {
			assertNotNull(HttpMediaType.forMediaType(type));
		}
		
	}
	
	
	@Test
	public void testMediaTypeWithInvalidMediaTypes() {
		
		final String[] invalidTypes = {"applications/*", "texts/*", "*-/*", "audios/*"};
		
		for(String type : invalidTypes) {
			assertNull(HttpMediaType.forMediaType(type));
		}
		
	}

}
