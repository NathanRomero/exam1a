package joseromero.exam1a.externalrequests.weatherApi.schemas;
import joseromero.exam1a.externalrequests.weatherApi.schemas.listSchema;

public class forecast {
    public String cod;
    public int message;
    public int cnt;
    public java.util.List<listSchema> list;

    public forecast(){

    }

    public String getCod() {
        return this.cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public int getMessage() {
        return this.message;
    }

    public void setMessage(int message) {
        this.message = message;
    }

    public int getCnt() {
        return this.cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public java.util.List<listSchema> getList() {
        return this.list;
    }

    public void setList(java.util.List<listSchema> list) {
        this.list = list;
    }


}
