package kr.centero.common.client.aws.s3.service;

import kr.centero.core.common.exception.ApiErrorCode;
import kr.centero.core.common.exception.ApiException;
import kr.centero.core.common.exception.ApplicationException;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.services.s3.model.*;

import java.util.function.Supplier;

/**
 * 공통 에러 처리를 위한 템플릿 메서드 패턴
 */
public abstract  class AwsS3Template {

    public static <T> T execute(Supplier<T> supplier) throws ApiException {
        try{
            return supplier.get();
        }
        catch (ObjectNotInActiveTierErrorException e){
            throw  new ApplicationException(e.getMessage(), e);
        }
        catch (InvalidObjectStateException e){
            throw  new ApplicationException(e.getMessage(), e);
        }
        catch (NoSuchBucketException e) {
            throw  new ApplicationException(e.getMessage(), e);
        }
        catch (NoSuchKeyException e){
            throw  new ApplicationException(e.getMessage(), e);
        }
        catch (S3Exception e ) {
            throw  new ApplicationException(e.getMessage(), e);
        }
        catch (AwsServiceException e ) {
            throw  new ApplicationException(e.getMessage(), e);
        }
        catch (SdkClientException e ) {
            throw  new ApplicationException(e.getMessage(), e);
        }
        catch (Exception e ) {
            throw  new ApplicationException(e.getMessage(), e);
        }
    }
}
