package ru.Artur.headhunt;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class SimpleTest {
    @Test
    public void test() {
        int x = 2;
        int y = 23;
        Assert.assertEquals(25, x + y);
        Assert.assertEquals(46, x * y);
//        Assert.assertEquals("expected value", function("returns expected value"));
    }
}
