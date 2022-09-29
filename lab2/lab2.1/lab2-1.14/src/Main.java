public class Main {
    public static void m(int x) throws ArithmeticException{
        int h = 10 / x;
    }
    public static void main(String[] args) {
        try{
            int l = args.length;
            System.out.println("Розмір масиву=" + l);
            m(l);
        } catch (ArithmeticException e) {
            System.out.println("Помилка: Ділення на нуль");
        }
    }
}