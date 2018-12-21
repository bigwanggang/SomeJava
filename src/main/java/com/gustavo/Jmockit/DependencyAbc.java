package com.gustavo.Jmockit;

/**
 * Created by gustaov on 2017/8/19.
 */
public class DependencyAbc {
    String s;

    public int intReturningMethod() {
        return 1;
    }

    public String stringReturningMethod() throws SomeCheckedException {
        if (s.equals("haha"))
            throw new SomeCheckedException();
        else return s;
    }
}
