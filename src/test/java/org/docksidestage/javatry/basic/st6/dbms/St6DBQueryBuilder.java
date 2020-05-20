package org.docksidestage.javatry.basic.st6.dbms;

/**
 * @author chunsheng.chung
 */
/*
 done [chung] ここのクラス名は考えたいですね。 by subaru (2020/05/20)
 このクラスの具象クラスはどういうものかを考えてみましょう。
 現状では MySql, PostgreSql の二つですが、他にどのようなものが増えるかと考えると OracleDb などが予想されます。
 OracleDb は Sql ということはできるでしょうか？
 今後どのような具象クラスが増えるかということを想定しながら命名できると良いです！
*/
public abstract class St6DBQueryBuilder {
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
