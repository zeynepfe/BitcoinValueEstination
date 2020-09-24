package tr.edu.gazi.bm.bitirme2018.flink.util;

import org.springframework.http.HttpStatus;
import tr.edu.gazi.bm.bitirme2018.flink.util.interfaces.IExceptionAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by ramazancesur on 5/31/18.
 */
public class DefaultIExceptionAttributes implements IExceptionAttributes {
    /**
     * The timestamp attribute key.
     */
    public static final String TIMESTAMP = "timestamp";
    /**
     * The status attribute key.
     */
    public static final String STATUS = "status";
    /**
     * The error attribute key.
     */
    public static final String ERROR = "error";
    /**
     * The exception attribute key.
     */
    public static final String EXCEPTION = "exception";
    /**
     * The message attribute key.
     */
    public static final String MESSAGE = "message";
    /**
     * The path attribute key.
     */
    public static final String PATH = "path";

    @Override
    public Map<String, Object> getExceptionAttributes(Exception exception, HttpServletRequest httpRequest,
                                                      HttpStatus httpStatus) {

        Map<String, Object> exceptionAttributes = new LinkedHashMap<String, Object>();

        exceptionAttributes.put(TIMESTAMP, Calendar.getInstance());
        addHttpStatus(exceptionAttributes, httpStatus);
        addExceptionDetail(exceptionAttributes, exception);
        addPath(exceptionAttributes, httpRequest);

        return exceptionAttributes;
    }

    /**
     * Adds the status and error attribute values from the {@link HttpStatus}
     * value.
     *
     * @param exceptionAttributes The Map of exception attributes.
     * @param httpStatus          The HttpStatus enum value.
     */
    private void addHttpStatus(Map<String, Object> exceptionAttributes, HttpStatus httpStatus) {
        exceptionAttributes.put(STATUS, httpStatus.value());
        exceptionAttributes.put(ERROR, httpStatus.getReasonPhrase());
    }

    /**
     * Adds the exception and message attribute values from the
     * {@link Exception}.
     *
     * @param exceptionAttributes The Map of exception attributes.
     * @param exception           The Exception object.
     */
    private void addExceptionDetail(Map<String, Object> exceptionAttributes, Exception exception) {
        exceptionAttributes.put(EXCEPTION, exception.getClass().getName());
        exceptionAttributes.put(MESSAGE, exception.getMessage());
    }

    /**
     * Adds the path attribute value from the {@link HttpServletRequest}.
     *
     * @param exceptionAttributes The Map of exception attributes.
     * @param httpRequest         The HttpServletRequest object.
     */
    private void addPath(Map<String, Object> exceptionAttributes, HttpServletRequest httpRequest) {
        exceptionAttributes.put(PATH, httpRequest.getServletPath());
    }

}
