package app;

import dto.ReviewDto;
import io.javalin.Javalin;
import io.javalin.core.util.RouteOverviewPlugin;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.staticfiles.Location;
import org.jetbrains.annotations.NotNull;

/**
 * Main Application Class.
 * <p>
 * Running this class as regular java application will start the Javalin HTTP
 * Server and our web application.
 *
 * @author Timothy Wiley, 2021. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 * @author Halil Ali, 2021. email halil.ali@rmit.edu.au
 */
public class App {

   public static final int JAVALIN_PORT = 7000;
   public static final String CSS_DIR = "css/";
   public static final String IMAGES_DIR = "images/";
   public static final String JS_DIR = "js/";

   public static void main(String[] args) throws Exception {

      // Establish database connection
      if (MongoDBConnection.getConnection() == null) {
         throw new Exception("Could not establish connection to database");
      }

      // Create our HTTP server and listen in port 7000
      Javalin app = Javalin.create(config -> {
         config.registerPlugin(new RouteOverviewPlugin("/help/routes"));

         // Uncomment this if you have files in the CSS Directory
         config.addStaticFiles("/css", CSS_DIR, Location.CLASSPATH);

         // Uncomment this if you have files in the Images Directory
         config.addStaticFiles("/images", IMAGES_DIR, Location.CLASSPATH);
         config.addStaticFiles("/js", JS_DIR, Location.CLASSPATH);

      }).start(JAVALIN_PORT);

      // capture ctrl-c signal so we can shutdown server safely
      Runtime.getRuntime().addShutdownHook(new Thread(() -> {
         app.stop();
      }));

      // handle shutdown events by closing database and ssh tunnel connections
      app.events(event -> {
         event.serverStopping(() -> {
            System.out.println("server stopping");
         });
         event.serverStopped(() -> {
            System.out.println("server stopped");
            // Close Database connection
            MongoDBConnection.closeConnection();
         });
      });

      // Configure Web Routes
      configureRoutes(app);
   }

   /**
    * set up each individual page of site
    **/
   public static void configureRoutes(Javalin app) {
      // All webpages are listed here as GET pages
      app.get(Index.URL, new Index());
      app.get(Results.URL, new Results());
      app.get(Details.URL, new Details());
      app.get(Reviews.URL, new Reviews());

      app.get("/logout", new Handler() {
         @Override
         public void handle(@NotNull Context context) throws Exception {
            Util.logout(context);
         }
      });

      app.post("/insertReview", new Handler() {
         @Override
         public void handle(@NotNull Context context) throws Exception {
            if (context.sessionAttribute("userID") == null || context.sessionAttribute("userName") == null) {
               context.redirect("/");
               return;
            }

            // Look up from JDBC
            ReviewDto reviewDto = new ReviewDto();
            reviewDto.setListingId(context.formParam("idHotel"));
            reviewDto.setReviewerId(context.sessionAttribute("userID"));
            reviewDto.setReviewerName(context.sessionAttribute("userName"));
            reviewDto.setComments(context.formParam("comment"));
            MongoDBConnection mongodb = MongoDBConnection.getConnection();
            mongodb.insertReview(reviewDto);

            context.redirect("/details?idHotel=" + context.formParam("idHotel"));
         }
      });

      app.post("/updateReview", new Handler() {
         @Override
         public void handle(@NotNull Context context) throws Exception {
            if (context.sessionAttribute("userID") == null || context.sessionAttribute("userName") == null) {
               context.redirect("/");
               return;
            }

            // Look up from JDBC
            ReviewDto reviewDto = new ReviewDto();
            reviewDto.setListingId(context.queryParam("idHotel"));
            reviewDto.setId(context.queryParam("idReview"));
            reviewDto.setComments(context.formParam("editComment"));
            MongoDBConnection mongodb = MongoDBConnection.getConnection();
            mongodb.updateReview(reviewDto);

            String url = context.cookie("url");
            if (url.endsWith("/details")) {
               context.redirect("/details?idHotel=" + context.queryParam("idHotel"));
            } else if (url.endsWith("/reviews")) {
               context.redirect("/reviews");
            }
         }
      });

      app.get("/deleteReview", new Handler() {
         @Override
         public void handle(@NotNull Context context) throws Exception {
            if (context.sessionAttribute("userID") == null || context.sessionAttribute("userName") == null) {
               context.redirect("/");
               return;
            }

            // Look up from JDBC
            ReviewDto reviewDto = new ReviewDto();
            reviewDto.setListingId(context.queryParam("idHotel"));
            reviewDto.setId(context.queryParam("idReview"));
            MongoDBConnection mongodb = MongoDBConnection.getConnection();
            mongodb.deleteReview(reviewDto);

            String url = context.cookie("url");
            if (url.endsWith("/details")) {
               context.redirect("/details?idHotel=" + context.queryParam("idHotel"));
            } else if (url.endsWith("/reviews")) {
               context.redirect("/reviews");
            }
         }
      });

      app.post("/login", new Handler() {
         @Override
         public void handle(@NotNull Context context) throws Exception {
            Util.login(context);
         }
      });

      app.get("/backToResultsPage", new Handler() {
         @Override
         public void handle(@NotNull Context context) throws Exception {
            if (context.sessionAttribute("searchParams") == null) {
               context.redirect("/");
               return;
            }
            context.redirect(context.sessionAttribute("searchParams"));
         }
      });

      app.error(404, new NotFound());
   }

}
