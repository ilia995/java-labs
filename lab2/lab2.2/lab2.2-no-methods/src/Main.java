import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numbers;
        while(true){
            System.out.print("Enter the number of numbers: ");
            try{
                numbers = Integer.parseInt(sc.nextLine());
                break;
            }catch (NumberFormatException e){
                System.out.print("Expecting a number. ");
            }
        }
        double[] values = new double[numbers];
        while(true){
            for(int i = 0; i < values.length; i++){
                while(true){
                    System.out.format("Enter the value %d: ", i + 1);
                    try{
                        values[i] = Integer.parseInt(sc.nextLine());
                        break;
                    }catch (NumberFormatException e){
                        System.out.print("Expecting a number. ");
                    }
                }
            }
            boolean hasNegValues = false;
            for(double val : values){
                if(val < 0){
                    hasNegValues = true;
                    break;
                }
            }
            if(hasNegValues){
                break;
            }else{
                System.out.print("There must be at least one negative value. Try again? [Y/n]: ");
                if(sc.nextLine().equalsIgnoreCase("n")) {
                    System.out.println("Exiting.");
                    System.exit(0);
                }
            }
        }

        int minInd = -1;
        double minVal = 0;
        for(int i = 0; i < values.length; i++){
            if(values[i] < minVal){
                minVal = values[i];
                minInd = i;
            }
        }
        System.out.println("\nMinimum value: " + minVal);
        System.out.println("Index of the value: " + (minInd + 1));
        System.out.println("Exiting.");
    }
}