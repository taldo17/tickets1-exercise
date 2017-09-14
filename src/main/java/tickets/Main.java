package tickets;

import java.util.Scanner;

/**
 * Created by taldo on 22/08/2017.
 */
public class Main {
    public static void main(String[] args) {
        try{
            Scanner reader = new Scanner(System.in);
            System.out.println("Welcome to the ticket Master!");
            System.out.println("Please choose the name of the show: ");
            String showName = reader.nextLine();
            System.out.println("Please choose the desired date in the following format dd/mm/yyyy: ");
            String desiredDate = reader.nextLine();
            System.out.println("Number of tickets: ");
            int numberOfTickets = reader.nextInt();

            TicketService ticketService = new TicketService();
            String report = ticketService.placeOrder(showName, desiredDate, numberOfTickets);
            System.out.println(report);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
