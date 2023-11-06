package kr.centero.core.common.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private static final String DEFAULT_MESSAGE = "SUCCESS";
    private static final String DEFAULT_ERROR_MESSAGE = "ERROR";
    private Boolean success = true;
    private T data;
    private Integer status;
    private String code;
    private String message;
    private String timestamp;

    /*##############################################################################################################
     * 1. ApiResponse.ok() -> ResponseEntity<ApiResponse<Void>> : Use this when you don't need to return data.
     * 2. ApiResponse.of(data) -> ResponseEntity<ApiResponse<T>> : Use this when you need to return data.
     * 3. ApiResponse.created() -> ResponseEntity<ApiResponse<Void>> : Use this when you need to return 201 created message.
     * 4. ApiResponse.created(data) -> ResponseEntity<ApiResponse<T>> : Use this when you need to return 201 created message with data.
     * 5. ApiResponse.bad() -> ResponseEntity<ApiResponse<Void>> : Use this when you need to return 400 error message with HttpStatus.OK.
     * 6. ApiResponse.not() -> ResponseEntity<ApiResponse<Void>> : Use this when you need to return 404 error message with HttpStatus.OK.
     *##############################################################################################################*/

    /**
     * return default api response
     *
     * @return ResponseEntity<ApiResponse <Void>>
     */
    public static <T> ResponseEntity<ApiResponse<T>> ok() {
        final ApiResponse<T> response = new ApiResponse<>();
        response.setStatus(HttpStatus.OK.value());
        response.setCode(HttpStatus.OK.name());
        response.setMessage(DEFAULT_MESSAGE);
        response.setTimestamp(LocalDateTime.now().toString());
        response.setData(null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * return api response of HttpStatus.CREATED
     * this is for the case when you need to 201 created message
     *
     * @return ResponseEntity<ApiResponse <Void>>
     */
    public static <T> ResponseEntity<ApiResponse<T>> created() {
        final ApiResponse<T> response = new ApiResponse<>();
        response.setStatus(HttpStatus.CREATED.value());
        response.setCode(HttpStatus.CREATED.name());
        response.setMessage(DEFAULT_MESSAGE);
        response.setTimestamp(LocalDateTime.now().toString());
        response.setData(null);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * return api response of HttpStatus.CREATED
     * this is for the case when you need to 201 created message with data
     *
     * @return ResponseEntity<ApiResponse <Void>>
     */
    public static <T> ResponseEntity<ApiResponse<T>> created(T data) {
        final ApiResponse<T> response = new ApiResponse<>();
        response.setStatus(HttpStatus.CREATED.value());
        response.setCode(HttpStatus.CREATED.name());
        response.setMessage(DEFAULT_MESSAGE);
        response.setTimestamp(LocalDateTime.now().toString());
        response.setData(data);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * return api response with message
     *
     * @param message custom message
     * @return ResponseEntity<ApiResponse <Void>>
     */
    public static <T> ResponseEntity<ApiResponse<T>> ok(String message) {
        final ApiResponse<T> response = new ApiResponse<>();
        response.setStatus(HttpStatus.OK.value());
        response.setCode(HttpStatus.OK.name());
        response.setMessage(message);
        response.setTimestamp(LocalDateTime.now().toString());
        response.setData(null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * return api response with message and code
     *
     * @param message custom message
     * @param code    custom code
     * @return @return ResponseEntity<ApiResponse<Void>>
     */
    public static <T> ResponseEntity<ApiResponse<T>> ok(String message, String code) {
        final ApiResponse<T> response = new ApiResponse<>();
        response.setStatus(HttpStatus.OK.value());
        response.setCode(code);
        response.setMessage(message);
        response.setTimestamp(LocalDateTime.now().toString());
        response.setData(null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * return api response with data
     *
     * @param data result data<T>
     * @return ResponseEntity<ApiResponse <T>>
     */
    public static <T> ResponseEntity<ApiResponse<T>> of(T data) {
        final ApiResponse<T> response = new ApiResponse<>();
        response.setStatus(HttpStatus.OK.value());
        response.setCode(HttpStatus.OK.name());
        response.setMessage(DEFAULT_MESSAGE);
        response.setData(data);
        response.setTimestamp(LocalDateTime.now().toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * return api response with data and message
     *
     * @param data    result data<T>
     * @param message custom message
     * @return ResponseEntity<ApiResponse <T>>
     */
    public static <T> ResponseEntity<ApiResponse<T>> of(T data, String message) {
        final ApiResponse<T> response = new ApiResponse<>();
        response.setStatus(HttpStatus.OK.value());
        response.setCode(HttpStatus.OK.name());
        response.setMessage(message);
        response.setData(data);
        response.setTimestamp(LocalDateTime.now().toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * return api response with data, message and code
     *
     * @param data    result data<T>
     * @param message custom message
     * @param code    custom code
     * @return ResponseEntity<ApiResponse <T>>
     */
    public static <T> ResponseEntity<ApiResponse<T>> of(T data, String message, String code) {
        final ApiResponse<T> response = new ApiResponse<>();
        response.setStatus(HttpStatus.OK.value());
        response.setCode(code);
        response.setMessage(message);
        response.setData(data);
        response.setTimestamp(LocalDateTime.now().toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * return error[BAD_REQUEST] api response with HttpStatus.OK
     * this is for the case when you need to return 400 error message with HttpStatus.OK
     *
     * @return ResponseEntity<ApiResponse <Void>>
     */
    public static <T> ResponseEntity<ApiResponse<T>> bad() {
        final ApiResponse<T> response = new ApiResponse<>();
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setCode(HttpStatus.BAD_REQUEST.name());
        response.setMessage(DEFAULT_ERROR_MESSAGE);
        response.setTimestamp(LocalDateTime.now().toString());
        response.setData(null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * return error[NOT_FOUND] api response with HttpStatus.OK
     * this is for the case when you need to return 404 error message with HttpStatus.OK
     *
     * @return ResponseEntity<ApiResponse <Void>>
     */
    public static <T> ResponseEntity<ApiResponse<T>> not() {
        final ApiResponse<T> response = new ApiResponse<>();
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setCode(HttpStatus.NOT_FOUND.name());
        response.setMessage(DEFAULT_ERROR_MESSAGE);
        response.setTimestamp(LocalDateTime.now().toString());
        response.setData(null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
