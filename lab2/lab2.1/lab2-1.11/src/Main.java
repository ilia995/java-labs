public class Main {
    public static void m() {
        try {
            System.out.println("0");
            throw new NullPointerException("Помилка");
        }catch (NullPointerException e) {
            System.out.println("1 " + e);
        } finally {
            System.out.println("2");
        }
        System.out.println("3");
    }
    public static void main(String[] args) {
        m();
    }
}