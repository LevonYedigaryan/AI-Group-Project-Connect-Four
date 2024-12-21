public class ConnectFour{
    private int board[][];
    private int connect;
    private int  turn;
    private boolean won;
    private int winner;
    private int number;
    private boolean finished;
    
    public ConnectFour(){
        this(6,7,4, 2);
    }

    public ConnectFour(int n, int m, int connect, int number){
        this.board=new int[n][m];
        this.connect=connect;
        this.number=number;
    }

    public int goal(){
        return connect;
    }

    int get(int i, int j){
        return board[i][j];
    }

    public int players(){
        return number;
    }

    public int next(){
        int t=turn+1;
        return t%number+1;
    }

    public boolean isLegal(int i){
        return board[0][i]==0;
    }

    public int columns(){
        return board[0].length;
    }

    public int rows(){
        return board.length;
    }

    public boolean won(){
        return won;
    }

    public int winner(){
        return winner;
    }

    public boolean finished(){
        return finished;
    }

    public void play(int i) throws IllegalMoveException{
        if(won){
            return;
        }
        if(board[0][i]!=0){
            throw new IllegalMoveException();
        }
        int j=0;
        for(j=board.length-1;j>=0;j--){
            if(board[j][i]==0){
                board[j][i]=turn+1;
                if(legalMoves().length<=0){
                    finished=true;
                }
                break;
            }
        }
        int k=1;
        int m=j, n=i;
        while(m>=0 && m<board.length && n>=0 && n+1<board[0].length && board[m][n]==board[m][++n]){
            k++;
        }
        m=j;
        n=i;
        while(m>=0 && m<board.length && n-1>=0 && n<board[0].length && board[m][n]==board[m][--n]){
            k++;
        }
        if(k>=connect){
            win();
        }
        k=1;
        m=j;
        n=i;
        while(m>=0 && m+1<board.length && n>=0 && n<board[0].length && board[m][n]==board[++m][n]){
            k++;
        }
        m=j;
        n=i;
        while(m-1>=0 && m<board.length && n>=0 && n<board[0].length && board[m][n]==board[--m][n]){
            k++;
        }
        if(k>=connect){
            win();
        }
        k=1;
        m=j;
        n=i;
        while(m>=0 && m+1<board.length && n>=0 && n+1<board[0].length && board[m][n]==board[++m][++n]){
            k++;
        }
        m=j;
        n=i;
        while(m-1>=0 && m<board.length && n-1>=0 && n<board[0].length && board[m][n]==board[--m][--n]){
            k++;
        }
        if(k>=connect){
            win();
        }
        k=1;
        m=j;
        n=i;
        while(m>=0 && m+1<board.length && n-1>=0 && n<board[0].length && board[m][n]==board[++m][--n]){
            k++;
        }
        m=j;
        n=i;
        while(m-1>=0 && m<board.length && n>=0 && n+1<board[0].length && board[m][n]==board[--m][++n]){
            k++;
        }
        if(k>=connect){
            win();
        }
        turn++;
        turn%=number;
    }

    private void win(){
        won=true;
        finished=true;
        winner=turn+1;
    }

    public int[] legalMoves(){
        int[] moves=new int[columns()];
        int j=0;
        for(int i=0;i<columns();i++){
            if(isLegal(i)){
                System.out.println("Dzmer Pap");
                moves[j++]=i;
            }
        }
        int[] legalmoves=new int[j];
        for(int i=0;i<j;i++){
            legalmoves[i]=moves[i];
        }
        return legalmoves;
    }

    private void set(int i, int j, int value){
        board[i][j]=value;
    }

    public boolean paytyunavtang(){
        boolean k=false;
        for(int i=0;i<columns()-1;i++){
            if(isLegal(i)){
                k=true;
            }
        }
        if(!k){
            if(board[2][6]!=0 && board[1][6]==0){
                return true;
            }
        }
        return false;
    }

    public void print(){
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                if(board[i][j]==0)
                    System.out.print(" |");
                else{
                    System.out.print(board[i][j]+"|");
                }
            }
            System.out.println();
        }
        for(int i=0;i<board[0].length;i++){
            System.out.print("--");
        }
        System.out.println();
        for(int i=0;i<board[0].length;i++){
            System.out.print(i+" ");
        }
        System.out.println();
    }

    public int player(){
        return turn%number+1;
    }

    public int[][] board(){
        int[][] b=new int[board.length][board[0].length];
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                b[i][j]=board[i][j];
            }
        }
        return b;
    }

    public ConnectFour clone(){
        ConnectFour game=new ConnectFour(rows(), columns(), connect, number);
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                game.set(i,j,board[i][j]);
            }
        }
        game.turn=this.turn;
        game.won=this.won;
        game.winner=this.winner;
        game.finished=this.finished;
        game.connect=this.connect;
        game.number=this.number;
        return game;
    }

    public void reset(){
        board=new int[board.length][board[0].length];
        turn=0;
        finished=false;
        won=false;
        winner=0;
    }
}