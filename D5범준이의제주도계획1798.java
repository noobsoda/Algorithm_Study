import java.util.*;
import java.io.*;

@SuppressWarnings("unchecked")

class D5범준이의제주도계획1798
{
    static int N, M, start, max;
    static ArrayList<Node> hotellist;
    static ArrayList<Node> tourlist;
    static ArrayList<Integer> [] arr;
    static List<Integer> list;
    static Deque<Integer> dq;
    static boolean visited[];
    
    public static void tourlist(int day, int spendtime, int cur, int satis){
        boolean flag = false;
                
        for(Node now : tourlist){
            if(visited[now.n]) continue;
            
            int nowspend = spendtime + now.p + arr[cur].get(now.n);
            if(day < M-1 &&nowspend <= 540){
                flag = true;
                visited[now.n] = true;             
                dq.addLast(now.n+1);
                tourlist(day, nowspend, now.n, satis + now.s);
                dq.removeLast();
                visited[now.n] = false;                
            } 
            else if(day == M-1 && nowspend + arr[now.n].get(start) <= 540){
                flag = true;
                visited[now.n] = true;       
                dq.addLast(now.n+1);
                tourlist(day, nowspend, now.n, satis + now.s);
                dq.removeLast();
                visited[now.n] = false;   
            }
            
        }
        if(!flag){
        	if(day == M-1){
                if(max < satis){   
                    max = satis;
            	    list.clear();
                	list.addAll(dq);
                    list.add(start+1);                    
	            }        
        	}	
        	else{
        		for(Node hotel : hotellist){            
            		if(spendtime + arr[cur].get(hotel.n) <= 540){
                        dq.addLast(hotel.n+1);
                		tourlist(day+1, 0, hotel.n, satis);
                        dq.removeLast();
            		}            
        		}
        	}
        }
        
    }
	public static void main(String args[]) throws Exception
	{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T=Integer.parseInt(st.nextToken());
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
            max = 0;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());      
            list = new LinkedList<>();
            dq = new LinkedList<>();
            
            visited = new boolean[N];
            arr = new ArrayList[N];
            hotellist = new ArrayList<>();
            tourlist = new ArrayList<>();
            
            for(int i = 0; i < N; i++){
                arr[i] = new ArrayList<>();           
            }
            
            for(int i = 0; i < N-1; i++){
                int count = i+1;
                st = new StringTokenizer(br.readLine());
                arr[i].add(0);
                
                while(st.hasMoreTokens()){
                    int n = Integer.parseInt(st.nextToken());                    
                    arr[i].add(n);
                    arr[count].add(n);
                    
                    count++;
                }                
            }
            arr[N-1].add(0);
            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                
                char c = st.nextToken().charAt(0);
                int p = 0;
                int s = 0;                
               
                if(c == 'P'){
                    p = Integer.parseInt(st.nextToken());
                    s = Integer.parseInt(st.nextToken());
                    
                    tourlist.add(new Node(p, s, i));
                }
                else if(c == 'H'){
                	hotellist.add(new Node(i));
                }
                else
                    start = i;
            }         
            
            tourlist(0, 0, start, 0);
                      
            bw.write("#"+test_case+" "+max+" ");
            for(int i=0;i<list.size();i++) {
                bw.write(list.get(i)+" ");
            }
            bw.newLine();
            
		}
        bw.close();
	}
    static class Node{        
        int  p, s, n;
        public Node(int n){
         	this.n = n;   
        }
        public Node( int p, int s, int n){            
            this.p = p;
            this.s = s;
            this.n = n;
            
        }
    }
}