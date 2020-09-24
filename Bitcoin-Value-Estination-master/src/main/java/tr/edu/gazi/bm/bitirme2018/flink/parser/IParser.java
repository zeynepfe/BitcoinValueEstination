package tr.edu.gazi.bm.bitirme2018.flink.parser;

import java.io.Serializable;
import java.util.List;

public interface IParser<T extends Serializable> {

    public List<T> siteParserData(String url);

}
