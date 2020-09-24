package tr.edu.gazi.bm.bitirme2018.flink.interfaces;

import java.io.Serializable;
import java.util.List;

/**
 * Created by flink on 2/23/18.
 */
public interface IReader {
    <T extends Serializable> List<T> reader(String readPath, String splitCharakter,
                                            Class<T> clazz);
}
