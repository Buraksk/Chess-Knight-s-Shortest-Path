import java.util.LinkedList;
import java.util.Queue;

/**
 * @author burakisik
 */

public class Knight{
    final static int N = 8; //length of row and column
    int mx[] = { 2, 1, -1, -2, -2, -1, 1, 2 };
    int my[] = { 1, 2, 2, 1, -1, -2, -2, -1 }; // x and y axis movements
    public int [][] area = new int[N][N]; //chessboard

    private class Move //instant knight's action
    {
        public int x,y;
        public int distance;

        public Move(int x,int y,int distance){
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }

    public void fillArea(){
        int iter = 0;
        for(int i=0;i < N;i++){
            for (int j = 0; j < N; j++){
                area[i][j] = iter;
                iter++;
            }
        }
    }

    public int [] findCoordinate(int square){
        int cord [] = new int[2]; //storing src and dest coordinate
        int counter=0;
        int i=0;
        int j=0;
        for(;i < N;i++){
            j=0;
            for (; j < N; j++) {
                if(square == area[i][j]){
                    counter++;
                    break;
                }
            }
            if (counter==1){
                break;
            }
        }
        cord[0] = i;
        cord[1] = j;
        return cord;
    }

    boolean isValidAction(int x, int y)
    {
        if (x >= 0 && y >= 0 && x < N  && y < N)
            return true;
        return false;
    }

    int answer(int src, int dest)
    {
        int startCord[] = findCoordinate(src);
        int targetCord[] = findCoordinate(dest);

        Queue<Move> q = new LinkedList<Move>();

        q.add(new Move(startCord[0],startCord[1],0));

        Move m;
        int x,y;
        boolean visited[][] = new boolean[N][N]; // it is for avoiding loop

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                visited[i][j] = false;

        //source position is visited at the beginning
        visited[startCord[0]][startCord[1]] = true;

        while (!q.isEmpty()) {
            m = q.poll();

            if (m.x == targetCord[0] && m.y == targetCord[1])
                return m.distance;

            for (int i = 0; i < 8; i++)
            {
                x = m.x + mx[i];
                y = m.y + my[i];

                //find possible moves then push it in queue
                if (isValidAction(x, y) && !visited[x][y]) {
                    visited[x][y] = true;
                    q.add( new Move(x,y,m.distance+1));
                }
            }
        }
        return 0;
    }

    public static void main(String [] args){
        Knight knight = new Knight();
        knight.fillArea();

        //System.out.println("Commander Lambda has installed new flooring in "+knight.answer(0, 1)+" step");
        //System.out.println("Commander Lambda has installed new flooring in "+knight.answer(19, 36)+" step");
        //geting paramater via comment line
        System.out.println("Commander Lambda has installed new flooring in "+knight.answer(Integer.parseInt(args[0]), Integer.parseInt(args[1]))+" step");
    }

}
