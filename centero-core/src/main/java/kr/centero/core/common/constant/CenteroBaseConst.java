package kr.centero.core.common.constant;

/**
 * this class is for constants of centero application
 */
public class CenteroBaseConst {

    private CenteroBaseConst() {
        throw new IllegalStateException("Constant class");
    }

    /**
     * centero request timezone header : "TimezoneId"
     */
    public static final String CENTERO_REQUEST_TIMEZONE_HEADER = "TimezoneId";

    /**
     * default timezoneId : "UTC"
     */
    public static final String CENTERO_DEFAULT_TIMEZONE_ID = "UTC";

    /**
     * default return timezoneId : "Asia/Seoul"
     */
    public static final String CENTERO_DEFAULT_REQUEST_TIMEZONE_ID = "Asia/Seoul";



}
