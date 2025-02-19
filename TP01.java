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
    static String kodeVoucher = ""; // Menyimpan kode voucher terbaru
    static double totalPengeluaran = 0; // Kita set semuanya jadi 0 dulu
    static int jumlahTransaksi = 0;
    static double totalPendapatan = 0;
    static double totalDiskonDiterima = 0;
    static double pendapatanTerbesar = 0; 
    static double pembelianTerbesar = 0;

    public static void main(String[] args) {
        tampilanAwal();
        
        // Validasi input awal (tidak boleh negatif atau 0)
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
            case 1: // Menu Penjual
                menuPenjual();
                break;
            case 2: // Menu Pembeli
                menuPembeli(); 
                break ;
            case 3: // Menu Hari Selanjutnya
                hariSelanjutnya();
                break;
            case 4: //untuk keluar dari main menu 
                System.out.println("\n===========================================");
                System.out.println("Terima kasih telah menggunakan Burhanpedia!");
                System.out.println("===========================================\n");
                running = false; // stop while loop
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
                case 5:
                    generateVoucher();
                    break;
                // case 6:
                //     kirimBarang();
                //     break;
                case 7:
                    laporanPendapatan();
                    break;
                case 8: // Ke menu utama makanya kita set while jadi false
                    penjual = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid."); 
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
                case 4:
                    beliBarang();
                    break;
                case 5:
                    generateVoucher();
                    break;
                // case 6:
                //     kirimBarang();
                //     break;
                case 7:
                    laporanPengeluaran();
                    break;
                case 8:
                    pembeli = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid."); 
                    break;
            }
        }
    }
    
    // Hari Selanjutnya
    public static void hariSelanjutnya() {
        today = today.plusDays(1); // Tambah satu hari
        Locale indonesia = new Locale("id", "ID");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy", indonesia);
        System.out.println("\nTanggal : " + today.format(formatter));
        System.out.println("Pok pok pok!");
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
        stok += validasiInputInt("Masukkan jumlah stok yang ingin ditambah: "); // Validasi input stok agar tdk negatif
        System.out.println("Stok berhasil ditambah! Stok saat ini: " + stok);
    }

    // Ubah harga barang
    public static void ubahHargaBarang() {
        hargaBarang = validasiInputDouble("Masukkan harga barang yang baru: "); // Validasi input agar tdk negatif
        System.out.println("Harga barang diperbarui: " + hargaBarang);
    }

    // Generate voucher
    public static void generateVoucher() {
        Random random = new Random();
        String kode = ""; // String kosong utk nanti hasilnya di convert ke angka lewat konversiKodeAngka
        for (int i=0; i<10; i++) {
            char huruf = (char) ('A' + random.nextInt(26)); // Generate random huruf A-Z
            kode += huruf;
        }
        kodeVoucher = konversiKodeKeAngka(kode);
        System.out.println("Voucher berhasil dibuat: " + kodeVoucher); 
    }

    // Konversi kode ke angka
    public static String konversiKodeKeAngka(String kode) {
        String hasil = "";
        for (int i = 0; i < kode.length(); i++) {
            int angka = getKodeAngka(kode.charAt(i)); // Method getKodeAngka utk generate angka sesuai ketentuan soal
            int value = (angka * (i + 1)) % 10; 
            hasil += value;
        }
        return hasil;
    }
    // Kode 93
    public static int getKodeAngka(char huruf) {
        switch (huruf) {
            case 'A': return 10;
            case 'B': return 11;
            case 'C': return 12;
            case 'D': return 13;
            case 'E': return 14;
            case 'F': return 15;
            case 'G': return 16;
            case 'H': return 17;
            case 'I': return 18;
            case 'J': return 19;
            case 'K': return 20;
            case 'L': return 21;
            case 'M': return 22;
            case 'N': return 23;
            case 'O': return 24;
            case 'P': return 25;
            case 'Q': return 26;
            case 'R': return 27;
            case 'S': return 28;
            case 'T': return 29;
            case 'U': return 30;
            case 'V': return 31;
            case 'W': return 32;
            case 'X': return 33;
            case 'Y': return 34;
            case 'Z': return 35;
            default: return 0;
        }
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
    
    // Beli barang
    public static void beliBarang() {
        int jumlah = validasiInputInt("Masukkan jumlah barang yang ingin dibeli: ");
        // Validasi jumlah dan stok 
        if (jumlah > stok) { // Pastikan stok lebih banyak dr jumlah yg ingin dibeli
            System.out.println("Stok tidak mencukupi!");
            return;
        }
        if (jumlah <= 0) { // Pastikan jumlah merupakan angka valid lebih dari 0
            System.out.println("Jumlah barang tidak valid!");
            return;
        }

        // User input voucher
        String inputVoucher = "";
        boolean inputValid = true;
        while (inputValid) {
            System.out.println("Masukkan kode voucher");
            System.out.println("Jika tidak ada, ketik 'skip'");
            System.out.println("Jika ingin buat, ketik 'generate'");
            System.out.println("================================");
            System.out.print("Kode: ");
            inputVoucher = scanner.next();
            
            if (inputVoucher.equalsIgnoreCase("generate")) { // kalau user generate voucher
                generateVoucher();
                System.out.println("Masukkan kode voucher yang baru saja dibuat: " + kodeVoucher);
            } 
            else if (!inputVoucher.equalsIgnoreCase("skip") && !inputVoucher.equals(kodeVoucher)) {
                System.out.println("Kode voucher tidak valid!"); // Kalau tidak skip tapi voucher tidak sesuai dgn yg udah dibuat sblmnya
            } else { 
                inputValid = false; // Keluar dr loop
            }
        }
        double diskon = 0;
        if (!inputVoucher.equalsIgnoreCase("skip") && inputVoucher.equals(kodeVoucher)) {
            diskon = hitungDiskon(kodeVoucher); // Kalau skip dan ternyata kode voucher sesuai, hitung diskon
        }

        // Hitung harga setelah diskon
        double hargaSetelahDiskon = hargaBarang * jumlah * (1 - diskon / 100);

        // Hitung pajak 3% setelah diskon
        double pajak = hargaSetelahDiskon * 0.03; // Pertama hitung dulu pajaknya brp
        double totalHarga = hargaSetelahDiskon + pajak; // Baru tambahin harga setelah diskon + pajak

        // Cek saldo
        if (saldo >= totalHarga) { // Pastikan saldo lebih besar daripada total harganya yg sudah kita hitung including pajak
            saldo -= totalHarga; // Saldo dikurang total harga
            stok -= jumlah; // Stok dikurang dgn jumlah yang kita beli
            totalPengeluaran += totalHarga; // Total pengeluaran kita tambahkan total harga yang barusan di purchase
            jumlahTransaksi++; // Jumlah transaksi bertambah
            totalPendapatan += totalHarga; // Total pendapatan kita tambahkan dengan total harga yang barusan di purchase
            totalDiskonDiterima += hargaBarang * jumlah * (diskon / 100); // Total diskon diterima
            if (totalHarga > pendapatanTerbesar) pendapatanTerbesar = totalHarga; // Untuk mendapatkan pendapatan terbesar
            if (totalHarga > pembelianTerbesar) pembelianTerbesar = totalHarga; // Untuk mendapatkan pembelian terbesar
            System.out.printf("Voucher berhasil digunakan! Harga setelah diskon: %.2f\n", totalHarga);
            System.out.printf("Pembelian sukses! Saldo saat ini: %.2f\n", saldo);
            }       
        else {
            System.out.println("Saldo tidak cukup.");
        }
    }

    // Hitung diskon (rekursif)
    public static int hitungDiskon(String kode) {
        if (kode.isEmpty()) {
            return 0; // Kalau kode kosong kembalikan 0 biar tidak error
        }
        if (kode.length() == 1) {
            return Character.getNumericValue(kode.charAt(0));
        }
        // Ambil digit pertama dan terakhir
        int depan = Character.getNumericValue(kode.charAt(0)); // Ambil digit pertama
        int belakang = Character.getNumericValue(kode.charAt(kode.length() - 1)); // Ambil digit terakhir

        // Hitung hasil perkalian 
        int hasil = depan * belakang;

        // Rekursif 
        return hasil + hitungDiskon(kode.substring(1, kode.length() - 1));
    }

    // Laporan pendapatan
    public static void laporanPendapatan() {
        System.out.println("==============================");
        System.out.println("LAPORAN PENDAPATAN");
        System.out.println("------------------------------");
        System.out.printf("Total Pendapatan: %.2f\n", totalPendapatan);
        System.out.printf("Jumlah Transaksi: %d\n", jumlahTransaksi);
        double rataRataPendapatan = totalPendapatan / jumlahTransaksi;
        System.out.printf("Rata-rata Pendapatan: %.2f\n", rataRataPendapatan);
        System.out.printf("Total Diskon Diberikan: %.2f\n", totalDiskonDiterima);
        System.out.printf("Pendapatan Terbesar: %.2f\n", pendapatanTerbesar);
        System.out.println("==============================");
    }

    // Laporan pengeluaran
    public static void laporanPengeluaran() {
        System.out.println("==============================");
        System.out.println("LAPORAN PENGELUARAN");
        System.out.println("------------------------------");
        System.out.printf("Total Pengeluaran: %.2f\n", totalPengeluaran);
        System.out.printf("Jumlah Transaksi: %d\n", jumlahTransaksi);
        double rataRataPengeluaran = totalPengeluaran / jumlahTransaksi;
        System.out.printf("Rata-rata Pengeluaran: %.2f\n", rataRataPengeluaran);
        System.out.printf("Total Diskon Diterima: %.2f\n", totalDiskonDiterima);
        System.out.printf("Pembelian Terbesar: %.2f\n", pembelianTerbesar);
        System.out.println("==============================");
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