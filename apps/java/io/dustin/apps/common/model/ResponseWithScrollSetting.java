package io.dustin.apps.common.model;

import io.dustin.apps.common.exception.DataNotFoundException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Comparator;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseWithScrollSetting {

    public static <T extends IdAble> CountByPagingInfo<T> getCountByPagingInfo(List<T> result, int realSize) {
        int querySize = realSize + 1;

        List<T> toClient;
        boolean isLast;
        Long nextId;
        if (result.size() <= realSize) {
            isLast = true;
            nextId = null;
            toClient = result;
        } else {
            isLast = false;
            toClient = result.subList(0, realSize);
            nextId = toClient.stream()
                    .sorted(Comparator.comparing(T::getId))
                    .findFirst().orElseThrow(() -> new DataNotFoundException("데이터에 문제가 있습니다.")).getId();
        }
        return new CountByPagingInfo(toClient, isLast, nextId);
    }
}
