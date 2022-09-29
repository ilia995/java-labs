import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in); // сканер для введення з клавіатури
    public static double[] values; // масив для збереженя значень
    public static void createArrayInstance(int numbers){ // метод створення екземпляру масиву
        values = new double[numbers];
    }
    public static void fillArray(){ // метод заповнення масиву з клавіатури
        for(int i = 0; i < values.length; i++){
            values[i] = getDouble(i);
        }
    }
    public static void hasNegativeValues() throws NoNegativeValuesInArray { // метод перевірки на правильність масиву
        for(double val : values){
            if(val < 0) return; // якщо є хоча б один від'ємний елемент, виходимо з методу
        }
        throw new NoNegativeValuesInArray("Incorrect values"); // якщо від'ємні елементи відсутні, генеруємо виключення
    }
    public static boolean tryAgain(){ // запитуємо чи користувач бажає повторно ввести елементи, якщо введені невірно
        System.out.print("\nThere must be at least one negative value. Try again? [Y/n]: ");
        return !sc.nextLine().equalsIgnoreCase("n"); // повертаємо false у разі відмови, true у іншому випадку
    }
    public static boolean getArray(){ // заповнюємо масив з перевіркою на правильність введених значень
        while(true){
            fillArray(); // метод заповнення масиву з клавіатури
            try{
                hasNegativeValues(); // метод перевірки на правильність масиву
                return true; // якщо перевірка успішна, повертаємо true (отримали правильний масив)
            }catch (NoNegativeValuesInArray e){ // перехоплення винятку, якщо не отримали правильний масив
                if(!tryAgain()){ // пропонуємо ввести дані повторно
                    return false; // якщо користувач відмовився, повертаємо false (не отримали правильний масив)
                }
            }
        }
    }
    public static void printResult(){ // виведення результату
        double minVal = 0; // зміна мінімального значення
        int minInd = -1; // зміна індексу мінімального значення
        for(int i = 0; i < values.length; i++){ // знаходження найменшого значенні та його індексу
            if(values[i] < minVal){
                minVal = values[i];
                minInd = i;
            }
        }
        System.out.println("\nMinimum value: " + minVal);
        System.out.println("Index of the value: " + (minInd + 1));
    }
    public static int getInteger(){ // метод отримання цілого числа з клавіатури з перевіркою типу
        while(true){
            System.out.print("How many numbers: ");
            try{
                int newInt = Integer.parseInt(sc.nextLine()); // спроба перетворити значення в число
                return Math.abs(newInt); // якщо випадково введено негативне значення, змінюємо знак та повертаємо
            }catch (NumberFormatException e){ // перехоплення винятку у разі неправильного введеного значення
                System.out.print("Expecting a number. ");
            }
        }
    }
    public static double getDouble(int index){ // метод отримання десяткового числа з клавіатури з перевіркою типу
        while(true){
            System.out.format("Enter the value %d: ", (index + 1));
            try{
                return Double.parseDouble(sc.nextLine()); // спробо перетворити значення в число
            }catch (NumberFormatException e){ // перехоплення винятку у разі неправильного введеного значення
                System.out.print("Expecting a number. ");
            }
        }
    }
    public static void main(String[] args) {
        int sizeOfArray = getInteger(); // отримання розміру масиву з клавіатури з перевіркою типу
        createArrayInstance(sizeOfArray); // створення екземпляру масиву вказаного розміру
        boolean gotCorrectArray = getArray(); // отримання масиву (передає true у разі правильного заповнення масиву, false у разі відмови)
        if (gotCorrectArray) {
            printResult(); // якщо введено правильний масив, виводимо результат на екран
        }else{
            System.out.println("Refused to enter values again. Exiting."); // у разі відмови ввести правильний масив, завершуємо програму
        }
    }
}