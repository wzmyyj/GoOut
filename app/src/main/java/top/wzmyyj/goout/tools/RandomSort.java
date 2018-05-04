package top.wzmyyj.goout.tools;

import java.util.Random;

/**
 * Created by wzm on 2018/5/4 0004.
 */

public class RandomSort {

    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            Random random = new Random();
            int p = random.nextInt(i + 1);
            int tmp = arr[i];
            arr[i] = arr[p];
            arr[p] = tmp;
        }
    }

    public static int[] getInt(int l) {
        int[] a = new int[l];

        for (int i = 0; i < l; i++) {
            a[i] = i;
        }
        sort(a);
        return a;
    }
}
