package org.docksidestage.javatry.basic.st6.dbms;

/**
 * @author chunsheng.chung
 */
public abstract class St6Sql {
    public final String buildPagingQuery(int pageSize, int pageNumber) {
        int offset = getPageOffset(pageSize, pageNumber);
        return getPagingQueryString(offset, pageSize);
    }

    private int getPageOffset(int pageSize, int pageNumber) {
        int offset = pageSize * (pageNumber - 1);
        return offset;
    }

    protected abstract String getPagingQueryString(int offset, int pageSize);
}
