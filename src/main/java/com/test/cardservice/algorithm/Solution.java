package com.test.cardservice.algorithm;

import java.util.Scanner;

public class Solution {

    //Space Complexity 0(n) because loop through the entire array
    public static void main (String[] args) throws java.lang.Exception
    {
        System.out.println("Enter Values");
        Scanner s = new Scanner(System.in);
        int j = 1;
        int k = s.nextInt();
        while(k-- > 0)
        {
            int n = s.nextInt();
            int[] a = new int[n];
            int[] array1 = new int[n];
            int[] array2 = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = s.nextInt();
                array1[i] = a[i] >> 16;
                array2[i] = a[i] - (array1[i]<<16);
            }
            System.out.println("Case " +j +":");
            j++;
            for (int i = 0; i < n; i++) {
                System.out.print(array2[i] + " ");
            }
            System.out.println();
            for (int i = 0; i < n; i++) {
                System.out.print(array1[i] + " ");
            }
            System.out.println();
        }

    }
}
