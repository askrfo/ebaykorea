package examples.boot.mybatis.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class City implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String state;

    private String country;

}