import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Home {
    public Shapes shape = new Shapes();
    Random rand = new Random();
    public boolean reveal = false;
    public boolean bust = false;
    public boolean stand = false;
    public boolean win = false;
    public List<Cards> availCards = new ArrayList<Cards>();
    public List<Cards> playerCards = new ArrayList<Cards>();
    public List<Cards> dealerCards = new ArrayList<Cards>();
    public int pTotal;
    public int dTotal;
    public String endText;

    public Home(){
        int count = 1;
        int suiteTrack = 0;
        Cards temp = new Cards();
        for (int i = 0; i < 52; i++) {
            switch (suiteTrack) {
                case 0:
                    temp = new Cards(count,"spade");
                    break;
                case 1:
                    temp = new Cards(count,"heart");
                    break;
                case 2:
                    temp = new Cards(count,"club");
                    break;
                case 3:
                    temp = new Cards(count,"diamond");
                    break;
                default:
                    temp = new Cards(count,"spade");
                    break;
            }
            availCards.add(temp);
            count++;
            if (count > 13){
                count = 1;
                suiteTrack++;
            }
        }
    }
    
    public void SceneHome(Graphics2D g2d){
        shape.text(g2d, "Gambling!!!", 232, 128, Color.white, 64);
        //button (232,256)-(568,304)
        shape.rect(g2d, 232, 256, 336, 48, Color.darkGray);
        shape.text(g2d, "Black Jack", 320, 292, Color.white, 32);
    }
    public void SceneBlackJack(Graphics2D g2d){
        //cards
        if(!playerCards.isEmpty()){
            int rows = 0;
            int columns = 0;
            for(int i = 2;i < playerCards.size();i++){
                shape.card(g2d, 640+(48*(columns)), 204+(80*rows), 32, String.valueOf(playerCards.get(i).num), playerCards.get(i).suite);
                columns++;
                if((i-1)%3 ==0){
                    rows++;
                    columns = 0;
                }
            }
        }
        //draw
        else{
            int randCard = rand.nextInt(availCards.size());
            Cards pcard1 = availCards.get(randCard);
            playerCards.add(pcard1);
            availCards.remove(randCard);
            randCard = rand.nextInt(availCards.size());
            Cards dcard1 = availCards.get(randCard);
            dealerCards.add(dcard1);
            availCards.remove(randCard);
            randCard = rand.nextInt(availCards.size());
            Cards pcard2 = availCards.get(randCard);
            playerCards.add(pcard2);
            availCards.remove(randCard);
            randCard = rand.nextInt(availCards.size());
            Cards dcard2 = availCards.get(randCard);
            dealerCards.add(dcard2);
            availCards.remove(randCard);
        }
        shape.card(g2d, 300, 300, 128, String.valueOf(playerCards.get(0).num), playerCards.get(0).suite);
        shape.card(g2d, 500, 300, 128, String.valueOf(playerCards.get(1).num), playerCards.get(1).suite);
        shape.card(g2d, 64, 64, 32, String.valueOf(dealerCards.get(0).num), dealerCards.get(0).suite);
        shape.card(g2d, 128, 64, 32, String.valueOf(dealerCards.get(1).num), dealerCards.get(1).suite);
        if (!reveal){
            shape.card(g2d, 128, 64, 32, "", "");
        }
        else{
            for(int  i = 2; i < dealerCards.size(); i++){
                shape.card(g2d, 192+(64*(i-2)), 64, 32, String.valueOf(dealerCards.get(i).num), dealerCards.get(i).suite);
            }
        }
        //buttons
        if(!stand && !bust && !win){
            shape.rect(g2d, 232, 450, 158, 48, Color.darkGray);
            shape.text(g2d, "Hit", 290, 482, Color.white, 32);
            shape.rect(g2d, 432, 450, 158, 48, Color.darkGray);
            shape.text(g2d, "Stand", 470, 482, Color.white, 32);
        }
        else{
            shape.rect(g2d, 232, 450, 158, 48, Color.darkGray);
            shape.text(g2d, "Try Again", 245, 482, Color.white, 32);
            shape.rect(g2d, 432, 450, 158, 48, Color.darkGray);
            shape.text(g2d, "Home", 470, 482, Color.white, 32);
            shape.text(g2d, endText, 275, 164, Color.white, 64);
        }
    }

    public void hit(){
        int randCard = rand.nextInt(availCards.size());
        Cards temp = availCards.get(randCard);
        playerCards.add(temp);
        availCards.remove(randCard);
        int total = 0;
        for (Cards c : playerCards) {
            if(c.num <= 10){
                total += c.num;
            }
            else{
                total += 10;
            }
        }
        if (total > 21){
            bust = true;
            endText = "You lose";
            reveal = true;
        }
        pTotal = total;
        if (total <= 11){
            for(Cards c: playerCards){
                if (c.num == 1){
                    pTotal += 10;
                    break;
                }
            }
        }
    }
    public void stand(){
        this.stand = true;
        this.reveal = true;
        int total = 0;
        for(Cards c: dealerCards){
            if(c.num <= 10){
                total += c.num;
            }
            else{
                total += 10;
            }
        }
        while(total < 17){
            int randCard = rand.nextInt(availCards.size());
            dealerCards.add(availCards.get(randCard));
            if(availCards.get(randCard).num <= 10){
                total += availCards.get(randCard).num;
            }
            else{
                total += 10;
            }
            availCards.remove(randCard);
        }
        dTotal = total;
        total = 0;
        for (Cards c : playerCards) {
            if(c.num <= 10){
                total += c.num;
            }
            else{
                total += 10;
            }
        }
        pTotal = total;
        if (total <= 11){
            for(Cards c: playerCards){
                if (c.num == 1){
                    pTotal += 10;
                    break;
                }
            }
        }
        if (pTotal > dTotal || dTotal > 21){
            win = true;
            endText = "You win!";
        }
        else if(pTotal == dTotal){
            endText = "You Tied";
        }
        else{
            endText = "You Lose";
        }
    }
}
