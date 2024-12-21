import java.util.Scanner;

public class Test {
    private static String history;
    public static void main(String[] args){
        Scanner s=new Scanner(System.in);
        System.out.println("Please select the nuumber of players:");
        int p=s.nextInt();
        while(p<1 || p>5){
            p=s.nextInt();
        }
        System.out.println("Please select the height of the board:");
        int m=s.nextInt();
        while(m<0){
            m=s.nextInt();
        }
        System.out.println("Please select the length of the board:");
        int n=s.nextInt();
        while(n<0){
            n=s.nextInt();
        }
        System.out.println("Please select the goal state:");
        int g=s.nextInt();
        while(g<0){
            g=s.nextInt();
        }
        ConnectFour game=new ConnectFour(m,n,g,p);
        AI[] players=new AI[p];
        int[] depths=new int[p];
        int d;
        for(int i=0;i<p;i++){
            System.out.println("Please select Player"+(i+1)+":");
            String str=s.nextLine();
            while(!str.equals("Human") && !str.equals("AlphaBeta") && !str.equals("MonteCarlo") && !str.equals("HillClimbing")){
                str=s.nextLine();
            }
            if(str.equals("Human")){
                players[i]=null;
            }else if(str.equals("AlphaBeta")){
                players[i]=new AlphaBeta();
                System.out.println("Please select the depth of your algorithm:");
                d=s.nextInt();
                while(d<0){
                    System.out.println("You are an idiot, select a valid depth:");
                    d=s.nextInt();
                }
                depths[i]=d;
            }else if(str.equals("MonteCarlo")){
                players[i]=new MonteCarlo();
                System.out.println("Please select the depth of your algorithm:");
                d=s.nextInt();
                while(d<0){
                    System.out.println("You are an idiot, select a valid depth:");
                    d=s.nextInt();
                }
                depths[i]=d;
            }else if(str.equals("HillClimbing")){
                players[i]=new HillClimbing();
                System.out.println("Please select the depth of your algorithm:");
                d=s.nextInt();
                while(d<0){
                    System.out.println("You are an idiot, select a valid depth:");
                    d=s.nextInt();
                }
                depths[i]=d;
            }
        }
        play(game, players, depths);
    }   

    private static void play(ConnectFour game, AI[] players, int[] depths){
        System.out.println("Game Started!");
        Scanner s=new Scanner(System.in);
        int count=1;
        boolean play=true;
        while(play){
            while(!game.finished() && !game.won()){
                game.print();
                System.out.println("Player"+game.player()+"'s turn:");
                if(players[game.player()-1]==null){
                    System.out.println("Please select the column you want to play:");
                    int i=s.nextInt();
                    while(!game.isLegal(i)){
                        System.out.println("You are an idiot, please select a normal column:");
                        i=s.nextInt();
                    }
                    try{
                        game.play(i);
                    }
                    catch(Exception e){}
                }else{
                    try{
                        game.play(players[game.player()-1].play(game, depths[game.player()-1]));
                    }
                    catch(Exception e){}
                }
            }
            history+="Game "+count+": ";
            for(int i=0;i<players.length;i++){
                if(players[i]!=null){
                    history+=players[i];
                }
                else{
                    history+="Human";
                }
                if(i!=players.length-1){
                    history+=" against ";
                }
            }
            history+=": ";
            if(game.won()){
                history+="Player"+game.winner()+" won.";
            }
            else{
                history+="It was a tie.";
            }
            history+="\n";
            System.out.println("Play again? (press 'y' for yes and 'n' for no)");
            String str=s.nextLine();
            while(!str.equals("y") && !str.equals("n")){
                System.out.println("Are you an idiot? Choose normally!");
                str=s.nextLine();
            }
            play=str.equals("y");
        }
    }    
}
