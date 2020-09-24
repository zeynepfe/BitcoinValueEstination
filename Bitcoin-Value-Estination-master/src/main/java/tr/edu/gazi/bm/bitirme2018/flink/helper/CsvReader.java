package tr.edu.gazi.bm.bitirme2018.flink.helper;

import tr.edu.gazi.bm.bitirme2018.flink.interfaces.DataMatch;
import tr.edu.gazi.bm.bitirme2018.flink.interfaces.IReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by flink on 2/23/18.
 */
public class CsvReader<T extends Serializable> implements IReader {


    public static boolean setField(Object targetObject, String fieldName, Object fieldValue) {
        Field field;
        try {
            field = targetObject.getClass().getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            field = null;
        }

        field.setAccessible(true);
        try {
            Class<?> type = field.getType();
            Property property = new Property<>(type);
            try {
                property.setValue(fieldValue.toString());
            } catch (NumberFormatException ex) {
                Double value = Double.parseDouble(fieldValue.toString().replace(".", "")) / 100;
                property.setValue(value.toString());

            }
            field.set(targetObject, property.value);
            return true;
        } catch (IllegalAccessException e) {
            return false;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return false;
    }

    public <T extends Serializable> List<T> reader(String readPath, String splitChareckter,
                                                   Class<T> clazz) {
        // TODO Auto-generated method stub
        List<T> lstData = new LinkedList<>();
        String line = "";

        try (BufferedReader br = new BufferedReader(new FileReader(readPath))) {
            while ((line = br.readLine()) != null) {

                String[] readerArray = line.split(splitChareckter);
                int sayac = 0;
                T data = clazz.newInstance();
                for (Field field : clazz.getDeclaredFields()) {
                    try {
                        if (field.isAnnotationPresent(DataMatch.class)) {
                            // elimizde bulunan datadaki bazı colomnları kullanmak istemiyorum
                            // fakat datayı da bozmak istemediğim için kendi custom anotationumu yazdım
                            DataMatch dataMatch = field.getAnnotation(DataMatch.class);
                            int tempCount = 0;
                            // burada da esas işlemi yaptık :) bunu anlayığ yaznana ladar 9 ayım geçmişti eferinnnnn


                            // hemen nasıl anladın ya :))) b

                            //uuuuuuuuuuuuuuuuuuuuuuuu //sorgulamayacaksın :D şaka yapıyom ya şuan kulağımın ağrısından kafm pek almıyo açıkcası
                            //inşallah bi sorun çıkmaz  abi git ya doktora devam ederse mecbur gidecem tabi

                            // dusdan çıkınca iyice kulağını temizle su kalmasın  :) //olur abisi :D


                            ////boğazımda aprıyo toptan bi grip olucam anlaşılan gel vatandaş batan geminin malları bunlar :)) :)
                            // şimdi ne drumdayız son olarak dur bi bakayım


                            // son datayı yazarken bi problem olmuş onun dışında uçusa hazır :D
                            if (dataMatch.index() != -1) {
                                tempCount = dataMatch.index();
                            } else {
                                tempCount = sayac;
                            }
                            if (readerArray[tempCount].contains("K")) {
                                String valueOfNoneK = readerArray[tempCount].replace(".", "").replace("K", "");
                                Double realValue = Double.valueOf(valueOfNoneK) * 1000;
                                readerArray[tempCount] = Double.toString(realValue);
                            }
                            setField(data, field.getName(), readerArray[tempCount]);
                        }
                        sayac++;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                lstData.add(data);

            }

            return lstData;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

}
