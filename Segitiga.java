public class Segitiga extends Matematika {
    private double alas;
    private double tinggi;

    public Segitiga(double alas, double tinggi) {
        this.alas = alas;
        this.tinggi = tinggi;
    }

    @Override
    public void luas() {
        double luasSegitiga = 0.5 * alas * tinggi;
        System.out.println("Luas Segitiga: " + luasSegitiga);
    }
}
