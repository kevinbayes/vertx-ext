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

import java.util.Map;

/**
 * <p>
 * Media Types as specified in <a href="http://www.iana.org/assignments/media-types/media-types.xhtml">http://www.iana.org/assignments/media-types/media-types.xhtml</a>
 * </p>
 * 
 * @author Kevin Bayes
 * @since 1.0.0
 */
public class HttpMediaType {
	
	private static final String MEDIA_TYPE_FORMAT = "%s/%s";
	
	private String type;
	private String subType;
	private boolean allowsParameters;
	private Map<String, String> parameters;
	
	/**
	 * @param type
	 * @param subType
	 */
	public HttpMediaType(String type, String subType) {
		super();
		this.type = type;
		this.subType = subType;
	}
	
	/**
	 * @param type
	 * @param subType
	 * @param allowsParameters
	 */
	public HttpMediaType(String type, String subType, boolean allowsParameters) {
		this(type, subType);
		this.allowsParameters = allowsParameters;
	}
	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the subType
	 */
	public String getSubType() {
		return subType;
	}

	/**
	 * @param subType the subType to set
	 */
	public void setSubType(String subType) {
		this.subType = subType;
	}

	/**
	 * @return the parameters
	 */
	public Map<String, String> getParameters() {
		return parameters;
	}

	/**
	 * @param parameters the parameters to set
	 */
	public void setParameters(Map<String, String> parameters) {
		if(allowsParameters) this.parameters = parameters;
	}
	
	/**
	 * @return the allowsParameters
	 */
	public boolean isAllowsParameters() {
		return allowsParameters;
	}

	/**
	 * @param allowsParameters the allowsParameters to set
	 */
	public void setAllowsParameters(boolean allowsParameters) {
		this.allowsParameters = allowsParameters;
	}
	
	
	public String toString() {
		return String.format(MEDIA_TYPE_FORMAT, type, subType);
	}

}
