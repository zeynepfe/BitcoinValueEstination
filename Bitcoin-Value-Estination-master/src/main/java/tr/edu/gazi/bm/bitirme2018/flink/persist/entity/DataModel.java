package tr.edu.gazi.bm.bitirme2018.flink.persist.entity;

import tr.edu.gazi.bm.bitirme2018.flink.interfaces.DataMatch;
import tr.edu.gazi.bm.bitirme2018.flink.util.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by flink on 2/23/18.
 */
@Entity
@Table(name = "RealTimeDataModel")
public class DataModel extends BaseEntity {
    @DataMatch(index = 1)
    @Column
    private double simdi;
    @DataMatch(index = 2)
    @Column
    private double acilis;
    @DataMatch(index = 3)
    @Column
    private double yuksek;
    @DataMatch(index = 4)
    @Column
    private double dusuk;
    @DataMatch(index = 5)
    @Column
    private double hacim;
    @DataMatch(index = 6)
    @Column
    private double fark;
    @DataMatch(index = 7, dataClass = true)
    @Column
    private int sinif;

    public double getSimdi() {
        return simdi;
    }

    public void setSimdi(double simdi) {
        this.simdi = simdi;
    }

    public double getAcilis() {
        return acilis;
    }

    public void setAcilis(double acilis) {
        this.acilis = acilis;
    }

    public double getYuksek() {
        return yuksek;
    }

    public void setYuksek(double yuksek) {
        this.yuksek = yuksek;
    }

    public double getDusuk() {
        return dusuk;
    }

    public void setDusuk(double dusuk) {
        this.dusuk = dusuk;
    }

    public double getHacim() {
        return hacim;
    }

    public void setHacim(double hacim) {
        this.hacim = hacim;
    }

    public double getFark() {
        return fark;
    }

    public void setFark(double fark) {
        this.fark = fark;
    }

    public int getSinif() {
        return sinif;
    }

    public void setSinif(int sinif) {
        this.sinif = sinif;
    }

    @Override
    public String toString() {
        return "DataModel{" +
                " simdi=" + simdi +
                ", acilis=" + acilis +
                ", yuksek=" + yuksek +
                ", dusuk=" + dusuk +
                ", hacim=" + hacim +
                ", fark=" + fark +
                ", sinif=" + sinif +
                '}';
    }
}
