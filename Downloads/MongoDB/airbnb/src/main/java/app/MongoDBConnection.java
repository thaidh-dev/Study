package app;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCursor;

import com.mongodb.client.model.Field;
import com.mongodb.client.model.UpdateOptions;
import dto.HotelDto;
import dto.ReviewDto;
import org.bson.Document;
import org.bson.types.Decimal128;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.include;
import static com.mongodb.client.model.Updates.*;
import static java.util.Arrays.asList;

public class MongoDBConnection {
    /**
     * TODO update the DATABASE_URL value below format:
     * mongodb+srv://username:password@atlas.server.address/database Goto
     * https://cloud.mongodb.com/ click connect > connect your application > Driver
     * Java > Version 4.3 or later copy connection string if you have time out
     * issues (due to firewalls) choose verion 3.3 or earlier instead
     **/
    // private static final String DATABASE_URL = "YOUR ATLAS SERVER CONNECTION
    // STRING HERE";

    private static final String DATABASE_URL = "mongodb+srv://thaidh:&Dht24111997@cluster0.7hhz1.mongodb.net/test";

    static MongoClient client;
    MongoDatabase database;
    MongoCollection<Document> allListings;
    private static MongoDBConnection mongodb = null;

    public static MongoDBConnection getConnection() {
        // check that MongoDBConnection is available (if not establish)
        if (mongodb == null) {
            mongodb = new MongoDBConnection();
        }
        return mongodb;
    }

    public MongoDBConnection() {
        System.out.println("Creating MongoDB Connection Object");

        try {
            client = MongoClients.create(DATABASE_URL);
            database = client.getDatabase("sample_airbnb");
            allListings = database.getCollection("listingsAndReviews");
        } catch (Exception e) {
            // If there is an error, lets just print the error
            System.err.println(e.getMessage());
        }
    }

    public static void closeConnection() {
        try {
            if (client != null) {
                client.close();
                System.out.println("Database Connection closed");
            }
        } catch (Exception e) {
            // connection close failed.
            System.err.println(e.getMessage());
        }
    }

    public ArrayList<String> getAllPropertyType() {
        MongoCursor<String> cursor = allListings.distinct("property_type", String.class).iterator();
        ArrayList<String> apartments = new ArrayList<String>();
        try {
            while (cursor.hasNext()) {
                apartments.add(cursor.next().toString());
            }
        } finally {
            cursor.close();
        }
        return apartments;
    }

    public ArrayList<String> getAllAmenities() {
        MongoCursor<String> cursor = allListings.distinct("amenities", String.class).iterator();
        ArrayList<String> amenities = new ArrayList<String>();
        try {
            while (cursor.hasNext()) {
                amenities.add(cursor.next().toString());
            }
        } finally {
            cursor.close();
        }
        return amenities;
    }

    public ArrayList<String> getAllCountry() {
        MongoCursor<String> cursor = allListings.distinct("address.country", String.class).iterator();
        ArrayList<String> countries = new ArrayList<String>();
        try {
            while (cursor.hasNext()) {
                countries.add(cursor.next().toString());
            }
            Collections.sort(countries);
        } finally {
            cursor.close();
        }
        return countries;
    }

    public ArrayList<String> getAllMarket() {
        MongoCursor<String> cursor = allListings.distinct("address.market", String.class).iterator();
        ArrayList<String> makerts = new ArrayList<String>();
        try {
            while (cursor.hasNext()) {
                makerts.add(cursor.next().toString());
            }
            Collections.sort(makerts);
        } finally {
            cursor.close();
        }
        return makerts;
    }

