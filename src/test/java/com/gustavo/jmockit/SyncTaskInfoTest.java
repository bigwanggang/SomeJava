package com.gustavo.jmockit;

import mockit.Expectations;
import mockit.Tested;
import org.junit.Test;

/**
 * Created by gustaov on 2017/8/20.
 */
public class SyncTaskInfoTest {

    @Tested
    SyncTaskInfo syncTaskInfo;

    @Test
    public void testJudge() {
        new Expectations(syncTaskInfo) {
            {

            }
        };
    }
}