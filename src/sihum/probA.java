package sihum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class probA {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int distance = Integer.parseInt(br.readLine());
        int rabbitL = 0;
        int cnt = 1;
        int sheepL = 0;
        while (true) {
            if (rabbitL >= distance && sheepL >= distance) {
                System.out.println("wow");
                break;
            }
            else if (rabbitL >= distance) {
                System.out.println("rabbit");
                break;
            } else if (sheepL >= distance) {
                System.out.println("sheep");
                break;
            }

            if (cnt % 3 == 0) {
                rabbitL += 3;
            }
            if (cnt % 2 == 0) {
                sheepL += 2;
            }
            
            cnt++;
        }
    }
}
