package 코딩테스트공부.백준;

import java.io.*;
import java.util.*;

public class P5큐빙5373 {
    static char U[][], D[][], F[][], B[][], L[][], R[][], temp[][];

    public static void Uturn(char clock) {
        if (clock == '+') {
            // B 위, L 위, F 위, R 위
            for (int i = 0; i < 3; i++) {
                char temp = B[0][i];
                B[0][i] = L[0][i];
                L[0][i] = F[0][i];
                F[0][i] = R[0][i];
                R[0][i] = temp;
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    temp[j][2 - i] = U[i][j];
                }
            }
        } else {
            // B 위, R 위, F 위, L 위
            for (int i = 0; i < 3; i++) {
                char temp = B[0][i];
                B[0][i] = R[0][i];
                R[0][i] = F[0][i];
                F[0][i] = L[0][i];
                L[0][i] = temp;
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    temp[2 - j][i] = U[i][j];
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            System.arraycopy(temp[i], 0, U[i], 0, temp[i].length);
        }
    }

    public static void Dturn(char clock) {
        if (clock == '+') {
            // B 아래, R 아래, F 아래, L 아래
            for (int i = 0; i < 3; i++) {
                char temp = B[2][i];
                B[2][i] = R[2][i];
                R[2][i] = F[2][i];
                F[2][i] = L[2][i];
                L[2][i] = temp;
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    temp[j][2 - i] = D[i][j];
                }
            }
        } else {
            // B 아래, L 아래, F 아래, R 아래
            for (int i = 0; i < 3; i++) {
                char temp = B[2][i];
                B[2][i] = L[2][i];
                L[2][i] = F[2][i];
                F[2][i] = R[2][i];
                R[2][i] = temp;
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    temp[2 - j][i] = D[i][j];
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            System.arraycopy(temp[i], 0, D[i], 0, temp[i].length);
        }
    }

    public static void Fturn(char clock) {
        if (clock == '+') {
            // U 아래, R 왼쪽, D 위, L 오른쪽
            for (int i = 0; i < 3; i++) {
                char temp = U[2][i];
                U[2][i] = L[2 - i][2];
                L[2 - i][2] = D[0][2 - i];
                D[0][2 - i] = R[i][0];
                R[i][0] = temp;
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    temp[j][2 - i] = F[i][j];
                }
            }
        } else {
            // U 아래, L 오른쪽, D 위, R 왼쪽
            for (int i = 0; i < 3; i++) {
                char temp = U[2][2 - i];
                U[2][2 - i] = R[2 - i][0];
                R[2 - i][0] = D[0][i];
                D[0][i] = L[i][2];
                L[i][2] = temp;
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    temp[2 - j][i] = F[i][j];
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            System.arraycopy(temp[i], 0, F[i], 0, temp[i].length);
        }
    }

    public static void Bturn(char clock) {
        if (clock == '+') {
            // U 위, R 오른쪽, D 아래, L 왼쪽
            for (int i = 0; i < 3; i++) {
                char temp = U[0][i];
                U[0][i] = R[i][2];
                R[i][2] = D[2][2 - i];
                D[2][2 - i] = L[2 - i][0];
                L[2 - i][0] = temp;
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    temp[j][2 - i] = B[i][j];
                }
            }
        } else {

            // U 위, L 왼쪽, D 아래, R 오른쪽
            for (int i = 0; i < 3; i++) {
                char temp = U[0][i];
                U[0][i] = L[2 - i][0];
                L[2 - i][0] = D[2][2 - i];
                D[2][2 - i] = R[i][2];
                R[i][2] = temp;
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    temp[2 - j][i] = B[i][j];
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            System.arraycopy(temp[i], 0, B[i], 0, temp[i].length);
        }
    }

    public static void Lturn(char clock) {
        if (clock == '+') {
            // B, D, F, U
            for (int i = 0; i < 3; i++) {
                char temp = B[2 - i][2];
                B[2 - i][2] = D[i][0];
                D[i][0] = F[i][0];
                F[i][0] = U[i][0];
                U[i][0] = temp;
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    temp[j][2 - i] = L[i][j];
                }
            }
        } else {
            // U, F, D, B
            for (int i = 0; i < 3; i++) {
                char temp = U[i][0];
                U[i][0] = F[i][0];
                F[i][0] = D[i][0];
                D[i][0] = B[2 - i][2];
                B[2 - i][2] = temp;
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    temp[2 - j][i] = L[i][j];
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            System.arraycopy(temp[i], 0, L[i], 0, temp[i].length);
        }
    }

    public static void Rturn(char clock) {
        if (clock == '+') {
            // U 오른쪽, F 오른쪽, D 오른쪽, B 오른쪽
            for (int i = 0; i < 3; i++) {
                char temp = U[i][2];
                U[i][2] = F[i][2];
                F[i][2] = D[i][2];
                D[i][2] = B[2 - i][0];
                B[2 - i][0] = temp;
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    temp[j][2 - i] = R[i][j];
                }
            }
        } else {
            // U 오른쪽, B 오른쪽, D 오른쪽, F 오른쪽
            for (int i = 0; i < 3; i++) {
                char temp = U[i][2];
                U[i][2] = B[2 - i][0];
                B[2 - i][0] = D[i][2];
                D[i][2] = F[i][2];
                F[i][2] = temp;
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    temp[2 - j][i] = R[i][j];
                }
            }

        }
        for (int i = 0; i < 3; i++) {
            System.arraycopy(temp[i], 0, R[i], 0, temp[i].length);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int test_case = Integer.parseInt(br.readLine());
        U = new char[3][3];
        D = new char[3][3];
        F = new char[3][3];
        B = new char[3][3];
        L = new char[3][3];
        R = new char[3][3];
        temp = new char[3][3];

        for (int t = 0; t < test_case; t++) {
            fill();

            int N = Integer.parseInt(br.readLine());
            String[] cube = br.readLine().split(" ");
            for (int i = 0; i < N; i++) {

                char d = cube[i].charAt(0);
                char clock = cube[i].charAt(1);
                switch (d) {
                    case 'U':
                        Uturn(clock);
                        break;
                    case 'D':
                        Dturn(clock);
                        break;
                    case 'F':
                        Fturn(clock);
                        break;
                    case 'B':
                        Bturn(clock);
                        break;
                    case 'L':
                        Lturn(clock);
                        break;
                    case 'R':
                        Rturn(clock);
                        break;
                }
                // print();
                // System.out.println();
            }
            print();
        }
        bw.close();

    }

    public static void fill() {
        for (int i = 0; i < 3; i++) {
            Arrays.fill(U[i], 'w');
            Arrays.fill(D[i], 'y');
            Arrays.fill(F[i], 'r');
            Arrays.fill(B[i], 'o');
            Arrays.fill(L[i], 'g');
            Arrays.fill(R[i], 'b');
        }
    }

    public static void print() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                bw.write(U[i][j]);
            }
            bw.newLine();
        }
        bw.flush();

    }
}
// 위 U w, 아래 D y, 앞 F r, 뒷 B o, 왼쪽 L g, 오른쪽 R b
// https://www.acmicpc.net/problem/5373