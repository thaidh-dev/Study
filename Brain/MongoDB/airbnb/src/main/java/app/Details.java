package app;

import dto.HotelDto;
import io.javalin.http.Context;
import io.javalin.http.Handler;

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
public class Details implements Handler {

   // URL of this page relative to http://localhost:7000/
   public static final String URL = "/details";

   // Name of the Thymeleaf HTML template page in the resources folder
   private static final String TEMPLATE = ("details.html");

   @Override
   public void handle(Context context) throws Exception {

      // The model of data to provide to Thymeleaf.
      Map<String, Object> model = new HashMap<String, Object>();

      String user = "";

      // Add in title for the h1 tag to the model
      model.put("title", new String("Details"));

      user = Util.getLoggedInUserName(context);
      String userID = Util.getLoggedInUserID(context);
      model.put("USERID", userID);
      model.put("USER", user);

      // Look up from JDBC
      MongoDBConnection mongodb = MongoDBConnection.getConnection();
      HotelDto hotelDto = mongodb.getHotel(context.queryParam("idHotel"));
      model.put("hotelDetails", hotelDto);

      context.cookie("url", context.url());
      // DO NOT MODIFY THIS
      // Makes Javalin render the webpage
      context.render(TEMPLATE, model);
   }
}
