import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

class Empat {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/users";
    private static final String USER = "uas";
    private static final String PASSWORD = "uas_ut_2023";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            createTableIfNotExists(connection);

            Scanner scanner = new Scanner(System.in);
            int choice;

            do {
                System.out.println("Menu:");
                System.out.println("1) Input Data");
                System.out.println("2) Tampil Data");
                System.out.println("3) Update Data");
                System.out.println("0) Keluar");
                System.out.print("Pilih menu: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        inputData(connection, scanner);
                        break;
                    case 2:
                        tampilData(connection);
                        break;
                    case 3:
                        updateData(connection, scanner);
                        break;
                    case 0:
                        System.out.println("Keluar dari program.");
                        break;
                    default:
                        System.out.println("Menu tidak valid.");
                        break;
                }
            } while (choice != 0);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTableIfNotExists(Connection connection) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS mahasiswa (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "nama VARCHAR(200)," +
                "alamat VARCHAR(200))";

        try (PreparedStatement preparedStatement = connection.prepareStatement(createTableSQL)) {
            preparedStatement.executeUpdate();
        }
    }

    private static void inputData(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Masukkan nama: ");
        String nama = scanner.next();

        System.out.print("Masukkan alamat: ");
        String alamat = scanner.next();

        String insertDataSQL = "INSERT INTO mahasiswa (nama, alamat) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertDataSQL)) {
            preparedStatement.setString(1, nama);
            preparedStatement.setString(2, alamat);
            preparedStatement.executeUpdate();
            System.out.println("Data berhasil dimasukkan.");
        }
    }

    private static void tampilData(Connection connection) throws SQLException {
        String selectDataSQL = "SELECT * FROM mahasiswa";

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectDataSQL);
                ResultSet resultSet = preparedStatement.executeQuery()) {

            System.out.println("Data Mahasiswa:");
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("Nama: " + resultSet.getString("nama"));
                System.out.println("Alamat: " + resultSet.getString("alamat"));
                System.out.println("------------------------");
            }
        }
    }

    private static void updateData(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Masukkan ID yang akan di-update: ");
        int idToUpdate = scanner.nextInt();

        String selectDataSQL = "SELECT * FROM mahasiswa WHERE id = ?";
        String updateDataSQL = "UPDATE mahasiswa SET nama = ?, alamat = ? WHERE id = ?";

        try (PreparedStatement selectStatement = connection.prepareStatement(selectDataSQL)) {
            selectStatement.setInt(1, idToUpdate);

            try (ResultSet resultSet = selectStatement.executeQuery()) {
                if (resultSet.next()) {
                    System.out.println("Data saat ini:");
                    System.out.println("ID: " + resultSet.getInt("id"));
                    System.out.println("Nama: " + resultSet.getString("nama"));
                    System.out.println("Alamat: " + resultSet.getString("alamat"));
                    System.out.println("------------------------");

                    System.out.print("Masukkan nama baru: ");
                    String newNama = scanner.next();

                    System.out.print("Masukkan alamat baru: ");
                    String newAlamat = scanner.next();

                    try (PreparedStatement updateStatement = connection.prepareStatement(updateDataSQL)) {
                        updateStatement.setString(1, newNama);
                        updateStatement.setString(2, newAlamat);
                        updateStatement.setInt(3, idToUpdate);

                        updateStatement.executeUpdate();
                        System.out.println("Data berhasil di-update.");
                    }
                } else {
                    System.out.println("Data tidak ditemukan.");
                }
            }
        }
    }
}
