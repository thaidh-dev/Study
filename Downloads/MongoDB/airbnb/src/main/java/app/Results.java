package app;

import dto.HotelDto;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.bson.types.Decimal128;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
public class Results implements Handler {

   // URL of this page relative to http://localhost:7000/
   public static final String URL = "/results";

   // Name of the Thymeleaf HTML template page in the resources folder
   private static final String TEMPLATE = ("results.html");

   @Override
   public void handle(Context context) throws Exception {

      // The model of data to provide to Thymeleaf.
      Map<String, Object> model = new HashMap<String, Object>();

      String user = "";

      // Add in title for the h1 tag to the model
      model.put("title", new String("Results"));

      user = Util.getLoggedInUserName(context);
      model.put("USER", user);

      // Look up from JDBC
      MongoDBConnection mongodb = MongoDBConnection.getConnection();
      HotelDto hotelDto = new HotelDto();
      String location = context.queryParam("location");
      if (location != null) {
         location = location.trim();
         if (!location.isEmpty()) {
            hotelDto.setLocation(location);
         }
      }
      String accommodates = context.queryParam("accommodates");
      if (accommodates != null) {
         accommodates = accommodates.trim();
         if (!accommodates.isEmpty()) {
            hotelDto.setAccommodates(Integer.parseInt(accommodates));
         }
      }
      String bedrooms = context.queryParam("bedrooms");
      if (bedrooms != null) {
         bedrooms = bedrooms.trim();
         if (!bedrooms.isEmpty()) {
            hotelDto.setBedrooms(Integer.parseInt(bedrooms));
         }
      }
      String beds = context.queryParam("beds");
      if (beds != null) {
         beds = beds.trim();
         if (!beds.isEmpty()) {
            hotelDto.setBeds(Integer.parseInt(beds));
         }
      }
      String propertyType = context.queryParam("property_type");
      if (propertyType != null && !propertyType.equals("PROPERTTY TYPE") && !propertyType.equals("all")) {
         propertyType = propertyType.trim();
         if (!propertyType.isEmpty()) {
            hotelDto.setPropertyType(propertyType);
         }
      }
      List<String> amenities = context.queryParams("available_amenities");
      if (!amenities.isEmpty()) {
         hotelDto.setLstAmenities(amenities);
      }
      String maximumPrice = context.queryParam("maximum_price");
      if (maximumPrice != null) {
         maximumPrice = maximumPrice.trim();
         if (!maximumPrice.isEmpty()) {
            hotelDto.setMaximumPrice(Decimal128.parse(maximumPrice));
         }
      }
      String reviewScoreRating = context.queryParam("review_score_rating");
      if (reviewScoreRating != null) {
         reviewScoreRating = reviewScoreRating.trim();
         if (!reviewScoreRating.isEmpty()) {
            hotelDto.setReviewScoreRating(Integer.parseInt(reviewScoreRating));
         }
      }
      String hostStatus = context.queryParam("host_status");
      if (hostStatus != null && !hostStatus.equals("HOST STATUS") && !hostStatus.equals("all")) {
         hotelDto.setHostStatus(hostStatus.equals("super"));
      }
      ArrayList<HotelDto> lstHotels = mongodb.searchHotels(hotelDto);
      model.put("lstHotels", lstHotels);

      context.cookie("url", context.url());
      context.sessionAttribute("searchParams", context.fullUrl());
      // DO NOT MODIFY THIS
      // Makes Javalin render the webpage
      context.render(TEMPLATE, model);
   }
}
