package votre.com.co.apache_camel.mybatis.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParamsMediaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String company;
    private String media;

}
