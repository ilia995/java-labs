public class Main {
    public static void main(String[] args) {
        try{
            System.out.println("0");
            throw new RuntimeException("Помилка");
        } catch (NullPointerException e) {
            System.out.println("1" );
        }
        System.out.println("2");
    }
}