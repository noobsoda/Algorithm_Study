package 코딩테스트공부;

import java.io.*;
import java.util.*;

public class P5모노미노도미노1235 {
  static int N, score = 0;
  static int bluemap[][];
  static int redmap[][];
  static Queue<Node> q;
  static List<Node> redList, blueList;
  static int dx[][] = { { 0 }, { 0, 0 }, { 0, 1 } };
  static int dy[][] = { { 0 }, { 0, 1 }, { 0, 0 } };

  public static void Print(Node n) {

    System.out.println(n);

    System.out.println();
    for (int i = 0; i < 4; i++) {
      System.out.println(Arrays.toString(bluemap[i]));
    }
    System.out.println(blueList);

    System.out.println();
    for (int i = 0; i < 6; i++) {
      System.out.println(Arrays.toString(redmap[i]));
    }
    System.out.println(redList);
    System.out.println();
  }

  public static void push() {

    // 나누는 함수 추가

    // 탐색하고 땡기기
    // 최대 2번 가능함
    int bluemax = 0;
    int redmax = 0;
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 4; j++) {
        if (bluemap[j][i] == 1) {
          bluemax++;
          break;
        }
      }

      for (int j = 0; j < 4; j++) {
        if (redmap[i][j] == 1) {
          redmax++;
          break;
        }

      }
    }

    for (int n = 0; n < 2; n++) {
      boolean blueflag = false;
      boolean redflag = false;
      // 블루 최대횟수 만큼 땡기기
      if (bluemax > 0) {
        blueflag = true;
        bluemax--;
      }
      // 레드 최대횟수 만큼 땡기기
      if (redmax > 0) {
        redflag = true;
        redmax--;
      }
      // 0부터 4까지 고려해서 땡기기
      for (int i = 4; i >= 0; i--) {
        for (int j = 0; j < 4; j++) {

          if (blueflag) {
            bluemap[j][i + 1] = bluemap[j][i];
            bluemap[j][i] = 0;
          }
        }

        for (int j = 0; j < 4; j++) {
          if (redflag) {
            redmap[i + 1][j] = redmap[i][j];
            redmap[i][j] = 0;
          }
        }

      }
      Node now;
      // 리스트에 있는 끝에 있는 것들중에 2, 3번 나누기 할거면 1로 바꿔버리고 그게 아니라면 삭제
      if (blueflag) {
        for (Iterator<Node> it = blueList.iterator(); it.hasNext();) {
          now = it.next();
          // 끝에 있는 1,3번 블럭들
          if (now.y == 5) {
            it.remove();
          } else if (now.y == 4 && now.t == 2) {
            now.t = 1;
          }
          // 한칸씩 오른쪽으로
          now.y++;
        }
      }
      if (redflag) {
        for (Iterator<Node> it = redList.iterator(); it.hasNext();) {
          now = it.next();

          // 끝에 있는 1,2번 블럭들
          if (now.x == 5) {
            it.remove();
          } else if (now.x == 4 && now.t == 3) {
            now.t = 1;
          }

          // 한칸씩 내려가
          now.x++;

        }
      }

    }

  }

  public static boolean explore() {

    boolean endflag = false;

    // 한칸 땡기는거 전면 수정
    // 리스트 나누기 블루일때 2 레드일때 3

    // 점수 획득
    // 탐색하고 제거
    // 최대 2번 가능함

    // 1개 터질경우와 2개 다 터질 경우

    int bluebomb[] = new int[2];
    int redbomb[] = new int[2];
    int bluecnt = 0;
    int redcnt = 0;

    Arrays.fill(bluebomb, -1);
    Arrays.fill(redbomb, -1);

    for (int i = 5; i >= 0; i--) {
      int bluesum = 0;
      int redsum = 0;
      for (int j = 0; j < 4; j++) {
        if (bluemap[j][i] == 1) {
          bluesum++;
        }
      }

      for (int j = 0; j < 4; j++) {
        if (redmap[i][j] == 1) {
          redsum++;
        }
      }

      // 4개 해당 지역 지워버리고 인덱스 저장
      if (bluesum == 4) {
        for (int j = 0; j < 4; j++) {
          bluemap[j][i] = 0;
        }
        bluebomb[bluecnt++] = i;
        score++;
        endflag = true;
        bluesum = 0;

      }
      if (redsum == 4) {
        for (int j = 0; j < 4; j++) {
          redmap[i][j] = 0;
        }
        redbomb[redcnt++] = i;
        score++;
        endflag = true;
        redsum = 0;
      }
    }

    // 리스트 쭉 돌아서 해당지역과 겹치는 값 지우고 잘리면 나누기

    Node now = null;

    if (bluebomb[0] != -1) {
      for (Iterator<Node> it = blueList.iterator(); it.hasNext();) {
        now = it.next();

        // 도형이 2이면서 머리나 다리가 짤린 경우
        if (now.t == 2) {
          if (bluemap[now.x][now.y] == 0 && bluemap[now.x][now.y + 1] == 0) {
            it.remove();
          } else if (bluemap[now.x][now.y + 1] == 0) {
            now.t = 1;
          } else if (bluemap[now.x][now.y] == 0) {
            now.t = 1;
            now.y++;
          }

        }

        // 도형이 1이랑 3이면서 현재 본인 위치가 사라진 경우
        else if (bluemap[now.x][now.y] == 0) {
          it.remove();
        }
      }
    }
    if (redbomb[0] != -1) {
      for (Iterator<Node> it = redList.iterator(); it.hasNext();) {
        now = it.next();

        // 도형이 3이면서 머리나 다리가 짤린 경우
        if (now.t == 3) {
          if (redmap[now.x][now.y] == 0 && redmap[now.x + 1][now.y] == 0) {
            it.remove();
          } else if (redmap[now.x + 1][now.y] == 0) {
            now.t = 1;
          } else if (redmap[now.x][now.y] == 0) {
            now.x++;
            now.t = 1;
          }
        }
        // 도형이 1이나 2면서 현재 본인 위치가 사라진 경우
        else if (redmap[now.x][now.y] == 0) {
          it.remove();
        }
      }
    }

    // 모노미노푸시로 인덱스보다 위 부분 다 보내기

    int blueindex = -1;
    int redindex = -1;
    // 제일 높은게 터진 인덱스
    for (int i = 1; i >= 0; i--) {
      if (bluebomb[i] != -1)
        blueindex = bluebomb[i];
      if (redbomb[i] != -1)
        redindex = redbomb[i];
    }
    // 현재 터트린 블록보다 위에 있는 블록들을 내려줘야 함
    if (blueindex != -1) {
      for (Iterator<Node> it = blueList.iterator(); it.hasNext();) {
        now = it.next();
        // 현재 터트린 위치보다 위에 있는 블록들
        if (now.y < blueindex) {
          for (int i = 0; i < dx[now.t - 1].length; i++) {
            int nx = now.x + dx[now.t - 1][i];
            int ny = now.y + dy[now.t - 1][i];

            bluemap[nx][ny] = 0;
          }
          Node temp = monominoPush(now.x, now.y, now.t, 0);
          now.x = temp.x;
          now.y = temp.y;
          now.t = temp.t;
        }
      }
    }
    if (redindex != -1) {
      for (Iterator<Node> it = redList.iterator(); it.hasNext();) {
        now = it.next();
        if (now.x < redindex) {

          for (int i = 0; i < dx[now.t - 1].length; i++) {
            int nx = now.x + dx[now.t - 1][i];
            int ny = now.y + dy[now.t - 1][i];

            redmap[nx][ny] = 0;
          }
          // 모노미노 남은거 템프로 만들어서 푸시
          Node temp = monominoPush(now.x, now.y, now.t, 1);
          now.x = temp.x;
          now.y = temp.y;
          now.t = temp.t;
        }
      }

    }

    return endflag;
  }

  // state가 0이면 블루 1이면 레드
  public static Node monominoPush(int x, int y, int t, int state) {
    Node now = null;
    // 모노미노 푸시 수정해서 남은것들 보낼수 있게 하기

    if (state == 0) {

      if (t == 1) {
        for (int i = y; i <= 5; i++) {

          // 앞에 블럭이 있거나 벽에 도달했으면 블럭 설치
          if (i + 1 == 6 || bluemap[x][i + 1] == 1) {
            bluemap[x][i] = 1;
            now = new Node(x, i, 1);
            break;
          }

        }
      } else if (t == 2) {
        for (int i = y; i <= 5; i++) {

          if (i + 1 == 6 || bluemap[x][i + 1] == 1) {
            bluemap[x][i] = 1;
            bluemap[x][i - 1] = 1;
            now = new Node(x, i - 1, 2);
            break;
          }
        }
      } else {
        for (int i = y; i <= 5; i++) {

          if (i + 1 == 6 || bluemap[x][i + 1] == 1 || bluemap[x + 1][i + 1] == 1) {
            bluemap[x][i] = 1;
            bluemap[x + 1][i] = 1;
            now = new Node(x, i, 3);
            break;
          }
        }
      }
    } else {

      if (t == 1) {
        for (int i = x; i <= 5; i++) {

          if (i + 1 == 6 || redmap[i + 1][y] == 1) {
            redmap[i][y] = 1;
            now = new Node(i, y, 1);
            break;
          }
        }

      } else if (t == 2) {
        for (int i = x; i <= 5; i++) {
          if (i + 1 == 6 || redmap[i + 1][y] == 1 || redmap[i + 1][y + 1] == 1) {
            redmap[i][y] = 1;
            redmap[i][y + 1] = 1;
            now = new Node(i, y, 2);
            break;
          }
        }
      } else {
        for (int i = x; i <= 5; i++) {

          if (i + 1 == 6 || redmap[i + 1][y] == 1) {
            redmap[i][y] = 1;
            redmap[i - 1][y] = 1;
            now = new Node(i - 1, y, 3);
            break;
          }
        }
      }
    }
    return now;
  }

  public static void monominodomino() {
    while (!q.isEmpty()) {
      Node now = q.poll();
      // T만큼 보내기
      blueList.add(monominoPush(now.x, 1, now.t, 0));
      redList.add(monominoPush(1, now.y, now.t, 1));

      // 점수획득 탐색 및 제거
      while (explore()) {

      }

      // 연한 타일에 있으면 끝 부분제거하고 땡기기
      push();

      // Print(now);
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    bluemap = new int[4][6];
    redmap = new int[6][4];
    redList = new ArrayList<>();
    blueList = new ArrayList<>();

    q = new ArrayDeque<>();

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int t = Integer.parseInt(st.nextToken());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());

      q.add(new Node(x, y, t));
    }

    monominodomino();

    // 타일 횟수
    int cnt = 0;
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 4; j++) {
        if (bluemap[j][i] == 1)
          cnt++;
        if (redmap[i][j] == 1)
          cnt++;

      }
    }
    System.out.println(score);
    System.out.println(cnt);

  }

  static class Node {
    int x, y, t;

    public Node(int x, int y, int t) {
      this.x = x;
      this.y = y;
      this.t = t;
    }

    @Override
    public String toString() {
      return "Node [x=" + x + ", y=" + y + ", t=" + t + "]";
    }

  }
}

// https://www.acmicpc.net/problem/19235
