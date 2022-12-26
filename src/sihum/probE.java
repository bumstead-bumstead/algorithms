package sihum;

import java.io.*;
import java.util.StringTokenizer;

public class probE {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] image = new int[n][m];
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int temp = Integer.parseInt(st.nextToken());
                max = max < temp ? temp : max;
                min = min > temp ? temp : min;
                image[i][j] = temp;
            }
        }
        int minOcha = Integer.MAX_VALUE;
        int minx = -1;
        int miny = -1;
        for (int i = 0; i <= 128; i++) {
            for (int j = i + 1; j <= 128; j++) {
                int tmpOcha = getOcha(image, i, j);
//                System.out.println(tmpOcha);
                if (minOcha > tmpOcha) {
                    minOcha = tmpOcha;
                    minx = i;
                    miny = j;
                }
            }
        }

        bw.write(minOcha+"");
        bw.newLine();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                StringBuilder sb = new StringBuilder();
                sb.append(Math.abs(minx - image[i][j]) < Math.abs(miny - image[i][j])
                        ? minx : miny).append(" ");
                bw.write(sb.toString());
            }
            bw.newLine();
        }

        bw.close();

    }

    private static int getOcha(int[][] image, int n1, int n2) {
        int result = 0;
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                int tmpLength = Math.abs(n1 - image[i][j]) < Math.abs(n2 - image[i][j])
                        ? Math.abs(n1 - image[i][j]) : Math.abs(n2 - image[i][j]);
                result += tmpLength * tmpLength;
            }
        }
        return result;
    }
}
