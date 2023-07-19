package io.dustin.apps.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResponseWithScroll<T> {
    private T result;
    private boolean isLast;
    private Long nextId;

    public static <T> ResponseWithScroll<T> from(T result, boolean isLast, Long nextId) {
        return new ResponseWithScroll<>(result, isLast, nextId);
    }
}
