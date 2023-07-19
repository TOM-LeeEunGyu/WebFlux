package io.dustin.apps.common.utils;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
public enum TypeCode {

    ONE("A"),
    TWO("B"),
    THREE("C"),
    FOUR("D");

    @Getter
    private String code;


}
