package com.microservice.poc.userprofile.config.s3;

import jdk.jfr.ContentType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;

@Service
public class AmazonClient {

    Region region = Region.AP_SOUTHEAST_1;
    S3Client s3Client;

    @Value("${amazonProperties.endpointUrl}")
    private String endpointUrl;

    @Value("${amazonProperties.bucketName}")
    private String bucketName;

    @Value("${amazonProperties.accessKey}")
    private String accessKey;

    @Value("${amazonProperties.secretKey}")
    private String secretKey;

    @PostConstruct
    private void initializeAmazon() {
        AwsCredentials awsCredentials = AwsBasicCredentials.create(this.accessKey, this.secretKey);

        this.s3Client = S3Client.builder()
                .region(region)
                .credentialsProvider(StaticCredentialsProvider.create(awsCredentials))
                .build();
    }

    public String uploadFile(MultipartFile imageFile) {

        String fileUrl = "";

        try {
            File file = convertMultiPartToFile(imageFile);

            String fileName = generateFileName(imageFile);
            fileUrl = endpointUrl + "/" + bucketName + "/" + fileName;

            uploadFileTos3bucket(file, imageFile.getContentType());
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return fileUrl;
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {

        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    private String generateFileName(MultipartFile multiPart) {

        return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
    }

    private void uploadFileTos3bucket(File file, String contentType) {

        System.out.println(s3Client.listBuckets());
        PutObjectResponse putObjectResponse = s3Client.putObject(
                PutObjectRequest.builder()
                        .bucket(bucketName)
                        .key(accessKey)
                        .contentType(contentType)
                        .acl(ObjectCannedACL.PUBLIC_READ)
                        .build(),
                RequestBody.fromFile(file)
        );

        final URL reportUrl = s3Client.utilities().getUrl(GetUrlRequest.builder().bucket(bucketName).key(accessKey).build());

        System.out.println("putObjectResponse = " + putObjectResponse);

        System.out.println("reportUrl = " + reportUrl);

        System.out.println("reportUrlStr = " + reportUrl.toString());
    }
}
