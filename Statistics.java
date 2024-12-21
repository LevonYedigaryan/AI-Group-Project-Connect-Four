import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class Statistics {
    private static int[] ab={1, 2, 3, 4, 5, 6, 7};
    private static int[] hc={1, 2, 3, 4, 5, 6};
    private static int[] mc={10, 100, 1000, 10000};
    private static int[] sp={0};
    private static AI[] ai={new AlphaBeta(), new MonteCarlo(), new HillClimbing(), new StupidPlayer()};
    public static void main(String[] args){
        int[] depths;
        File file = new File("Statistics.txt");
        try{
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            boolean cont=false;
            bufferedWriter.write("PLAYERS1\n\n");
            for(int i=0;i<ai.length;i++){
                for(int j=0;j<ai.length;j++){
                    int[] depths1;
                    int[] depths2;
                    int random=1;
                    int simulations=1000;
                    switch(ai[i].toString()){
                        case "MonteCarlo":
                            depths1=mc;
                            random=simulations;
                            break;
                        case "AlphaBeta":
                            depths1=ab;
                            break;
                        case "HillClimbing":
                            depths1=hc;
                            break;
                        default:
                            depths1=sp;
                            random=simulations;
                            break;
                    }
                    switch(ai[j].toString()){
                        case "MonteCarlo":
                            depths2=mc;
                            random=simulations;
                            break;
                        case "AlphaBeta":
                            depths2=ab;
                            break;
                        case "HillClimbing":
                            depths2=hc;
                            break;
                        default:
                            depths2=sp;
                            random=simulations;
                            break;
                    }
                    int m1, m2;
                    for(int d1=0;d1<depths1.length;d1++){
                        for(int d2=0;d2<depths2.length;d2++){
                            if(depths2[d2]==2){
                                System.out.println("Panir");
                            }
                            int a1=0, a2=0;
                            double time=0;
                            boolean fail=false;
                            for(int c=0;c<random;c++){
                                System.out.println(c);
                                double start = System.currentTimeMillis();
                                ConnectFour game=new ConnectFour();
                                while(!game.won() && !game.finished()){
                                    if(game.paytyunavtang()){
                                        System.out.println("Any questions? Any Concerns?");
                                    }
                                    m1=ai[i].play(game, depths1[d1]);
                                    game.play(m1);
                                    if(!game.finished()){
                                        m2=ai[j].play(game, depths2[d2]);
                                        game.play(m2);
                                    }
                                }
                                if(game.won()){
                                    switch(game.winner()){
                                        case 1:
                                            a1++;
                                            break;
                                        case 2:
                                            a2++;
                                            break;
                                        default:
                                    }
                                }
                                double end = System.currentTimeMillis();
                                time+=end-start;
                                if(time/(c+1)>1000.00){
                                    d2++;
                                    fail=true;
                                    break;
                                }
                            }
                            double meanTime=time/random;
                            if(!fail){
                                System.out.println("Simulation: (P1)"+ai[i]+" depth "+depths1[d1]+" vs (P2)"+ai[j]+" depth "+depths2[d2]+"\n\t Simulations: "+random+"\n"+"\t\t\tWins:\n"+"\t\t\t\t"+ai[i].toString()+" depth "+depths1[d1]+": "+a1+"\n\t\t\t\t"+ai[j].toString()+" depth "+depths2[d2]+": "+a2+"\n"+"\n\t\t\t\t"+"Ties:"+(random-a1-a2)+"\n"+"\n\t\t\t\t"+"Mean duration of the game:"+meanTime+"\n");
                                bufferedWriter.write("Simulation: (P1)"+ai[i]+" depth "+depths1[d1]+" vs (P2)"+ai[j]+" depth "+depths2[d2]+"\n\t Simulations: "+random+"\n"+"\t\t\tWins:\n"+"\t\t\t\t"+ai[i].toString()+" depth "+depths1[d1]+": "+a1+"\n\t\t\t\t"+ai[j].toString()+" depth "+depths2[d2]+": "+a2+"\n"+"\n\t\t\t\t"+"Ties:"+(random-a1-a2)+"\n"+"\n\t\t\t\t"+"Mean duration of the game:"+meanTime+"\n");
                            }
                            if((a1==random || a2==random) && random!=1){
                                System.out.println("Dzmeruk");
                                if(a2==random){
                                    d2=depths2.length;
                                }
                            }
                            System.out.println("Llama");
                            a1=0;
                            a2=0;
                        }
                    }
                }
            }
        }
        catch(Exception e){
            System.out.println("Once again, hi everyone, welcome back!");
        }
    }
}
/*
bufferedWriter.write("PLAYERS1\n\n");
for(int i=0;i<ai.length;i++){
    for(int j=0;j<ai.length;j++){
        for(int k=0;k<ai.length;k++){
            int[] depths1;
            int[] depths2;
            int[] depths3;
            int random=1;
            int simulations=1000;
            switch(ai[i].toString()){
                case "MonteCarlo":
                    depths1=mc;
                    random=simulations;
                    break;
                case "AlphaBeta":
                    depths1=ab;
                    break;
                case "HillClimbing":
                    depths1=hc;
                    break;
                default:
                    depths1=sp;
                    random=simulations;
                    break;
            }
            switch(ai[k].toString()){
                case "MonteCarlo":
                    depths3=mc;
                    random=simulations;
                    break;
                case "AlphaBeta":
                    depths3=ab;
                    break;
                case "HillClimbing":
                    depths3=hc;
                    break;
                default:
                    depths3=sp;
                    random=simulations;
                    break;
            }
            switch(ai[j].toString()){
                case "MonteCarlo":
                    depths2=mc;
                    random=simulations;
                    break;
                case "AlphaBeta":
                    depths2=ab;
                    break;
                case "HillClimbing":
                    depths2=hc;
                    break;
                default:
                    depths2=sp;
                    random=simulations;
                    break;
            }
            int a1=0;
            int a2=0;
            int a3=0;
            for(int d1=0;d1<depths1.length;d1++){
                for(int d2=0;d2<depths2.length;d2++){
                    for(int d3=0;d3<depths3.length;d3++){
                        for(int c=0;c<random;c++){
                            ConnectFour game=new ConnectFour();
                            while(!game.won() && !game.finished()){
                                game.play(ai[i].play(game, depths1[d1]));
                                game.play(ai[j].play(game, depths2[d2]));
                                game.play(ai[k].play(game, depths3[d3]));
                            }
                            if(game.won()){
                                String winner="";
                                switch(game.winner()){
                                    case 1:
                                        a1++;
                                        break;
                                    case 2:
                                        a2++;
                                        break;
                                    case 3:
                                        a3++;
                                        break;
                                    default:
                                }
                            }
                            bufferedWriter.write("GAME1: ");
                        }
                        bufferedWriter.write("GAME1: (P1)"+ai[i]+"depth "+depths1[d1]+" vs (P2)"+ai[j]+"depth "+depths2[d2]+"\n\t Simulations: "+random+"\n"+"\t\t\tWins:\n"+"\t\t\t\t"+ai[i].toString()+" depth "+depths1[d1]+": "+a1+"\n\t\t\t\t"+ai[j].toString()+" depth "+depths2[d2]+": "+a2+"\n"+"\n\t\t\t\t"+"Ties:"+(random-a1-a2)+"\n");
                    }
                }
            }
        }
    }
}
*/