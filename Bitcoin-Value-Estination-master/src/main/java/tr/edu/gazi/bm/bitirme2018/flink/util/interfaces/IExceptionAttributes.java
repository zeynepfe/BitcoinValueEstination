package tr.edu.gazi.bm.bitirme2018.flink.util.interfaces;

import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by ramazancesur on 5/31/18.
 */
public interface IExceptionAttributes {
    Map<String, Object> getExceptionAttributes(Exception exception, HttpServletRequest httpRequest,
                                               HttpStatus httpStatus);
}
