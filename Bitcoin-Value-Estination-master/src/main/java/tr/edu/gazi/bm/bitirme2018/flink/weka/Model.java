package tr.edu.gazi.bm.bitirme2018.flink.weka;

public class Model {
    private int id;
    private String data;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", data='" + data + '\'' +
                '}';
    }
}
