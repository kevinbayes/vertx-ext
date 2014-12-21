package me.bayes.vertx.ext.http;


import io.vertx.core.http.HttpServerResponse;

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
