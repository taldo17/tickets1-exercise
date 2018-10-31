package tickets;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;

import java.io.ByteArrayInputStream;
import java.util.UUID;

public class S3TicketsDao {
    private static S3TicketsDao instance;

    private S3TicketsDao() {
    }
    public static S3TicketsDao getInstance(){
        if(instance == null){
            instance = new S3TicketsDao();
        }
        return instance;
    }

    public void uploadObject(String reportString) {
        System.out.println("uploading report string to AWS s3 bucket");
        try {
            AmazonS3Client amazonS3Client = new AmazonS3Client(new DefaultAWSCredentialsProviderChain());
            byte[] bytesToWrite = reportString.getBytes();
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(bytesToWrite.length);
            objectMetadata.setSSEAlgorithm(ObjectMetadata.AES_256_SERVER_SIDE_ENCRYPTION);
            amazonS3Client.putObject("bucketName", UUID.randomUUID().toString(), new ByteArrayInputStream(bytesToWrite), objectMetadata);
        }
        catch (Exception e) {
//            System.out.println("We are not really connected to Amazon");
        }

    }

}
