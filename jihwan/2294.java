package coding_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K, LIMIT = 100000;
    static int[][] dp;
    static int[] coins;

    // 메모이제이션하는 배열을 늘리고, dp[i][j]의 뜻은 i개의 동전들로 j 값을 만들 수 있는 최소의 개수라는 뜻.

    public static void main(String[] args) throws IOException {
        input();
        int answer = logic(N, K);
        System.out.println(answer == LIMIT ? -1 : answer);
    }

    private static int logic(int count, int value) {
        if(count == 0) return value == 0 ? 0 : LIMIT;
        if(dp[count][value] != -1) return dp[count][value];

        int res = logic(count-1, value); // coin의 크기를 N으로 설정했기 때문에, 원하는 위치를 호출하기 위해서는 -1 해야함. 만약 처음부터 1~N 까지 받는다면 이런 짓은 안해도 된다..!
        if(value >= coins[count-1]) res = Math.min(res, logic(count, value-coins[count-1])+1);
        dp[count][value] = res;
        return res;
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        coins = new int[N];
        dp = new int[N+1][K+1];

        for(int i = 0; i <= N; i++)
            for(int j = 0; j <= K; j++)
                dp[i][j] = -1;

        for (int i = 0; i < N; i++)
            coins[i] = Integer.parseInt(br.readLine());

    }
}