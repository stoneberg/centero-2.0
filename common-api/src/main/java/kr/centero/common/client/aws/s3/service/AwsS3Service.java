package kr.centero.common.client.aws.s3.service;
import kr.centero.core.common.exception.ApplicationException;
import kr.centero.core.common.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class AwsS3Service {

    private final S3Client s3Client;

    @Value( "${cloud.aws.s3.bucket}" )
    private String bucket;


    /**
     * S3 업로드 요청 생성
     * @param path
     * @param multipartFile
     * @return
     */
    private PutObjectRequest createPutObjectRequest(String path, MultipartFile multipartFile) throws IOException
    {
        String orgFilename      = multipartFile.getOriginalFilename();
        String contentType      = multipartFile.getContentType();
        String saveFilename     = FileUtil.createUUIDFilename(orgFilename);
        String savePath         = Path.of( path, saveFilename).toString();
        long fileSize           = multipartFile.getSize();

        Map<String, String> metadata = new HashMap<>();
        metadata.put("org-filename", orgFilename);

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucket)
                .key(savePath)
                .contentType(contentType)
                .contentLength(fileSize)
                .metadata(metadata)
                .build();

        return putObjectRequest;
    }

    /**
     * 단일 파일 S3 업로드 처리
     * @param path
     * @param multipartFile
     * @return
     * @throws IOException
     */
    public String upload(String path, MultipartFile multipartFile) throws ApplicationException
    {
        try
        {
            PutObjectRequest putObjectRequest = createPutObjectRequest(path, multipartFile);
            byte[] bytes = multipartFile.getBytes();

            PutObjectResponse response = AwsS3Template.execute(() -> {
                return s3Client.putObject( putObjectRequest, RequestBody.fromBytes(bytes) );
            });
            return putObjectRequest.key();
        }
        catch (IOException e)
        {// 서버에러 예외처리
            log.error( "AwsS3Service.upload ", e);
        }

        return "";
    }



    /**
     * Object 요청 생성
     * @param key
     * @return
     */
    private GetObjectRequest createGetObjectRequest(String key)
    {
        return GetObjectRequest
                .builder()
                .bucket(bucket)
                .key(key)
                .build();
    }

    /**
     * 다운로드
     * @param key
     */
    public boolean download(String key, String path)
    {
        GetObjectRequest getObjectRequest = createGetObjectRequest(key);

        ResponseBytes<GetObjectResponse> response = AwsS3Template.execute(() -> {
            return s3Client.getObjectAsBytes(getObjectRequest);
        });
        byte[] data = response.asByteArray();

        // path 파일로 저장
        return FileUtil.saveToFile(data, path);
    }


    /**
     * S3 삭제 요청 생성
     * @param key
     * @return
     */
    private DeleteObjectRequest createDeleteObjectRequest(String key)
    {
        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .build();

        return deleteObjectRequest;
    }


    /**
     * S3 삭제 처리
     * @param key
     * @throws ApplicationException
     */
    public void delete(String key) throws ApplicationException {

        DeleteObjectRequest deleteObjectRequest = createDeleteObjectRequest(key);

        DeleteObjectResponse response = AwsS3Template.execute(() -> {
            return s3Client.deleteObject(deleteObjectRequest);
        });
    }


    /**
     * S3 복사 요청 생성
     * @param srcBucket
     * @param srcKey
     * @param destBucket
     * @param destKey
     * @return
     */
    private CopyObjectRequest createCopyObjectRequest(String srcBucket, String srcKey, String destBucket, String destKey)
    {
        return CopyObjectRequest.builder()
                .sourceBucket(srcBucket)
                .sourceKey(srcKey)
                .destinationBucket(destBucket)
                .destinationKey(destKey)
                .build();
    }

    /**
     * S3 복사 (동일 버킷 내)
     * @param srcKey
     * @param destKey
     * @throws ApplicationException
     */
    public void copy(String srcKey, String destKey) throws ApplicationException {

        CopyObjectRequest copyObjectRequest = createCopyObjectRequest(bucket, srcKey, bucket, destKey);

        CopyObjectResponse response = AwsS3Template.execute(() -> {
            return s3Client.copyObject(copyObjectRequest);
        });
    }


    /**
     * S3 버킷 목록 요청 생성
     * @param bucketName
     * @return
     */
    private ListObjectsRequest createListObjectsRequest(String bucketName){
        return  ListObjectsRequest.builder()
                .bucket(bucketName)
                .build();
    }

    /**
     * S3 버킷 목록 조회
     * @return
     */
    public List<S3Object> list()
    {
        ListObjectsRequest listObjectsRequest = createListObjectsRequest(bucket);

        ListObjectsResponse response = AwsS3Template.execute(() -> {
            return s3Client.listObjects(listObjectsRequest);
        });

        List<S3Object> objects = response.contents();
        return objects;
    }






    /**
     * URL 요청 생성
     * @param key
     * @return
     */
    private GetUrlRequest createGetUrlRequest(String key)
    {
        return GetUrlRequest.builder()
                .bucket(bucket)
                .key(key)
                .build();
    }

    /**
     * URL 요청 조회
     * @param key
     * @return
     */
    public URL getUrl(String key)
    {
        GetUrlRequest getUrlRequest = createGetUrlRequest(key);
        URL url = AwsS3Template.execute(() -> {
            return s3Client.utilities().getUrl(getUrlRequest);
        });
        return url;
    }


    /**
     * 헤드 객체 요청 생성
     * @param key
     * @return
     */
    private HeadObjectRequest createHeadObjectRequest(String key)
    {
        return HeadObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .build();
    }

    /**
     * 컨텐츠 타입 조회
     * @param key
     * @return
     */
    public String getContentType(String key){
        HeadObjectRequest headObjectRequest = createHeadObjectRequest(key);

        HeadObjectResponse headObjectResponse = AwsS3Template.execute(() -> {
            return  s3Client.headObject(headObjectRequest);
        });
        return headObjectResponse.contentType();
    }


    /**
     * 컨텐츠 크기 반환
     * @param key
     * @return
     */
    public Long getContentLength(String key){
        HeadObjectRequest headObjectRequest = createHeadObjectRequest(key);
        HeadObjectResponse headObjectResponse = AwsS3Template.execute(() -> {
            return  s3Client.headObject(headObjectRequest);
        });
        return headObjectResponse.contentLength();
    }

    /**
     * 메타 데이터 반환
     * @param key
     * @return
     */
    public Map<String,String> getMetadata(String key){
        HeadObjectRequest headObjectRequest = createHeadObjectRequest(key);
        HeadObjectResponse headObjectResponse = AwsS3Template.execute(() -> {
            return  s3Client.headObject(headObjectRequest);
        });
        return headObjectResponse.metadata();
    }
}
