package tr.edu.gazi.bm.bitirme2018.flink.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.edu.gazi.bm.bitirme2018.flink.util.interfaces.IBaseController;
import tr.edu.gazi.bm.bitirme2018.flink.util.interfaces.IGenericDao;
import tr.edu.gazi.bm.bitirme2018.flink.util.interfaces.IGenericService;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by ramazancesur on 5/31/18.
 */
public abstract class BaseController<T extends BaseEntity> implements IBaseController<T> {
    @Autowired
    IGenericService<T, Long> genericService;

    /**
     * The Logger for this class.
     */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Handles JPA NoResultExceptions thrown from web service controller
     * methods. Creates a response with Exception Attributes as JSON and HTTP
     * status code 404, not found.
     *
     * @param noResultException A NoResultException instance.
     * @param request           The HttpServletRequest in which the NoResultException was
     *                          raised.
     * @return A ResponseEntity containing the Exception Attributes in the body
     * and HTTP status code 404.
     */
    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<Map<String, Object>> handleNoResultException(NoResultException noResultException,
                                                                       HttpServletRequest request) {

        logger.info("> handleNoResultException");

        DefaultIExceptionAttributes exceptionAttributes = new DefaultIExceptionAttributes();

        Map<String, Object> responseBody = exceptionAttributes.getExceptionAttributes(noResultException, request,
                HttpStatus.NOT_FOUND);

        logger.info("< handleNoResultException");
        return new ResponseEntity<Map<String, Object>>(responseBody, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles all Exceptions not addressed by more specific
     * <code>@ExceptionHandler</code> methods. Creates a response with the
     * Exception Attributes in the response body as JSON and a HTTP status code
     * of 500, internal server error.
     *
     * @param exception An Exception instance.
     * @param request   The HttpServletRequest in which the Exception was raised.
     * @return A ResponseEntity containing the Exception Attributes in the body
     * and a HTTP status code 500.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleException(Exception exception, HttpServletRequest request) {

        logger.error("> handleException");
        logger.error("- Exception: ", exception);

        DefaultIExceptionAttributes exceptionAttributes = new DefaultIExceptionAttributes();

        Map<String, Object> responseBody = exceptionAttributes.getExceptionAttributes(exception, request,
                HttpStatus.INTERNAL_SERVER_ERROR);

        logger.error("< handleException");
        return new ResponseEntity<Map<String, Object>>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Override
    @GetMapping("/all")
    public ResponseEntity<List<T>> getAll() {
        List<T> lstData= genericService.getAll();
        return new ResponseEntity(lstData, HttpStatus.OK);
    }

    @Override
    @GetMapping("/getByID/{id}")
    public ResponseEntity<T> getDataById(@PathVariable("id") Long id) {
        try {
            T data= genericService.get(id);
            return new ResponseEntity(data, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @PutMapping
    public ResponseEntity<Boolean> addData(@RequestBody T data) {
        T dataSaved= genericService.add(data);
        return new ResponseEntity(dataSaved, HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<Boolean> updateData(@RequestBody T data) {
        Boolean dataUpdated= genericService.update(data);
        return new ResponseEntity(dataUpdated, HttpStatus.OK);
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Boolean> deleteData(@RequestBody T data) {
        Boolean dataDeleted= genericService.remove(data);
        return null;
    }
}
