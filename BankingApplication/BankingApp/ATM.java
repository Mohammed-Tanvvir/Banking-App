import java.util.Scanner;

public class ATM{
    private String balances;
    private String transfer;
    
    public ATM () {
        balances = "[BALANCES]";
        transfer = "[TRANSFER]";

    }
        public void print(){
        System.out.print(this.getClass().getSimpleName() + " - ");
        System.out.println(balances + " " + transfer);
        }
        
    }