package 코딩테스트공부.B형공부.Pro비용과시간;

import java.util.Scanner;

class Solution {
    private final static int MAX_E = 500;
    private final static int CMD_INIT = 100;
    private final static int CMD_ADD = 200;
    private final static int CMD_COST = 300;

    private final static UserSolution usersolution = new UserSolution();

    private static boolean run(Scanner sc) throws Exception {
        int q = sc.nextInt();

        int n, k, m;
        int[] sCityArr = new int[MAX_E];
        int[] eCityArr = new int[MAX_E];
        int[] mCostArr = new int[MAX_E];
        int[] mTimeArr = new int[MAX_E];
        int sCity, eCity, mCost, mTime;
        int cmd, ans, ret = 0;
        boolean okay = false;

        for (int i = 0; i < q; ++i) {
            cmd = sc.nextInt();
            switch (cmd) {
                case CMD_INIT:
                    okay = true;
                    n = sc.nextInt();
                    k = sc.nextInt();
                    for (int j = 0; j < k; j++) {
                        sCityArr[j] = sc.nextInt();
                        eCityArr[j] = sc.nextInt();
                        mCostArr[j] = sc.nextInt();
                        mTimeArr[j] = sc.nextInt();
                    }
                    usersolution.init(n, k, sCityArr, eCityArr, mCostArr, mTimeArr);
                    break;
                case CMD_ADD:
                    sCity = sc.nextInt();
                    eCity = sc.nextInt();
                    mCost = sc.nextInt();
                    mTime = sc.nextInt();
                    usersolution.add(sCity, eCity, mCost, mTime);
                    break;
                case CMD_COST:
                    m = sc.nextInt();
                    sCity = sc.nextInt();
                    eCity = sc.nextInt();
                    ans = sc.nextInt();
                    ret = usersolution.cost(m, sCity, eCity);
                    if(ret != ans)
                        okay = false;
                    break;          
                default:
                    okay = false;
                    break;
            }
        }

        return okay;
    }

    public static void main(String[] args) throws Exception {
        int TC, MARK;

        System.setIn(Solution.class.getResourceAsStream("res/sample_input.txt"));

        Scanner sc = new Scanner(System.in);

        TC = sc.nextInt();
        MARK = sc.nextInt();

        for (int testcase = 1; testcase <= TC; ++testcase) {
            int score = run(sc) ? MARK : 0;
            System.out.println("#" + testcase + " " + score);
        }

        sc.close();
    }
}