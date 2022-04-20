package platform;

public class Code {

    private String code;
    private String date;
    private String codeSnippet = "public static void main(String[] args) {\n" +
            "    SpringApplication.run(CodeSharingPlatform.class, args);\n" +
            "}";

    public Code() {
        this.code = codeSnippet;
        this.date = LoadDate.getLoadDate();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    //    @JsonSerialize(using = LocalDateTimeSerializer.class)
//    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
//    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
