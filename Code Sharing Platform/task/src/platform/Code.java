package platform;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Code {

    @JsonIgnore
    private Long id;
    private String code;
    private String date;

    public Code() {
    }

    public Code(Long id, String code, String date) {
        this.id = id;
        this.code = code;
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
