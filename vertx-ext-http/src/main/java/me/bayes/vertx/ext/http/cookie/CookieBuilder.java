package me.bayes.vertx.ext.http.cookie;

import org.vertx.java.core.http.HttpHeaders;
import org.vertx.java.core.http.HttpServerResponse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * Created by Kevin Bayes on 2014/11/15.
 */
public class CookieBuilder {

    private DateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'");

    private HttpServerResponse response;
    private Map<String, String> propertyMap = new HashMap<String, String>();
    private Date expiryDate;
    private String domain = "";
    private String path = "";
    private boolean secure = false;
    private boolean httpOnly = false;

    public CookieBuilder(HttpServerResponse response) {
        this.response = response;
    }

    public CookieBuilder addProperty(Property name, String value) {
        return addProperty(name.value, value);
    }

    public CookieBuilder addProperty(String name, String value) {
        propertyMap.put(name, value);
        return this;
    }

    public CookieBuilder expires(Date when) {
        expiryDate = when;
        return this;
    }

    public CookieBuilder domain(String cookieDomain) {
        domain = cookieDomain;
        return this;
    }

    public CookieBuilder path(String cookiePath) {
        path = cookiePath;
        return this;
    }

    public CookieBuilder isHttpOnly() {
        httpOnly = true;
        return this;
    }

    public CookieBuilder isSecure() {
        secure = true;
        return this;
    }

    public HttpServerResponse build() {

        StringBuilder sb = new StringBuilder();

        propertyMap.forEach((key, value) -> {
            if(sb.length() == 0) {
                sb.append(key + "=" + value);
            } else {
                sb.append("; " + key + "=" + value);
            }
        });

        if(expiryDate != null) sb.append("; " + Property.EXPIRE.value + "=" + dateFormat.format(expiryDate));
        if(path != null) sb.append("; " + Property.PATH.value + "=" + path);
        if(domain != null) sb.append("; " + Property.DOMAIN.value + "=" + domain);
        if(secure) sb.append("; " + Property.SECURE.value);
        if(httpOnly) sb.append("; " + Property.HTTP_ONLY.value);

        response.putHeader(HttpHeaders.SET_COOKIE.toString(), sb.toString());

        return response;
    }


    public static enum Property {
        EXPIRE("Expires"),
        DOMAIN("domain"),
        PATH("path"),
        SECURE("secure"),
        HTTP_ONLY("httponly");

        String value;

        private Property(String value) {
            this.value = value;
        }
    }
}