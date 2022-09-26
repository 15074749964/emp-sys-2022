package cn.kgc;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class ArrayTest {

    @Test
    public void test(){
        String[] arr = "a,b,c".split(",");
        System.out.println(Arrays.asList(arr));
    }
}
