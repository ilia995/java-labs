import java.lang.reflect.Field;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in, "cp1251");
    public static void main(String[] args){ // main method
        int number = getInt("Enter the number of teams"); // getting number of teams with type check
        if(number == 0){
            System.out.println("Exiting...");
            return;
        }
        Team[] team = new Team[number]; // instantiating an array of Employee objects
        for(int i = 0; i < number; i++){
            team[i] = new Team(); // instantiating each Team object
            System.out.println("\nEnter the information of team " + (i + 1) + ": ");
            System.out.print("Name: ");
            team[i].name = sc.nextLine();
            System.out.print("City: ");
            team[i].city = sc.nextLine();
            team[i].rating = getInt("Rating");
            team[i].wins = getInt("Wins");
        }
        System.out.println("\nTeam information:"); // printing entered information on the screen
        for (Team t : team){
            System.out.format("Name: %6s,\tcity: %6s,\trating: %2d,\twins: %2d%n",
                    t.name, t.city, t.rating, t.wins);
        }

        // determining the team with the most wins
        int maxWins = 0;
        int maxWinsIndex = 0;
        for (int i = 0; i < team.length; i++){
            if (team[i].wins > maxWins){
                maxWinsIndex = i;
                maxWins = team[i].wins;
            }
        }
        Team winTeam = team[maxWinsIndex]; // assigning the winner team to a new variable
        System.out.format("\nMost winning team: %s, wins: %s%n",
                winTeam.name, winTeam.wins); // printing the winner team

        // calculating the average win value
        int winSum = 0;
        for (Team t : team){
            winSum += t.wins;
        }
        double averageWins = (double) winSum / team.length;
        averageWins = Math.round(averageWins * 10) / 10.0;
        System.out.println("\nWin average: " + averageWins);

        // printing out teams with more wins than average
        System.out.println("Teams that have more wins than average:");
        for (Team t : team){
            if (t.wins > averageWins){
                System.out.format("%s (%d wins)%n", t.name, t.wins);
            }
        }

        // sorting the array of Team objects in descending order
        for (int i = 0; i < team.length - 1; i++){
            for (int j = 0; j < team.length - 1 - i; j++){
                if (team[j].wins < team[j + 1].wins){
                    Team tmp = team[j];
                    team[j] = team[j + 1];
                    team[j + 1] = tmp;
                }
            }
        }
        // printing sorted information
        System.out.println("\nSorted by wins:");
        for (Team t : team){
            System.out.format("%s -\t%d%n", t.name, t.wins);
        }

        // finding a team by its name
        System.out.print("\nFind team: ");
        String toFind = sc.nextLine();
        int toFindIndex = -1;
        for (int i = 0; i < team.length; i++){
            if (team[i].name.equalsIgnoreCase(toFind)){
                toFindIndex = i;
                break;
            }
        }
        if (toFindIndex == -1){ // if no team was found print message and exit
            System.out.println("No team found with this name. Exiting...");
            return;
        }
        // if a team is found let user change one of its fields
        Team t = team[toFindIndex]; // assigning the found team to a variable
        System.out.println("Team found: ");
        System.out.format("Name: %s,\tcity: %s,\trating: %d,\twins: %d%n",
                t.name, t.city, t.rating, t.wins);
        System.out.print("\nEnter field to change: "); // asking user to specify a field to change
        String fieldToChange = sc.nextLine();
        Field field; // declaring a variable of Field type
        try{
            field = t.getClass().getField(fieldToChange); // accessing the specified field
        }catch(NoSuchFieldException e){ // if there's no field of that name exit the program
            System.out.println("There is no field '" + fieldToChange + "'. Exiting...");
            return;
        }
        try{ // if the field is located ask for a new value for that field
            if (fieldToChange.equalsIgnoreCase("rating") ||
                    fieldToChange.equalsIgnoreCase("wins")){
                // if user chose the 'rating' or 'wins' fields set the new int value with type check
                field.set(t, getInt("Enter new value"));
            }else{
                System.out.print("Enter new value: ");
                field.set(t, sc.nextLine());
            }
        }catch(IllegalAccessException e){ // if there was an error changing the field print message and exit
            System.out.println("Can't access the field. Exiting...");
            return;
        }
        // print the changed team and exit
        System.out.println("Field changed successfully:");
        System.out.format("Name: %s,\tcity: %s,\trating: %d,\twins: %d%n",
                t.name, t.city, t.rating, t.wins);
        System.out.println("Exiting...");
    }
    public static int getInt(String s){
        while (true) {
            System.out.print(s + ": ");
            try {
                return Math.abs(Integer.parseInt(sc.nextLine()));
            } catch (NumberFormatException ignore) {
                System.out.print("Expecting a number. ");
            }
        }
    }
}
