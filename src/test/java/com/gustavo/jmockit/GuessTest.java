package com.gustavo.jmockit;

import mockit.Injectable;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by gustaov on 2017/8/22.
 */
@RunWith(JMockit.class)
public class GuessTest {
    @Tested        // 表明被修饰实例是将会被自动构建和注入的实例
            Guess guess = new Guess(3);
    @Injectable    // 表明被修饰实例将会自动注入到@Tested修饰的实例中，并且会自动mock掉，除非在测试前被赋值
            GuessDAO guessDAO;

    /**
     * 连续3次失败
     */
    @Test
    public void behaviorTest_fail3time() {

                 // 录制完成后，进行实际的代码调用，也称回放(replay)
    }
}