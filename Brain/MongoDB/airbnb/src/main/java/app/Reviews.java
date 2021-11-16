package app;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class Reviews implements Handler {
    // URL of this page relative to http://localhost:7000/
    public static final String URL = "/reviews";

    // Name of the Thymeleaf HTML template page in the resources folder
    private static final String TEMPLATE = ("reviews.html");

    @Override
    public void handle(@NotNull Context context) throws Exception {
        // The model of data to provide to Thymeleaf.
        Map<String, Object> model = new HashMap<String, Object>();

        // Add in title for the h1 tag to the model
        model.put("title", new String("Reviews"));
        String user = "";
        user = Util.getLoggedInUserName(context);
        model.put("USER", user);

        user = Util.getLoggedInUserName(context);

        if (user == "" || user == null) {
            context.redirect("/");
        }

        model.put("USER", user);

        // Look up from JDBC
        MongoDBConnection mongodb = MongoDBConnection.getConnection();
        model.put("reviews",
                mongodb.getAllUserReviews(context.sessionAttribute("userID"), context.sessionAttribute("userName")));

        context.cookie("url", context.url());
        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.render(TEMPLATE, model);
    }
}
