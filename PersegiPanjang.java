public class PersegiPanjang extends Matematika {
    private double panjang;
    private double lebar;

    public PersegiPanjang(double panjang, double lebar) {
        this.panjang = panjang;
        this.lebar = lebar;
    }

    @Override
    public void luas() {
        double luasPersegiPanjang = panjang * lebar;
        System.out.println("Luas Persegi Panjang: " + luasPersegiPanjang);
    }
}
