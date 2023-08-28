package io.github.josecarlosbran.JBRestAPI;

import lombok.*;

@ToString(includeFieldNames = true)
@AllArgsConstructor
public class ParametroOfRoute {

    @Getter
    @Setter
    @NonNull
    private String name;
    @Getter
    @Setter
    @NonNull
    private Object value;

}
