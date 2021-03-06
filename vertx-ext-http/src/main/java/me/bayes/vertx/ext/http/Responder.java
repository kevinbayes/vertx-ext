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

import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponseStatus;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.streams.WriteStream;

/**
 * <p>
 * This is a wrapper for the {@link HttpServerResponse} that provides a few extra
 * methods for providing extra methods for building a http response.
 * </p>
 * 
 * <p>
 * This class is not thread-safe.
 * </p>
 * 
 * @author Kevin Bayes
 * @since 1.0.0
 */
public class Responder implements WriteStream<Responder> {
	
	private HttpServerResponse response;
	private Object responseObject;
	
	/**
	 * <p>
	 * private as the creation is left to the factory method.
	 * </p>
	 * 
	 * @param response
	 */
	public Responder(final HttpServerResponse response) {
		super();
		this.response = response;
	}
	
	/**
	 * <p>
	 * Raw set status based on the http status codes found in {@link me.bayes.vertx.ext.http.StatusClass}.
	 * </p>
	 * 
	 * @param code
	 * @return this
	 */
	public Responder setHttpStatus(int code) {
		return status(HttpResponseStatus.valueOf(code));
	}
	
	/**
	 * <p>
	 * Raw set status based on the http status codes found in {@link HttpResponseStatus}
	 * </p>
	 * 
	 * @param status
	 * @return this
	 */
	public Responder status(HttpResponseStatus status) {
		response.setStatusCode(status.code());
		response.setStatusMessage(status.reasonPhrase());
		
		return this;
	}

	/*
	 * Commonly used status code helper methods.
	 */
	public Responder ok() {
		return status(HttpResponseStatus.OK);
	}
	
	public Responder created() {
		return status(HttpResponseStatus.CREATED);
	}
	
	public Responder created(Object responseObject) {
		this.responseObject = responseObject;
		return status(HttpResponseStatus.CREATED);
	}
	
	public Responder accepted() {
		return status(HttpResponseStatus.ACCEPTED);
	}
	
	public Responder noContent() {
		return status(HttpResponseStatus.NO_CONTENT);
	}
	
	public Responder resetContent() {
		return status(HttpResponseStatus.RESET_CONTENT);
	}
	
	public Responder internalServerError() {
		return status(HttpResponseStatus.INTERNAL_SERVER_ERROR);
	}

	public Responder seeOther() {
		return status(HttpResponseStatus.SEE_OTHER);
	}

	/*
	 * Common MIME Types
	 */
	public Responder html() {
		return putHeader(HttpHeaders.Names.CONTENT_TYPE, HttpMediaTypes.TEXT_HTML.getMediaType().toString());
	}

	public Responder json() {
		return putHeader(HttpHeaders.Names.CONTENT_TYPE, HttpMediaTypes.APPLICATION_JSON.getMediaType().toString());
	}

	/*
	 * Common headers
	 */
	public Responder location(String url) {
		return putHeader(HttpHeaders.Names.LOCATION, url);
	}


	public Responder content(Object responseObject) {
		this.responseObject = responseObject;
		return this;
	}

	
	public void sendResponse() {
		ResponseObjectWriter responseWriter = ResponseObjectWriterFactory.getInstance(response.headers().get(HttpHeaders.Names.CONTENT_TYPE));
		if(responseWriter != null && responseObject != null) {
			try {
				responseWriter.writeResponse(response, responseObject);
			} catch (Exception e) {
				this.internalServerError().end();
			}
		} else {
			this.internalServerError().end();
		}
	}
	
	/*
	 * Vertx HttpResponse methods
	 */
	public Responder exceptionHandler(Handler<Throwable> handler) {
		response.exceptionHandler(handler);
		return this;
	}

	@Override
	public WriteStream<Responder> write(Responder responder) {
		return null;
	}

	public Responder setWriteQueueMaxSize(int maxSize) {
		response.setWriteQueueMaxSize(maxSize);
		return this;
	}

	public boolean writeQueueFull() {
		return response.writeQueueFull();
	}

	public Responder drainHandler(Handler<Void> handler) {
		response.drainHandler(handler);
		return this;
	}

	public int getStatusCode() {
		return response.getStatusCode();
	}

	public Responder setStatusCode(int statusCode) {
		response.setStatusCode(statusCode);
		return this;
	}

	public String getStatusMessage() {
		return response.getStatusMessage();
	}

	public Responder setStatusMessage(String statusMessage) {
		response.setStatusMessage(statusMessage);
		return this;
	}

	public Responder setChunked(boolean chunked) {
		response.setChunked(chunked);
		return this;
	}

	public boolean isChunked() {
		return response.isChunked();
	}

	public MultiMap headers() {
		return response.headers();
	}

	public Responder putHeader(String name, String value) {
		response.putHeader(name, value);
		return this;
	}

	public Responder putHeader(String name, Iterable<String> values) {
		response.putHeader(name, values);
		return this;
	}

	public MultiMap trailers() {
		return response.trailers();
	}

	public Responder putTrailer(String name, String value) {
		response.putTrailer(name, value);
		return this;
	}

	public Responder putTrailer(String name, Iterable<String> values) {
		response.putTrailer(name, values);
		return this;
	}

	public Responder closeHandler(Handler<Void> handler) {
		response.closeHandler(handler);
		return this;
	}

	public Responder write(Buffer chunk) {
		response.write(chunk);
		return this;
	}

	public Responder write(String chunk, String enc) {
		response.write(chunk, enc);
		return this;
	}

	public Responder write(String chunk) {
		response.write(chunk);
		return this;
	}

	public void end(String chunk) {
		response.end(chunk);
	}

	public void end(String chunk, String enc) {
		response.end(chunk, enc);
	}

	public void end(Buffer chunk) {
		response.end(chunk);
	}

	public void end() {
		response.end();
	}

	public Responder sendFile(String filename) {
		response.sendFile(filename);
		return this;
	}

	public Responder sendFile(String filename,
			Handler<AsyncResult<Void>> resultHandler) {
		response.sendFile(filename,
				resultHandler);
		return this;
	}

	public Responder sendFile(String filename, String notFoundFile,
			Handler<AsyncResult<Void>> resultHandler) {
		response.sendFile(filename,
				resultHandler);
		return this;
	}

	public void close() {
		response.close();
		
	}
}
