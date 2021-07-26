import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import java.util.List;

public class JavaConnectS3 {
    public static void main(String[] args) {
        AWSCredentials credentials = new BasicAWSCredentials(
                "AKIAQ2YGQA6OPBMUKNCI",
                "sbq2Gd+uRRWPFj9pe1jmZzJEhKpx63cqOKER0X4s"
        );

        AmazonS3 s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.AP_SOUTHEAST_1)
                .build();

        List<Bucket> buckets = s3client.listBuckets();
        for (Bucket bucket : buckets) {
            System.out.println(bucket.getName());
        }

        ObjectListing objectListing = s3client.listObjects("thaidh-bucket");
        for (S3ObjectSummary os : objectListing.getObjectSummaries()) {
            System.out.println(os.getKey());
        }

//        s3client.deleteObject("thaidh-bucket","iot_data/abc.json");
    }
}
