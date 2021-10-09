package app;

import org.bson.Document;

import io.javalin.http.Context;

public class Util {
   public static String getLoggedInUserID(Context context) {
      String user = null;
      if (context.sessionAttribute("userID") != null && context.sessionAttribute("userName") != null) {
         user = context.sessionAttribute("userID").toString();
      }
      return user;
   }

   public static String getLoggedInUserName(Context context) {
      String user = null;
      if (context.sessionAttribute("userID") != null && context.sessionAttribute("userName") != null) {
         user = context.sessionAttribute("userName").toString();
      }
      return user;
   }

   // logout the current user by removing their session id details
   public static void logout(Context context) {
      context.sessionAttribute("userID", null);
      context.sessionAttribute("userName", null);

      // reload the page
      context.redirect("/");
   }

   // login the user by creating a session id details
   public static void login(Context context) {
      String userID = context.formParam("userID");
      String userName = context.formParam("userName");
      MongoDBConnection mongodb = MongoDBConnection.getConnection();
      if (mongodb.login(userID, userName)) {
         context.sessionAttribute("userID", userID);
         context.sessionAttribute("userName", userName);
         Document doc = new Document("messenger", "Success");
         String jsonString = doc.toJson();
         context.json(jsonString);
      } else {
         Document doc = new Document("messenger", "The ID or username is incorrect");
         String jsonString = doc.toJson();
         context.json(jsonString);
      }
   }
}
