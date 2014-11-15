package me.bayes.vertx.ext.http;

import org.vertx.java.core.http.HttpServerResponse;
import org.vertx.java.core.json.JsonObject;

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
public class HtmlResponseObjectWriter implements ResponseObjectWriter {

    public void writeResponse(HttpServerResponse response, Object object) throws Exception {
        response.end(object.toString(), "UTF-8");
    }

}
