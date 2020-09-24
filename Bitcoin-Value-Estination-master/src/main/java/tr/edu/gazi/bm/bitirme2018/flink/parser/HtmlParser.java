package tr.edu.gazi.bm.bitirme2018.flink.parser;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import tr.edu.gazi.bm.bitirme2018.flink.helper.Helper;
import tr.edu.gazi.bm.bitirme2018.flink.persist.entity.DataModel;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class HtmlParser implements IParser<DataModel> {

    Helper helper;

    public HtmlParser() {
        helper = new Helper();
    }

    private Double setDataField(String fieldValue) {
        try{
            return Double.valueOf(fieldValue.replace(".", "").replace(",", "."));
        } catch (Exception ex){
            ex.printStackTrace();
        }
      return 0.0;
    }


    @Override
    public List<DataModel> siteParserData(String url) {
        WebClient webClient = new WebClient();
        HtmlPage myPage = null;
        try {
            webClient.getOptions().setUseInsecureSSL(true);
            webClient.getOptions().setCssEnabled(false);//if you don't need css
            webClient.getOptions().setThrowExceptionOnScriptError(false);
            myPage = webClient.getPage(url);

        } catch (FailingHttpStatusCodeException | IOException e) {
            e.printStackTrace();
        }

        List<DomElement> lstElement =
                myPage.getByXPath("//div[@class='overViewBox instrument' ]");

        List<DataModel> lstData = new LinkedList<>();

        for (DomElement element : lstElement) {

            String selectedData = element.asText();

            Pattern p = Pattern.compile("((.*[0-9]))");

            List<String> essantialData = Arrays.stream(selectedData.split("\r\n"))
                    .filter(x -> !x.trim().equals("") && p.matcher(x.trim()).find())
                    .collect(Collectors.toList());
            DataModel dataModel = new DataModel();

            for (int i = 0; i < essantialData.size(); i++) {
                if (i == 0) {
                    String[] nowValues = essantialData.get(i).split(" ");
                    double currentValue = setDataField(nowValues[i].split(" ")[0]);
                    double fark = setDataField(nowValues[1]);
                    double acilis = currentValue - fark;
                    dataModel.setSimdi(currentValue);
                    dataModel.setAcilis(helper.round(acilis, 1));
                    dataModel.setFark(fark);
                    double yuzdeFark =
                            setDataField(essantialData.get(0).split("\n")[0].split("   ")[1].replace("%",""));


                } else if (i == 1) {
                    continue;
                } else if (i == 2) {
                    double hacim = this.setDataField(essantialData.get(i).split(" ")[1]);
                    dataModel.setHacim(hacim * 1000.0);

                } else if (i == 3) {
                    continue;
                } else if (i == 4) {

                    String[] lowHigh = essantialData.get(i).split(" ");
                    Double low = setDataField(lowHigh[2]);
                    Double high = setDataField(lowHigh[4]);
                    dataModel.setDusuk(low);
                    dataModel.setYuksek(high);
                }
            }
            lstData.add(dataModel);
        }
        return lstData;
    }

}
