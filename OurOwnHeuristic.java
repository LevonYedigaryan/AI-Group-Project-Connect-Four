public class OurOwnHeuristic extends Heuristic{
    public int evaluate(ConnectFour game, int player) {
        int[][] board=game.board();
        int h=0;
        if(game.won()){
            if(game.winner()==player){
                h+=4*board.length*board[0].length;
            }
            else{
                return Integer.MIN_VALUE+1;
            }
        }
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                int k=0;
                while(board[i][j]==player && i>=0 && i<board.length && j>=0 && j+1<board[0].length && board[i][j]==board[i][++j]){
                    k++;
                }
                h+=k;
            }
        }
        for(int j=0;j<board[0].length;j++){
            for(int i=0;i<board.length;i++){
                int k=0;
                if(i==4){
                    //System.out.println("Monika");
                }
                while(board[i][j]==player && i>=0 && i+1<board.length && j>=0 && j<board[0].length && board[i][j]==board[++i][j]){
                    k++;
                }
                h+=k;
            }
        }
        for(int i=0;i<board.length;i++){
            int m=0,n=i;
            while(m>=0 && n>=0 && m<board[0].length && n<board.length){
                int k=0;
                while(board[n][m]==player && m>=0 && m+1<board[0].length && n>=0 && n+1<board.length && board[n][m]==board[++n][++m]){
                    k++;
                }
                m++;
                n++;
                h+=k;
            }
        }
        for(int j=1;j<board[0].length;j++){
            int m=j,n=0;
            while(m>=0 && n>=0 && m<board[0].length && n<board.length){
                int k=0;
                while(board[n][m]==player && m>=0 && m+1<board[0].length && n>=0 && n+1<board.length && board[n][m]==board[++n][++m]){
                    k++;
                }
                m++;
                n++;
                h+=k;
            }
        }
        for(int i=0;i<board.length;i++){
            int m=0,n=i;
            while(m>=0 && n>=0 && m<board[0].length && n<board.length){
                int k=0;
                while(board[n][m]==player && m>=0 && m+1<board[0].length && n-1>=0 && n<board.length && board[n][m]==board[--n][++m]){
                    k++;
                }
                m++;
                n--;
                h+=k;
            }
        }
        for(int j=1;j<board[0].length;j++){
            int m=j,n=board[0].length-1;
            while(m>=0 && n>=0 && m<board[0].length && n<board.length){
                int k=0;
                while(board[n][m]==player && m>=0 && m+1<board[0].length && n-1>=0 && n<board.length && board[n][m]==board[--n][++m]){
                    k++;
                }
                m++;
                n--;
                h+=k;
            }
        }
        return h;
    }
}
