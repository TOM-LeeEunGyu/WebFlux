package io.dustin.apps.common.utils;

import io.dustin.apps.common.exception.DataNotFoundException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OptionalUtils {


    public static <T> T getEntity(Optional<T> optional, Class<T> clazz, String errorMessage) {
        if(optional.isPresent()) {
            return optional.get();
        } else {
            throw new DataNotFoundException("데이터가 없습니다.");
        }
    }

}
