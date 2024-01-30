#include <iostream>
#include <queue>
using namespace std;
static int N, M, res, lastCheese;
static int map[101][101];
static bool visited[101][101];
static int dx[] = {-1, 0, 1, 0};
static int dy[] = {0, -1, 0, 1};
class Cheese
{
public:
    int x, y;
    Cheese(){};
    Cheese(int a, int b)
    {
        x = a;
        y = b;
    }
};

bool ExistCheese()
{
    lastCheese = 0;
    bool flag = true;
    for (int i = 0; i < N; i++)
        for (int j = 0; j < M; j++)
            if (map[i][j] == 1)
            {
                lastCheese++;
                flag = false;
            }

    return flag;
}

void Queue(int a, int b)
{
    if (map[a][b] == 1)
    {
        visited[a][b] = true;
        return;
    }

    queue<Cheese> q;
    q.push(Cheese(a, b));

    while (!q.empty())
    {
        Cheese node = q.front();
        q.pop();
        for (int k = 0; k < 4; k++)
        {
            int nx = node.x + dx[k];
            int ny = node.y + dy[k];
            if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny])
                continue;

            if (map[nx][ny] == 1)
            {
                visited[nx][ny] = true;
                continue;
            }
            q.push(Cheese(nx, ny));
            visited[nx][ny] = true;
        }
    }
}
void RemoveCheese()
{
    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < M; j++)
        {
            if (visited[i][j])
                map[i][j] = 0;
        }
    }
}

int main()
{
    int lastValue = 0;
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> N >> M;

    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < M; j++)
        {
            cin >> map[i][j];
        }
    }

    while (!ExistCheese())
    {
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < M; j++)
            {
                bool flag = false;
                for (int k = 0; k < 4; k++)
                {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                    {
                        flag = true;
                        break;
                    }
                }
                if (visited[i][j] || !flag)
                    continue;
                Queue(i, j);
            }
        }
        RemoveCheese();
        fill(&visited[0][0], &visited[101][101], false);
        lastValue = lastCheese;
        res++;
    }
    cout << res << "\n"
         << lastValue;
}