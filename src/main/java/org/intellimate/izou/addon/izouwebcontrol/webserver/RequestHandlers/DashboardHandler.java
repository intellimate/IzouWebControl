package org.intellimate.izou.addon.izouwebcontrol.webserver.RequestHandlers;

import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.NanoHTTPD.IHTTPSession;
import fi.iki.elonen.NanoHTTPD.Response;
import org.intellimate.izou.addon.izouwebcontrol.webserver.RequestHandler;
import org.intellimate.izou.sdk.Context;

import javax.json.Json;
import javax.json.JsonObject;
import java.util.Map;

import static fi.iki.elonen.SimpleWebServer.newFixedLengthResponse;

/**
 * The DashboardHandler handles the content of the Izou Dashboard. It updates the dashboard continuously through
 * short polling on the client side.
 */
public class DashboardHandler extends RequestHandler {

    /**
     * Creates a new DashboardHandler
     *
     * @param context the context of the addOn
     */
    public DashboardHandler(Context context) {
        super(DashboardHandler.class.getName(), context);
    }

    @Override
    public Response POST(IHTTPSession session) {
        Map<String, String> params = session.getParms();

        String color = "";
        int i  = (int)(Math.random() * 5);
        switch (i) {
            case 0:
                color = "red";
                break;
            case 1:
                color = "green";
                break;
            case 2:
                color = "blue";
                break;
            case 3:
                color = "yellow";
                break;
            case 4:
                color = "purple";
                break;
        }

        JsonObject personObject = Json.createObjectBuilder()
                .add("color", color)
                .build();

        return newFixedLengthResponse(Response.Status.OK, NanoHTTPD.MIME_PLAINTEXT, personObject.toString());
    }

    @Override
    public Response GET(IHTTPSession session) {
        return newFixedLengthResponse(Response.Status.OK, NanoHTTPD.MIME_PLAINTEXT, "success");
    }
}
