public class Main {
    public static void main(String[] args) {
        Matematika matematika1 = new Segitiga(5, 8);
        Matematika matematika2 = new PersegiPanjang(4, 6);

        // Polimorfisme
        matematika1.luas(); // Memanggil luas() dari Segitiga
        matematika2.luas(); // Memanggil luas() dari PersegiPanjang
    }
}
