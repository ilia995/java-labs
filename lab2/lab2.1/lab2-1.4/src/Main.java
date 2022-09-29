public class Main {
    public static void main(String[] args) {
        try{
            System.out.println("0");
            throw new RuntimeException("Помилка");
        } catch (NullPointerException e) {
            System.out.println("1 " + e);
        } catch (Exception e) {
            System.out.println("2 " + e);
        } catch (Error e) {
            System.out.println("3 " + e);
        }
        System.out.println("4");
    }
}