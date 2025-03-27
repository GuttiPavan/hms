package com.hms.hms.service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class S3Service {

    @Value("${aws.accessKey}")
    private String accessKey;

    @Value("${aws.secretKey}")
    private String secretKey;

    @Value("${aws.s3.bucketName}")
    private String bucketName;

    public String uploadFileToS3(MultipartFile file) throws IOException {
        BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 s3client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion("eu-north-1")
                .build();

        File localFile = File.createTempFile("temp", file.getOriginalFilename());
        file.transferTo(localFile);

        s3client.putObject(new PutObjectRequest(bucketName, file.getOriginalFilename(), localFile));

        return s3client.getUrl(bucketName, file.getOriginalFilename()).toString();
    }
}
