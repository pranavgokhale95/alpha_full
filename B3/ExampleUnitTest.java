package com.example.pranav.b3_calculator;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void sin_isCorrect() throws Exception{
        assertEquals(1, MainActivity.getSin(90),0.1);
    }

    @Test
    public void cos_isCorrect() throws Exception{
        assertEquals(0.5, MainActivity.getCos(60),0.1);
    }

    @Test
    public void tan_isCorrect() throws Exception{
        assertEquals(1, MainActivity.getTan(45),0.1);
    }

    @Test
    public void sqrt_isCorrect() throws Exception{
        assertEquals(1.414, MainActivity.getSqrt(2),0.1);
    }
}