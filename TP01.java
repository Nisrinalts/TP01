import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class TP01 {
    static Scanner scanner = new Scanner(System.in);
    static LocalDate today = LocalDate.now(); // Tanggal saat ini
    static int stok;
    static double hargaBarang;
    static double saldo;

    public static void main(String[] args) {
        tampilanAwal();
        
        // Validasi input awal
        stok = validasiInputInt("Masukkan stok awal: ");
        hargaBarang = validasiInputDouble("Masukkan harga barang: ");
        saldo = validasiInputDouble("Masukkan saldo awal: ");
        
        mainMenu();
        
        scanner.close();
    }

    // Tampilan awal
    public static void tampilanAwal() {
        System.out.println("=============================================================");
        System.out.println("\n  ____             _                 _____         _ _       \r\n" +
                " |  _ \\           | |               |  __ \\       | (_)      \r\n" +
                " | |_) |_   _ _ __| |__   __ _ _ __ | |__) |__  __| |_  __ _ \r\n" +
                " |  _ <| | | | '__| '_ \\ / _` | '_ \\|  ___/ _ \\/ _` | |/ _` |\r\n" +
                " | |_) | |_| | |  | | | | (_| | | | | |  |  __/ (_| | | (_| |\r\n" +
                " |____/ \\__,_|_|  |_| |_|\\__,_|_| |_|_|   \\___|\\__,_|_|\\__,_|\r\n" +
                "                                                             \r\n" +
                "                                                             ");
        System.out.println("=============================================================");
        System.out.println("============== Selamat datang di Burhanpedia! ===============");
        System.out.println("=============================================================");
    }

    // Main menu
    public static void mainMenu() {
        boolean running = true; 
        while (running){
            System.out.println("\nPilih menu\n" + //
                            "1. Penjual\n" + //
                            "2. Pembeli\n" + //
                            "3. Hari Selanjutnya\n" + //
                            "4. Keluar\n");
            System.out.print("Perintah : ");
            int perintah = scanner.nextInt();
            
            switch(perintah){
            case 4: //untuk keluar dari main menu 
                System.out.println("\n===========================================");
                System.out.println("Terima kasih telah menggunakan Burhanpedia!");
                System.out.println("===========================================\n");
                running = false; // stop while loop
                break;
            
            case 1: // Menu Penjual
                menuPenjual();
                break;
            case 2: // Menu Pembeli
                menuPembeli(); 
                break ;
            case 3: // Menu Hari Selanjutnya
                hariSelanjutnya();
                break;
            default:
                System.out.println("Pilihan tidak valid.");
                break;
            }
        }
    
    }

    // Menu Penjual
    public static void menuPenjual() {
        boolean penjual = true;
        while (penjual) {
            System.out.println("\n===== MENU PENJUAL =====\n" + //
                    "1. Cek Stok\n" + //
                    "2. Cek Harga Barang\n" + //
                    "3. Tambah Stok\n" + //
                    "4. Ubah Harga Barang\n" + //
                    "5. Generate Voucher\n" + //
                    "6. Kirim Barang\n" + //
                    "7. Lihat Laporan Pendapatan\n" + //
                    "8. Kembali ke menu utama");
            System.out.print("\nPerintah : ");
            int menuPenjual = scanner.nextInt();
            
            switch(menuPenjual) {
                case 1:
                    cekStok();
                    break;
                case 2:
                    cekHargaBarang();
                    break;
                case 3:
                    tambahStok();
                    break;
                case 4:
                    ubahHargaBarang();
                    break;
                case 8:
                    penjual = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid."); // FIXED: Tambah titik koma (;)
                    break;
            }
        }
    }

    // Menu Pembeli
    public static void menuPembeli() {
        boolean pembeli = true;
        while (pembeli){
            System.out.println("\n===== MENU PEMBELI =====\n" + //
                    "1. Cek Saldo\n" + //
                    "2. Top Up Saldo\n" + //
                    "3. Cek Harga Barang\n" + //
                    "4. Beli Barang\n" + //
                    "5. Generate Voucher\n" + //
                    "6. Lacak Barang\n" + //
                    "7. Lihat Laporan Pengeluaran\n" + //
                    "8. Kembali ke menu utama");
            System.out.print("\nPerintah : ");
            int menuPembeli = scanner.nextInt();

            switch (menuPembeli) {
                case 1:
                    cekSaldo();
                    break;
                case 2:
                    topUpSaldo();
                    break;
                case 3:
                    cekHargaBarang();
                    break;
                case 8:
                    pembeli = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid."); // FIXED: Tambah titik koma (;)
                    break;
            }
        }
    }
    
    // Hari Selanjutnya
    public static void hariSelanjutnya() {
        today = today.plusDays(1); // Tambah satu hari
        Locale indonesia = new Locale("id", "ID");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy", indonesia);
        System.out.println("\nHari selanjutnya: " + today.format(formatter));
    }

    // Cek stok
    public static void cekStok() {
        System.out.println ("==============================");
        System.out.println("Stok saat ini: " + stok);
        System.out.println("==============================");
    }

    // Cek harga barang
    public static void cekHargaBarang() {
        System.out.println("==============================");
        System.out.println("Harga barang saat ini : " + hargaBarang);
        System.out.println("==============================");
    }

    // Tambah stok
    public static void tambahStok() {
        stok += validasiInputInt("Masukkan jumlah stok yang ingin ditambah: ");
        System.out.println("Stok berhasil ditambah! Stok saat ini: " + stok);
    }

    // Ubah harga barang
    public static void ubahHargaBarang() {
        hargaBarang = validasiInputDouble("Masukkan harga barang yang baru: ");
        System.out.println("Harga barang diperbarui: " + hargaBarang);
    }

    // Cek saldo
    public static void cekSaldo() {
        System.out.println("==============================");
        System.out.println("Saldo saat ini: " + saldo);
        System.out.println("==============================");
    }

    // Top up saldo
    public static void topUpSaldo() {
        saldo += validasiInputDouble("Masukkan jumlah saldo yang ingin ditambah: ");
        System.out.println("Saldo berhasil ditambah! Saldo saat ini: " + saldo);
    }

    // Validasi input integer
    public static int validasiInputInt (String pesan) {
        int input;
        do {
            System.out.print(pesan);
            input = scanner.nextInt();
            if (input <= 0) {
                System.out.println("Nominal tidak valid!");
            }
        } while (input <= 0);
        return input;
    }

    // Validasi input double
    public static double validasiInputDouble(String pesan) {
        double input;
        do {
            System.out.print(pesan);
            input = scanner.nextDouble();
            if (input <= 0) {
                System.out.println("Nominal tidak valid!");
            }
        } while (input <= 0);
        return input;
    }
}
