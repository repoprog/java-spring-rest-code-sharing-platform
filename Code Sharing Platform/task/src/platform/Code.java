package platform;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Code {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String code;
    private String date;
    private Long time;
    private int views;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String uuid;
    @JsonIgnore
    // WARNING: to ignore boolean field CAN"T be named with IS!
    // have to match methods: setRestricted(), isRestricted()(or getRestricted())
    private String restricted;
}