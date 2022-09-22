import java.lang.reflect.Field;
import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in, "cp1251");
    public static Team[] teams; // declaring an array of Team objects
    public static void fillTeamInfo(int number){ // filling in information about the teams
        teams = new Team[number];
        for(int i = 0; i < number; i++){
            teams[i] = new Team();
            System.out.println("\nEnter the information of team " + (i + 1) + ": ");
            System.out.print("Name: ");
            teams[i].name = sc.nextLine();
            System.out.print("City: ");
            teams[i].city = sc.nextLine();
            teams[i].rating = getInt("Rating");
            teams[i].wins = getInt("Wins");
        }
    }
    public static void printTeamInfo(String s){ // printing entered information
        System.out.format("%n%s:%n", s);
        for (Team t : teams){
            System.out.format("Name: %6s,\tcity: %6s,\trating: %3d,\twins: %3d%n",
                    t.name, t.city, t.rating, t.wins);
        }
    }
    public static void printMostWinningTeam(){ // printing the team with the most wins
        int maxWins = 0;
        int winTeamIndex = 0;
        for (int i = 0; i < teams.length; i++){
            if (teams[i].wins > maxWins){
                winTeamIndex = i;
                maxWins = teams[i].wins;
            }
        }
        Team winner = teams[winTeamIndex];
        System.out.format("\nMost winning team:\t%s, wins: %s%n",
                winner.name, winner.wins);
    }
    public static void printBetterThanAverageTeams(){ // printing the teams with more wins than average
        int winSum = 0;
        for (Team team : teams){
            winSum += team.wins;
        }
        double averageWins = (double) winSum / teams.length;
        averageWins = Math.round(averageWins * 10) / 10.0;
        System.out.println("\nWin average: " + averageWins);
        System.out.println("Teams that have more wins than average:");
        for (Team team : teams){
            if (team.wins > averageWins){
                System.out.format("%s (%d wins)%n", team.name, team.wins);
            }
        }
    }
    public static void sortDescending(){ // sorting the array of teams in descending order
        for (int i = 0; i < teams.length - 1; i++){
            for (int j = 0; j < teams.length - 1 - i; j++){
                if (teams[j].wins < teams[j + 1].wins){
                    Team tmp = teams[j];
                    teams[j] = teams[j + 1];
                    teams[j + 1] = tmp;
                }
            }
        }
    }
    public static Team findTeam(){ // searching for a team by name
        while (true){
            System.out.print("\nFind team: ");
            String toFind = sc.nextLine();
            for (Team team : teams) {
                if (team.name.equalsIgnoreCase(toFind)){
                    return team;
                }
            }
            if (tryAgain("Team not found")){
                return null;
            }
        }
    }
    public static void printFoundTeam(Team foundTeam){ // print the found team
        System.out.println("\nTeam found: ");
        System.out.format("Name: %s,\tcity: %s,\trating: %d,\twins: %d%n",
                foundTeam.name, foundTeam.city, foundTeam.rating, foundTeam.wins);
    }
    public static void changeFieldAndPrint(Team foundTeam){ // change a field of the team
        Field field;
        String fieldToChange;
        while (true){
            System.out.print("\nEnter field to change: ");
            fieldToChange = sc.nextLine().trim();
            try{
                field = foundTeam.getClass().getField(fieldToChange);
                break;
            }catch(NoSuchFieldException e){
                if(tryAgain("There is no field '" + fieldToChange + "'")){
                    System.out.println("Exiting...");
                    return;
                }
            }
        }
        try{
            if (fieldToChange.equalsIgnoreCase("rating") ||
                    fieldToChange.equalsIgnoreCase("wins")){
                field.set(foundTeam, getInt("Enter new value"));
            }else{
                System.out.print("Enter new value: ");
                field.set(foundTeam, sc.nextLine());
            }
        }catch(IllegalAccessException e){
            System.out.println("Can't access the field. Exiting...");
            return;
        }
        System.out.println("\nField changed successfully:");
        System.out.format("Name: %s,\tcity: %s,\trating: %d,\twins: %d%n",
                foundTeam.name, foundTeam.city, foundTeam.rating, foundTeam.wins);
        System.out.println("Exiting...");
    }
    
    public static void main(String[] args){
        fillTeamInfo(getInt("Enter the number of teams"));
        printTeamInfo("Stored team information");
        printMostWinningTeam();
        printBetterThanAverageTeams();
        sortDescending();
        printTeamInfo("Sorted by wins");
        Team foundTeam = findTeam();
        if (foundTeam != null){
            printFoundTeam(foundTeam);
            changeFieldAndPrint(foundTeam);
        }else{
            System.out.println("Exiting...");
        }
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
    public static boolean tryAgain(String s){
        System.out.print(s + ". Try again? [Y/n]: ");
        return sc.nextLine().equalsIgnoreCase("n");
    }
}
