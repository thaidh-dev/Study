package dto;

import org.bson.types.Decimal128;

import java.util.List;

public class HotelDto {
    String id;
    String name;
    String summary;
    String img;
    String location;
    Integer accommodates;
    Integer bedrooms;
    Integer beds;
    String propertyType;
    String availableAmenities;
    List<String> lstAmenities;
    Decimal128 maximumPrice;
    Integer reviewScoreRating;
    Boolean hostStatus;
    String address;
    String hostName;
    String hostImg;
    List<ReviewDto> reviews;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getAccommodates() {
        return accommodates;
    }

    public void setAccommodates(Integer accommodates) {
        this.accommodates = accommodates;
    }

    public Integer getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(Integer bedrooms) {
        this.bedrooms = bedrooms;
    }

    public Integer getBeds() {
        return beds;
    }

    public void setBeds(Integer beds) {
        this.beds = beds;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getAvailableAmenities() {
        return availableAmenities;
    }

    public void setAvailableAmenities(String availableAmenities) {
        this.availableAmenities = availableAmenities;
    }

    public List<String> getLstAmenities() {
        return lstAmenities;
    }

    public void setLstAmenities(List<String> lstAmenities) {
        this.lstAmenities = lstAmenities;
    }

    public Decimal128 getMaximumPrice() {
        return maximumPrice;
    }

    public void setMaximumPrice(Decimal128 maximumPrice) {
        this.maximumPrice = maximumPrice;
    }

    public Integer getReviewScoreRating() {
        return reviewScoreRating;
    }

    public void setReviewScoreRating(Integer reviewScoreRating) {
        this.reviewScoreRating = reviewScoreRating;
    }

    public Boolean getHostStatus() {
        return hostStatus;
    }

    public void setHostStatus(Boolean hostStatus) {
        this.hostStatus = hostStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getHostImg() {
        return hostImg;
    }

    public void setHostImg(String hostImg) {
        this.hostImg = hostImg;
    }

    public List<ReviewDto> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewDto> reviews) {
        this.reviews = reviews;
    }
}
