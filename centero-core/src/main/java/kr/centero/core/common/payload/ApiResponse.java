package kr.centero.core.common.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {
    private static final String DEFAULT_MESSAGE = "SUCCESS";
    private static final String DEFAULT_ERROR_MESSAGE = "ERROR";
    private Boolean success = true;
    private Object data;
    private Integer status;
    private String code;
    private String message;
    private String timestamp;


    /**
     * POST, PUT, DELETE - @PostMapping(""), @PutMapping("/{id}"), @DeleteMapping("/{id}")
     *
     * @return ApiResponse with HttpStatus.OK (200)
     */
    @ResponseStatus(HttpStatus.OK)
    public static ResponseEntity<ApiResponse> ok() {
        final ApiResponse response = new ApiResponse();
        response.setStatus(HttpStatus.OK.value());
        response.setCode(HttpStatus.OK.name());
        response.setMessage(DEFAULT_MESSAGE);
        response.setTimestamp(LocalDateTime.now().toString());
        response.setData(null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * POST, PUT, DELETE - @PostMapping(""), @PutMapping("/{id}"), @DeleteMapping("/{id}")
     *
     * @param message custom message
     * @return ApiResponse with HttpStatus.OK (200)
     */
    public static ResponseEntity<ApiResponse> ok(String message) {
        final ApiResponse response = new ApiResponse();
        response.setStatus(HttpStatus.OK.value());
        response.setCode(HttpStatus.OK.name());
        response.setMessage(message);
        response.setTimestamp(LocalDateTime.now().toString());
        response.setData(null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * POST, PUT, DELETE - @PostMapping(""), @PutMapping("/{id}"), @DeleteMapping("/{id}")
     *
     * @param code    custom code
     * @param message custom message
     * @return ApiResponse with HttpStatus.OK (200)
     */
    public static ResponseEntity<ApiResponse> ok(String code, String message) {
        final ApiResponse response = new ApiResponse();
        response.setStatus(HttpStatus.OK.value());
        response.setCode(code);
        response.setMessage(message);
        response.setTimestamp(LocalDateTime.now().toString());
        response.setData(null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * GET, POST, PUT - @GetMapping("/{id}"), @PostMapping(""), @PutMapping("/{id}")
     *
     * @param data payload
     * @return ApiResponse with HttpStatus.OK (200)
     */
    public static ResponseEntity<ApiResponse> of(Object data) {
        final ApiResponse response = new ApiResponse();
        response.setStatus(HttpStatus.OK.value());
        response.setCode(HttpStatus.OK.name());
        response.setMessage(DEFAULT_MESSAGE);
        response.setData(data);
        response.setTimestamp(LocalDateTime.now().toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * GET, POST, PUT - @GetMapping("/{id}"), @PostMapping(""), @PutMapping("/{id}")
     *
     * @param message custom message
     * @param data    payload
     * @return ApiResponse with HttpStatus.OK (200)
     */
    public static ResponseEntity<ApiResponse> of(String message, Object data) {
        final ApiResponse response = new ApiResponse();
        response.setStatus(HttpStatus.OK.value());
        response.setCode(HttpStatus.OK.name());
        response.setMessage(message);
        response.setData(data);
        response.setTimestamp(LocalDateTime.now().toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * GET, POST, PUT - @GetMapping("/{id}"), @PostMapping(""), @PutMapping("/{id}")
     *
     * @param code    custom code
     * @param message custom message
     * @param data    payload
     * @return ApiResponse with HttpStatus.OK (200)
     */
    public static ResponseEntity<ApiResponse> of(String code, String message, Object data) {
        final ApiResponse response = new ApiResponse();
        response.setStatus(HttpStatus.OK.value());
        response.setCode(code);
        response.setMessage(message);
        response.setData(data);
        response.setTimestamp(LocalDateTime.now().toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    /**
     * OpenFeign Not Found Response
     *
     * @return ApiResponse with HttpStatus.NOT_FOUND (404)
     */
    public static ResponseEntity<ApiResponse> not() {
        final ApiResponse response = new ApiResponse();
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setCode(HttpStatus.NOT_FOUND.name());
        response.setMessage(DEFAULT_ERROR_MESSAGE);
        response.setTimestamp(LocalDateTime.now().toString());
        response.setData(null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
