
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import src.util.controller.Controller;

import com.sun.net.httpserver.Headers;

public class MyHandler implements HttpHandler {
    private Controller controller;

    public MyHandler(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String requestMethod = exchange.getRequestMethod();
        if(requestMethod.equalsIgnoreCase("GET")) {
            Headers responseHeaders = exchange.getResponseHeaders();
            responseHeaders.set("Content-Type", "text/plain");
            exchange.sendResponseHeaders(200, 0);

            String fileName = exchange.getRequestURI().toString();

            OutputStream responseBody = exchange.getResponseBody();
            Headers requestHeaders = exchange.getRequestHeaders();
            Set<String> keySet = requestHeaders.keySet();
            Iterator<String> iter = keySet.iterator();
            while(iter.hasNext()) {
                String key = iter.next();
                List values = requestHeaders.get(key);
                String s = key + " = " + values.toString() + "\n";
                responseBody.write(s.getBytes());
            }
            responseBody.close();
        }
        if(requestMethod.equalsIgnoreCase("POST")) {
            Headers responseHeaders = exchange.getResponseHeaders();
            responseHeaders.set("Content-Type", "text/plain");
            exchange.sendResponseHeaders(200, 0);
            String fileName = exchange.getRequestURI().toString();

            InputStream is = exchange.getRequestBody();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String info = "";
            while(br.ready()) {
                info += br.readLine();
            }
            OutputStream response = exchange.getResponseBody();
            response.write("Information is written".getBytes());
            response.close();
        }
    }

}