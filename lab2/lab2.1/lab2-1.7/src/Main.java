public class Main {
    public static void main(String[] args) {
        try{
            System.out.println("0");
            throw new NullPointerException("Помилка");
        } catch (NullPointerException e) {
            System.out.println("1 " + e);
            throw new ArithmeticException();
        } catch (ArithmeticException e) {
            System.out.println("2 " + e);
        }
        System.out.println("3");
    }
}