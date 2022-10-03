package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        List arr = new ArrayList();
        arr.add(5);
        arr.add(6);
        arr.add(5);
        arr.add(7);
        arr.add(7);
        arr.add(8);

        int n = arr.size();
        int k = 14;

        int pairs = 0;

        HashMap<Integer,Integer> cntFre = new HashMap<Integer,Integer>();

        for (int i = 0; i < n; i++)
        {
            if(cntFre.containsKey(arr.get(i)))

                cntFre.put((int)arr.get(i), cntFre.get(arr.get(i)) + 1);

            else
                cntFre.put((int)arr.get(i), 1);
        }

        for (Map.Entry<Integer,Integer> it : cntFre.entrySet())
        {
            int i = it.getKey();

            if (2 * i == k)
            {
                if (cntFre.get(i) > 1)

                    pairs += 2;
            }

            else
            {
                if (cntFre.containsKey(k - i))
                {
                    pairs += 1;
                }
            }
        }

        pairs = pairs / 2;

        System.out.print(arr + ", " + k + " => ");
        System.out.println(pairs);
        System.out.println("Time Complexity: O(N)");
    }


}



