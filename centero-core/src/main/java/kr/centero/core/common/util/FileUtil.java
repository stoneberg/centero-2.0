package kr.centero.core.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public abstract class FileUtil {

    /**
     * 랜던 파일명 생성 (원본 파일명의 확장자가 있으면 붙여준다)
     * @param originalFilename
     * @return
     */
    public static String createUUIDFilename(String originalFilename){
        String uuid = createUUID();
        String fileExt = extractFileExt(originalFilename);
        if ( StringUtils.hasText(fileExt) )
            return uuid + "." + fileExt;
        return uuid;
    }

    /**
     * UUID 생성
     * @return
     */
    public static String createUUID(){
        return UUID.randomUUID().toString();
    }
    
    /**
     * 파일명에서 파일 확장자 추출한다.
     * ex) filename.jpg ==> jpg 반환
     * @param fileName
     * @return
     */
    public static String extractFileExt(String fileName)
    {
        if (!StringUtils.hasText(fileName))
            return "";
        int pos = fileName.lastIndexOf(".");
        if ( pos == -1 )
            return "";
        return fileName.substring(pos + 1);
    }

    /**
     * 바이트 데이터를 파일로 저장
     * @param data
     * @param path
     * @return
     */
    public static boolean saveToFile(byte[] data, String path)
    {
        File file = new File(path);
        try( FileOutputStream os = new FileOutputStream(file) )
        {
            os.write(data);
            return true;
        }
        catch (FileNotFoundException e){
            log.error("FileUtil.saveToFile", e);
        }
        catch (IOException e){
            log.error("FileUtil.saveToFile", e);
        }
        return false;
    }


}
