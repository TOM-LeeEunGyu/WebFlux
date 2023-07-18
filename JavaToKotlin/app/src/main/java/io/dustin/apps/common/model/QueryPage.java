package io.dustin.apps.common.model;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Data
@Slf4j
@ToString
public class QueryPage {

    private int page;
    private int size;
    private Long nextId;
    private String column;

    private Sort.Direction sort;

    public int offset() {
        return this.page - 1;
    }

    public int limit() {
        return this.size;
    }

    public PageRequest pageable() {
        Sort resultSort = Sort.unsorted();
        if(column != null && sort != null) {
            resultSort = Sort.by(this.sort, column);
        }
        return PageRequest.of(offset(), limit(), resultSort);
    }

}
