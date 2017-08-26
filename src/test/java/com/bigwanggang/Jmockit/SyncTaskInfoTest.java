package com.bigwanggang.Jmockit;

import com.sun.corba.se.impl.orbutil.concurrent.Sync;
import mockit.Expectations;
import mockit.Mocked;
import mockit.Tested;
import org.junit.Test;

import static org.junit.Assert.*;

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