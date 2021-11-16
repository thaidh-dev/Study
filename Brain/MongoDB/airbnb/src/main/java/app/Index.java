package app;

import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Example Index HTML class using Javalin
 * <p>
 * Generate a static HTML page using Javalin by writing the raw HTML into a Java
 * String object
 *
 * @author Timothy Wiley, 2021. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class Index implements Handler {

   // URL of this page relative to http://localhost:7000/
   public static final String URL = "/";

   // Name of the Thymeleaf HTML template page in the resources folder
   private static final String TEMPLATE = ("index.html");

   @Override
   public void handle(Context context) throws Exception {

      // The model of data to provide to Thymeleaf.
      Map<String, Object> model = new HashMap<String, Object>();

      // Look up from JDBC
      MongoDBConnection mongodb = MongoDBConnection.getConnection();

      String user = "";

      // Add in title for the h1 tag to the model
      model.put("title", new String("Index"));

      user = Util.getLoggedInUserName(context);
      model.put("USER", user);

      ArrayList<String> propertyType = mongodb.getAllPropertyType();
      ArrayList<String> amenities = mongodb.getAllAmenities();
      ArrayList<String> locations = mongodb.getAllCountry();
      locations.addAll(mongodb.getAllMarket());
      model.put("propertyType", propertyType);
      model.put("amenities", amenities);
      model.put("locations", locations);

      context.cookie("url", context.url());
      // DO NOT MODIFY THIS
      // Makes Javalin render the webpage
      context.render(TEMPLATE, model);
   }
}