    public ArrayList<HotelDto> searchHotels(HotelDto hotelDto) {
//        // câu query search hotel, đoạn code java bên dưới chỉ là dịch câu query này ra java
//        db.listingsAndReviews.find(
//            {
//                // location startWith "P"
//                $or: [
//                    {"address.country": {$regex: "^P.*$"}},
//                    {"address.market": {$regex: "^P.*$"}}
//                ],
//                accommodates: 4,
//                bedrooms: 1,
//                beds: {$gte: 4},
//                property_type: "Apartment",
//                $and: [
//                    {amenities: "TV"},
//                    {amenities: "Wifi"},
//                ],
//                price: {$lte: 75},
//                "review_scores.review_scores_rating": {$gte: 96},
//                "host.host_is_superhost": false
//            },
//            // chỉ show ra các field này
//            {name: 1, "images.picture_url": 1, bedrooms: 1, beds: 1, price: 1}
//        )
        Document filter = new Document();
        String location = hotelDto.getLocation();
        if (location != null) {
            Document regexLocation = new Document();
            regexLocation.append("$regex", Pattern.compile("^" + location + ".*$", Pattern.CASE_INSENSITIVE));
            Document filterCountry = new Document();
            filterCountry.append("address.country", regexLocation);
            Document filterMarket = new Document();
            filterMarket.append("address.market", regexLocation);
            filter.append("$or", asList(filterCountry, filterMarket));
        }
        Integer accommodates = hotelDto.getAccommodates();
        if (accommodates != null) {
            filter.append("accommodates", accommodates);
        }
        Integer bedrooms = hotelDto.getBedrooms();
        if (bedrooms != null) {
            filter.append("bedrooms", bedrooms);
        }
        Integer beds = hotelDto.getBeds();
        if (beds != null) {
            Document gteBeds = new Document();
            gteBeds.append("$gte", beds);
            filter.append("beds", gteBeds);
        }
        String propertyType = hotelDto.getPropertyType();
        if (propertyType != null) {
            filter.append("property_type", propertyType);
        }
        List<String> amenities = hotelDto.getLstAmenities();
        List<Document> lstFilterAmenities = new ArrayList<>();
        if (amenities != null) {
            if (!amenities.isEmpty()) {
                for (String a : amenities) {
                    Document filterAmenities = new Document();
                    filterAmenities.append("amenities", a);
                    lstFilterAmenities.add(filterAmenities);
                }
                filter.append("$and", lstFilterAmenities);
            }
        }
        Decimal128 price = hotelDto.getMaximumPrice();
        if (price != null) {
            Document ltePrice = new Document();
            ltePrice.append("$lte", price);
            filter.append("price", ltePrice);
        }
        Integer reviewScoresRating = hotelDto.getReviewScoreRating();
        if (reviewScoresRating != null) {
            Document gteReviewScoresRating = new Document();
            gteReviewScoresRating.append("$gte", reviewScoresRating);
            filter.append("review_scores.review_scores_rating", gteReviewScoresRating);
        }
        Boolean hostIsSuperhost = hotelDto.getHostStatus();
        if (hostIsSuperhost != null) {
            filter.append("host.host_is_superhost", hostIsSuperhost);
        }

        MongoCursor<Document> cursor = allListings.find(filter)
                .projection(include("name", "images.picture_url", "bedrooms", "beds", "price")).iterator();
        ArrayList<HotelDto> hotels = new ArrayList<HotelDto>();
        try {
            while (cursor.hasNext()) {
                HotelDto hotelResult = new HotelDto();
                Document element = cursor.next();
                hotelResult.setId(element.getString("_id"));
                hotelResult.setName(element.getString("name"));
                hotelResult.setImg(element.get("images", Document.class).getString("picture_url"));
                hotelResult.setBedrooms(element.getInteger("bedrooms"));
                hotelResult.setBeds(element.getInteger("beds"));
                hotelResult.setMaximumPrice(element.get("price", Decimal128.class));

                hotels.add(hotelResult);
            }
        } finally {
            cursor.close();
        }

        return hotels;
    }

    public HotelDto getHotel(String id) {
        // get hotel by id
        MongoCursor<Document> cursor = allListings.find(eq("_id", id)).iterator();
        HotelDto hotelResult = new HotelDto();
        try {
            // add kết quả query vào hotelResult
            while (cursor.hasNext()) {
                Document element = cursor.next();
                hotelResult.setId(element.getString("_id"));
                hotelResult.setName(element.getString("name"));
                hotelResult.setSummary(element.getString("summary"));
                Document address = element.get("address", Document.class);
                hotelResult.setAddress(
                        address.getString("government_area") + ", " +
                                address.getString("market") + ", " +
                                address.getString("country")
                );
                hotelResult.setReviewScoreRating(element.get("review_scores", Document.class).getInteger("review_scores_rating"));
                hotelResult.setMaximumPrice(element.get("price", Decimal128.class));
                hotelResult.setPropertyType(element.getString("property_type"));
                hotelResult.setLstAmenities(element.get("amenities", List.class));
                hotelResult.setBedrooms(element.getInteger("bedrooms"));
                hotelResult.setAccommodates(element.getInteger("accommodates"));
                hotelResult.setHostStatus(element.get("host", Document.class).getBoolean("host_is_superhost"));
                hotelResult.setImg(element.get("images", Document.class).getString("picture_url"));
                List<Document> lstReviews = element.get("reviews", List.class);
                // sort date desc
                lstReviews.sort((r1, r2) -> r2.get("date", Date.class).compareTo(r1.get("date", Date.class)));
                List<ReviewDto> lstReviewsDto = new ArrayList<>();
                for (Document d : lstReviews) {
                    ReviewDto review = new ReviewDto();
                    review.setId(d.getString("_id"));
                    SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
                    review.setDate(sdf.format(d.get("date", Date.class)));
                    review.setReviewerId(d.getString("reviewer_id"));
                    review.setReviewerName(d.getString("reviewer_name"));
                    review.setComments(d.getString("comments"));
                    lstReviewsDto.add(review);
                }
                hotelResult.setReviews(lstReviewsDto);
                Document host = element.get("host", Document.class);
                hotelResult.setHostName(host.getString("host_name"));
                hotelResult.setHostImg(host.getString("host_picture_url"));
            }
        } finally {
            cursor.close();
        }

        return hotelResult;
    }

