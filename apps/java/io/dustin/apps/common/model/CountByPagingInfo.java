package io.dustin.apps.common.model;

import java.util.List;

public record CountByPagingInfo<T> (
        List<T> result,
        boolean isLast,
        Long nextId
){}
