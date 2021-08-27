import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.services.s3.transfer.MultipleFileDownload;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.TransferProgress;
import me.tongfei.progressbar.ProgressBar;
import me.tongfei.progressbar.ProgressBarStyle;

import java.io.File;
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

//        List<Bucket> buckets = s3client.listBuckets();
//        for (Bucket bucket : buckets) {
//            System.out.println(bucket.getName());
//        }
//
//        ObjectListing objectListing = s3client.listObjects("thaidh-bucket");
//        for (S3ObjectSummary os : objectListing.getObjectSummaries()) {
//            System.out.println(os.getKey());
//        }

//        TransferManager transferManager = TransferManagerBuilder.standard().withS3Client(s3client).build();
//        File file = new File("C:\\Users\\Admin\\Desktop\\Newfolder");
//        MultipleFileDownload multipleFileDownload = transferManager.downloadDirectory("thaidh-bucket", "", file);
//        try (ProgressBar pb = new ProgressBar("Download", 100)) {
//            do {
//                TransferProgress progress = multipleFileDownload.getProgress();
//                long pct = (long) (progress.getPercentTransferred());
//                pb.stepTo(pct);
//            } while (!multipleFileDownload.isDone());
//        }


        s3client.deleteObject("thaidh-bucket","iot_data/example.json");
//        ObjectListing objectListing = s3client.listObjects("thaidh-film");
//        for (S3ObjectSummary os : objectListing.getObjectSummaries()) {
//            s3client.deleteObject("thaidh-film", os.getKey());
//        }
    }
}
