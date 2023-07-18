package io.dustin.apps.common.utils;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.function.BiFunction;

public enum SelectDate {
    TYPE_ONE(0, "yyyyMMdd", (localDateTime, pattern) -> DateUtils.localDateTimeToStringWithPattern(localDateTime, pattern )),
    TYPE_TWO(1, "yyyy/MM/dd", (localDateTime, pattern) -> DateUtils.localDateTimeToStringWithPattern(localDateTime, pattern )),
    TYPE_THREE(2,"yyyyMMddHHmmss", (localDateTime, pattern) -> DateUtils.localDateTimeToStringWithPattern(localDateTime, pattern )),
    TYPE_FOUR(3,"yyyy-MM-dd hh:mm:ss", (localDateTime, pattern) -> DateUtils.localDateTimeToStringWithPattern(localDateTime, pattern ));

    /** enum pattern */
    @Getter
    private int index;
    /** enum code */
    @Getter
    private String pattern;

    /** enum dateConvert */
    private BiFunction<LocalDateTime, String, String > convert;

    public String convertDate(LocalDateTime localDateTime, String pattern){
        return convert.apply(localDateTime, pattern);
    }



    SelectDate(int index, String pattern, BiFunction<LocalDateTime, String, String> convert ){
        this.index = index;
        this.pattern = pattern;
        this.convert = convert;
    }

    public String transform(LocalDateTime localDateTime){
        return DateUtils.localDateTimeToStringWithPattern(localDateTime, this.pattern );

    }


    }