    public void insertReview(ReviewDto reviewDto) {
        // find distinct reviews._id của tất cả hotel
        MongoCursor<String> cursor = allListings.distinct("reviews._id", String.class).iterator();
        BigDecimal maxIdReview = BigDecimal.ZERO;
        try {
            while (cursor.hasNext()) {
                // tìm ra id có giá trị lớn nhất
                BigDecimal numberIdReview = new BigDecimal(cursor.next().toString());
                if (numberIdReview.compareTo(maxIdReview) == 1) {
                    maxIdReview = numberIdReview;
                }
            }
        } finally {
            cursor.close();
        }

//        db.listingsAndReviews.updateOne(
//            { _id: "10009999" },
//            { $push: {
//                reviews: {
//                    "_id": "7777777",
//                    "date": new Date(),
//                    "listing_id": "10009999",
//                    "reviewer_id": "reviewerID",
//                    "reviewer_name": "reviewerName",
//                    "comments": "nc hotel",
//                }
//            }}
//        )
        Document review = new Document();
        // sau khi tìm ra id lớn nhất, +1 vào id và lấy đó làm id của review chuẩn bị insert
        review.append("_id", maxIdReview.add(BigDecimal.ONE).toString());
        review.append("date", new Date());
        review.append("listing_id", reviewDto.getListingId());
        review.append("reviewer_id", reviewDto.getReviewerId());
        review.append("reviewer_name", reviewDto.getReviewerName());
        review.append("comments", reviewDto.getComments());
        allListings.updateOne(eq("_id", reviewDto.getListingId()), push("reviews", review));
    }

    public void updateReview(ReviewDto reviewDto) {
//        db.listingsAndReviews.updateOne(
//            { _id: "10009999" },
//            { $set: {
//                "reviews.$[el].comments": "dumb hotel",
//                "reviews.$[el].date": new Date()
//            }},
//            { arrayFilters: [
//                {"el._id": "7777777"}
//            ]}
//        )
        allListings.updateOne(eq("_id", reviewDto.getListingId()),
                set(List.of(
                        new Field<>("reviews.$[el].comments", reviewDto.getComments()),
                        new Field<>("reviews.$[el].date", new Date())
                )),
                new UpdateOptions().arrayFilters(List.of(eq("el._id", reviewDto.getId())))
        );
    }

    public void deleteReview(ReviewDto reviewDto) {
//        db.listingsAndReviews.updateOne(
//            { _id: "10009999" },
//            { $pull: {
//                reviews: {"_id": "7777777"}
//            }}
//        )
        Document idReview = new Document();
        idReview.append("_id", reviewDto.getId());
        allListings.updateOne(eq("_id", reviewDto.getListingId()), pull("reviews", idReview));
    }

    public boolean login(String userID, String userName) {
//        db.listingsAndReviews.find(
//        {
//            "reviews.reviewer_id": "reviewerID",
//            "reviews.reviewer_name": "reviewerName"
//        },
//        {
//            "reviews": {$elemMatch: {reviewer_id: "reviewerID", reviewer_name: "reviewerName"}},
//        })
        Document user = allListings
                .find(and(
                        eq("reviews.reviewer_id", userID),
                        eq("reviews.reviewer_name", userName)
                ))
                .projection(elemMatch("reviews", and(
                        eq("reviewer_id", userID),
                        eq("reviewer_name", userName)
                )))
                .first();

        return user != null;
    }

    public ArrayList<ReviewDto> getAllUserReviews(String userId, String userName) {
//        db.listingsAndReviews.aggregate([
//            {$unwind: "$reviews"},
//            {$match: {$and: [
//                {"reviews.reviewer_id": "reviewerID"},
//                {"reviews.reviewer_name": "reviewerName"},
//            ]}},
//            {$sort: {"reviews.date": -1}},
//            {$project: {"_id": 1, "name": 1, "images.picture_url": 1, "host.host_name": 1, "reviews": 1}}
//        ])
        Document sortDate = new Document();
        sortDate.append("reviews.date", -1);
        MongoCursor<Document> cursor = allListings.aggregate(asList(
                unwind("$reviews"),
                match(and(
                        eq("reviews.reviewer_id", userId),
                        eq("reviews.reviewer_name", userName)
                )),
                sort(sortDate),
                project(include("_id", "name", "images.picture_url", "host.host_name", "reviews"))
        )).iterator();
        ArrayList<ReviewDto> reviews = new ArrayList<>();

        while (cursor.hasNext()) {
            Document element = cursor.next();
            ReviewDto review = new ReviewDto();
            review.setListingId(element.getString("_id"));
            review.setHotelName(element.getString("name"));
            Document img = element.get("images", Document.class);
            review.setImgHotelUrl(img.getString("picture_url"));
            Document r = element.get("reviews", Document.class);
            review.setId(r.getString("_id"));
            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
            review.setDate(sdf.format(r.get("date", Date.class)));
            review.setComments(r.getString("comments"));
            Document host = element.get("host", Document.class);
            review.setHostName(host.getString("host_name"));

            reviews.add(review);
        }

        return reviews;
    }
}
