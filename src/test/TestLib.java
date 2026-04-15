package test;

public class TestLib {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✅ SUCCÈS ! Le JAR est bien chargé !");
        } catch (ClassNotFoundException e) {
            System.out.println("❌ ÉCHEC ! Le JAR n'est pas dans le classpath");
        }
    }
}