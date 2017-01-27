/**
 * Created by arshak.askaryan on 2/17/2016.
 */
import java.io.IOException;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.json.stream.JsonGenerator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/json2")
public class HelloWorldJson2 extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");

        JsonWriter jsonWriter = Json.createWriter(response.getWriter());
        JsonObject model = Json.createObjectBuilder()
                .add("firstName", "johnny")
                .add("lastName", "English")
                .add("age", 48)
                .add("streetAddress", "Downing Street 10")
                .add("city", "London")
                .add("state", "UK")
                .add("phoneNumbers", Json.createArrayBuilder()
                        .add(Json.createObjectBuilder()
                                .add("type", "mobile")
                                .add("number", "338-111-1111"))
                        .add(Json.createObjectBuilder()
                                .add("type", "home")
                                .add("number", "0771-222-2222")))
                .build();

        jsonWriter.writeObject(model);
        jsonWriter.close();

        jsonWriter.close();
    }
}