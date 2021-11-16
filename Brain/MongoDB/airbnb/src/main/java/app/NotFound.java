package app;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class NotFound implements Handler {

   // URL of this page relative to http://localhost:7000/
   public static final String URL = "/404.html";

   // Name of the Thymeleaf HTML template page in the resources folder
   private static final String TEMPLATE = ("404.html");

   @Override
   public void handle(Context context) throws Exception {
      // DO NOT MODIFY THIS
      // Makes Javalin render the webpage
      context.render(TEMPLATE);
   }
}