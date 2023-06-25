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
                "AKIAW57JWNSXIVLH5OIA",
                "hhitEY+CixGZnmgVFKybsYdPNzhzkcvjPPQKiWui"
        );

        AmazonS3 s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.AP_NORTHEAST_1)
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

        // download all file trong bucket
        TransferManager transferManager = TransferManagerBuilder.standard().withS3Client(s3client).build();
        File file = new File("C:\\Users\\Admin\\Desktop\\Newfolder");
        MultipleFileDownload multipleFileDownload = transferManager.downloadDirectory("geminiotvms", "ThaiDH", file);
        try (ProgressBar pb = new ProgressBar("Download", 100)) {
            do {
                pb.stepTo((long) multipleFileDownload.getProgress().getPercentTransferred());
            } while (!multipleFileDownload.isDone());

            multipleFileDownload.waitForCompletion();
            transferManager.shutdownNow();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


//        s3client.deleteObject("thaidh-bucket","iot_data/example.json");
//        ObjectListing objectListing = s3client.listObjects("thaidh-study");
//        for (S3ObjectSummary os : objectListing.getObjectSummaries()) {
//            s3client.deleteObject("thaidh-study", os.getKey());
//        }
//        s3client.deleteBucket("thaidh-study");
    }
}
