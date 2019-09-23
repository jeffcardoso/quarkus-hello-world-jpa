package org.acme.model;

import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;

public class Pageable {

    private Sort sort;
    private Page page;

    public enum SortDirection {
        ASC("asc", Sort.Direction.Ascending),
        DESC("desc", Sort.Direction.Descending);

        private String acronym;
        private Sort.Direction direction;

        SortDirection(String acronym, Sort.Direction direction) {
            this.acronym = acronym;
            this.direction = direction;
        }

        public String getAcronym() {
            return acronym;
        }

        public Sort.Direction getDirection() {
            return direction;
        }
    }

    public Pageable(int pageNumber, int pageSize, String columnName, SortDirection direction) {
        sort = Sort.by(columnName, direction.getDirection());
        page = Page.of(pageNumber, pageSize);
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
