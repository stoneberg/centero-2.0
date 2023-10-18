package kr.centero.netzero.common.payload;

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
public class ApiResponse {
    private static final String DEFAULT_MESSAGE = "SUCCESS";
    private Boolean success = true;
    private Object data;
    private Integer status;
    private String code;
    private String message;
    private String timestamp;

    static final ApiResponse response = new ApiResponse();

    /**
     * POST, PUT, DELETE - @PostMapping(""), @PutMapping("/{id}"), @DeleteMapping("/{id}")
     *
     * @return ApiResponse with HttpStatus.OK (200)
     */
    public static ResponseEntity<ApiResponse> ok() {
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
    public static ResponseEntity<ApiResponse> ok(Object data) {
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
    public static ResponseEntity<ApiResponse> ok(String message, Object data) {
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
    public static ResponseEntity<ApiResponse> ok(String code, String message, Object data) {
        response.setStatus(HttpStatus.OK.value());
        response.setCode(code);
        response.setMessage(message);
        response.setData(data);
        response.setTimestamp(LocalDateTime.now().toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * POST - @PostMapping("")
     *
     * @param data payload
     * @return ApiResponse with HttpStatus.CREATED (201)
     */
    public static ResponseEntity<ApiResponse> is(Object data) {
        response.setStatus(HttpStatus.CREATED.value());
        response.setCode(HttpStatus.CREATED.name());
        response.setMessage(DEFAULT_MESSAGE);
        response.setData(data);
        response.setTimestamp(LocalDateTime.now().toString());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * POST - @PostMapping("")
     *
     * @param message custom message
     * @param data    payload
     * @return ApiResponse with HttpStatus.CREATED (201)
     */
    public static ResponseEntity<ApiResponse> is(String message, Object data) {
        response.setStatus(HttpStatus.CREATED.value());
        response.setCode(HttpStatus.CREATED.name());
        response.setMessage(message);
        response.setData(data);
        response.setTimestamp(LocalDateTime.now().toString());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
