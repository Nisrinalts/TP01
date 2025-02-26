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
    static boolean nextDay = false;
    static boolean validatorJumlahTransaksi = false;
    static boolean validatorInputBeliBarang = false;

    public static void main(String[] args) {
        System.out.println(tampilanAwal());
        
        while (true) {
            System.out.print("Masukkan stok awal: ");
            if (scanner.hasNextInt()) { // Pastikan input integer
                stok = scanner.nextInt();
                if (stok > 0) break;
            }
            System.out.println("Masukkan angka yang valid!");
            scanner.nextLine(); 
        }
        while (true) {
            System.out.print("Masukkan harga barang: ");
            if (scanner.hasNextDouble()) { // Pastikan input double
                hargaBarang = scanner.nextDouble();
                if (hargaBarang > 0) break;
            }
            System.out.println("Masukkan angka yang valid!");
            scanner.nextLine(); 
        }
        while (true) {
            System.out.print("Masukkan saldo awal: ");
            if (scanner.hasNextDouble()) { // Pastikan input double
                saldo = scanner.nextDouble();
                if (saldo > 0) break;
            }
            System.out.println("Masukkan angka yang valid!");
            scanner.nextLine(); 
        }

        mainMenu();
        scanner.close();
    }
    
    // Tampilan awal
    public static String tampilanAwal() {
        return "=============================================================\n"
        + "\n  ____             _                 _____         _ _       \r\n"
        + " |  _ \\           | |               |  __ \\       | (_)      \r\n"
        + " | |_) |_   _ _ __| |__   __ _ _ __ | |__) |__  __| |_  __ _ \r\n"
        + " |  _ <| | | | '__| '_ \\ / _` | '_ \\|  ___/ _ \\/ _` | |/ _` |\r\n"
        + " | |_) | |_| | |  | | | | (_| | | | | |  |  __/ (_| | | (_| |\r\n"
        + " |____/ \\__,_|_|  |_| |_|\\__,_|_| |_|_|   \\___|\\__,_|_|\\__,_|\r\n"
        + "                                                             \r\n"
        + "                                                             \n"
        + "=============================================================\n"
        + "============== Selamat datang di Burhanpedia! ===============\n"
        + "=============================================================";
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
            if (!scanner.hasNextInt()) {  // validasi input integer atau bukan
                System.out.println("Pilihan tidak valid!");
                scanner.next();  
                continue;
            }

            int perintah = scanner.nextInt();
            
            switch(perintah){
            case 1: // Menu Penjual
                menuPenjual();
                break;
            case 2: // Menu Pembeli
                menuPembeli(); 
                break ;
            case 3: // Menu Hari Selanjutnya
                System.out.println(hariSelanjutnya());
                break;
            case 4: //untuk keluar dari main menu 
                System.out.println("\n===========================================");
                System.out.println("Terima kasih telah menggunakan Burhanpedia!");
                System.out.println("===========================================\n");
                running = false; // stop while loop
                break;
            default:
                System.out.println("Pilihan tidak valid!");
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
            if (!scanner.hasNextInt()) { // validasi input integer atau bukan
                System.out.println("Pilihan tidak valid!");
                scanner.next();  
                continue;
            }

            int menuPenjual = scanner.nextInt();
            
            switch(menuPenjual) {
                case 1:
                    System.out.println(cekStok());
                    break;
                case 2:
                    System.out.println(cekHargaBarang());
                    break;
                case 3:
                    System.out.println(tambahStok());
                    break;
                case 4:
                    System.out.println(ubahHargaBarang());
                    break;
                case 5:
                    System.out.println(generateVoucher());
                    break;
                case 6:
                    System.out.println(kirimBarang());
                    break;
                case 7:
                    System.out.println(laporanPendapatan());
                    break;
                case 8: // Kembali ke menu utama
                    penjual = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid!"); 
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
            if (!scanner.hasNextInt()) { // Validasi input integer atau bukan
                System.out.println("Pilihan tidak valid!");
                scanner.next();  
                continue;
            }

            int menuPembeli = scanner.nextInt();

            switch (menuPembeli) {
                case 1:
                    System.out.println(cekSaldo());
                    break;
                case 2:
                    System.out.println(topUpSaldo());
                    break;
                case 3:
                    System.out.println(cekHargaBarang());
                    break;
                case 4:
                    beliBarang();
                    break;
                case 5:
                    System.out.println(generateVoucher());
                    break;
                case 6:
                    System.out.println(lacakBarang());
                    break;
                case 7:
                    System.out.println(laporanPengeluaran());
                    break;
                case 8:
                    pembeli = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid!"); 
                    break;
            }
        }
    }
    
    // Hari Selanjutnya
    public static String hariSelanjutnya() {
        today = today.plusDays(1); // Tambah satu hari
        Locale indonesia = new Locale("id", "ID");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy", indonesia);
        String output = "\nTanggal : " + today.format(formatter);

        if (validatorJumlahTransaksi == true) {
            output +="\nHari telah berganti. Barang sudah sampai!\n";
            validatorJumlahTransaksi = false;
        }
        else {
            output +="\nPok pok pok!";
            nextDay = true; // validator sudah pernah di akses atau belum
        }
    return output;
    }

    // Cek stok
    public static String cekStok() {
        return "==============================\n" +
               "Stok saat ini: " + stok + "\n" +
               "==============================";
    }

    // Cek harga barang
    public static String cekHargaBarang() {
        return "==============================\n" +
               String.format("Harga barang saat ini: %.1f\n", hargaBarang) +
               "==============================";
    }

    // Tambah stok
    public static String tambahStok() {
        int temp = validasiInputInt("Masukkan jumlah stok yang ingin ditambah: "); 
    
        if (temp > 0) {
            stok += temp;
            return "Stok berhasil ditambah! Stok saat ini: " + stok;
        }
        return ""; 
    }

    // Ubah harga barang
    public static String ubahHargaBarang() {
        double temp = validasiInputDouble("Masukkan harga barang yang baru: ");
        if (temp > 0) {
            hargaBarang = temp;
            return String.format("Harga barang diperbarui:%.2f ", hargaBarang);
        }
        return "";
    }

    // Generate voucher
    public static String generateVoucher() {
        Random random = new Random();
        String kode = ""; // String kosong utk nanti hasilnya di convert ke angka lewat konversiKodeAngka
        for (int i=0; i<10; i++) {
            char huruf = (char) ('A' + random.nextInt(26)); // Generate random huruf A-Z
            kode += huruf;
        }
        kodeVoucher = konversiKodeKeAngka(kode);
        return "Voucher berhasil dibuat: " + kodeVoucher; 
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

    // Laporan pendapatan
    public static String laporanPendapatan() {
        String laporan = "==============================\n";
        laporan += "LAPORAN PENDAPATAN\n";
        laporan += "------------------------------\n";
        laporan += String.format("Total Pendapatan: %.2f\n", totalPendapatan);
        laporan += String.format("Jumlah Transaksi: %d\n", jumlahTransaksi);
        
        double rataRataPendapatan;
        if (jumlahTransaksi == 0) {
            rataRataPendapatan = 0.00; // Kalau belum ada transaksi, rata2 pendapatannya 0
        } else {
            rataRataPendapatan = totalPendapatan / jumlahTransaksi;
        }
        laporan += String.format("Rata-rata Pendapatan: %.2f\n", rataRataPendapatan);
        laporan += String.format("Total Diskon Diberikan: %.2f\n", totalDiskonDiterima);
        laporan += String.format("Pendapatan Terbesar: %.2f\n", pendapatanTerbesar);
        laporan += "==============================";
        
        return laporan;
    }

    // Kirim barang
    public static String kirimBarang() {
        if (jumlahTransaksi != 0) { // Kalau ada transaksi
            validatorJumlahTransaksi = true;
            return "Barang sedang dalam perjalanan.";
        }
        return "Tidak ada barang yang bisa dikirim."; // Kalau belum ada transaksi
    }

    // Cek saldo
    public static String cekSaldo() {
        return "==============================\n" +
           String.format("Saldo saat ini: %.2f\n", saldo) +
           "==============================";
    }
    // Top up saldo
    public static String topUpSaldo() {
        double temp = validasiInputDouble("Masukkan jumlah saldo yang ingin ditambah: ");
        if (temp > 0) { // Ketika inputnya setelah di validasi input lebih dari 0 kondisinya
            saldo += temp;
            return String.format("Saldo berhasil ditambah! Saldo saat ini: %.2f", saldo);
        }
        return "";
    }
    
    // Beli barang
    public static boolean beliBarang() {
        int jumlah = validasiInputBeliBarang("Masukkan jumlah barang yang ingin dibeli: ");
    
        // Jika input tidak valid, kembali ke menu pembeli
        if (jumlah <= 0) return false;

        // Validasi jumlah barang
        if (!cekJumlahBarang(jumlah)) {
            return false;
        }
        
        // User input voucher
        String inputVoucher = inputVoucher();
        // Hitung diskon berdasarkan kode voucher
        double diskon = hitungDiskonVoucher(inputVoucher);
        // Total harga
        double hitungTotalHarga = totalHarga(jumlah, diskon);

        // Cek saldo dan transaksi
        boolean prosesPembelian = cekSaldoDanTransaksi(jumlah, hitungTotalHarga, diskon, inputVoucher);
        if (prosesPembelian == false) {
            System.out.println("Saldo tidak mencukupi.");
            return false;
            }
        return true;
        }

    // Total harga
    private static double totalHarga(int jumlah, double diskon) {
        // Hitung total harga setelah diskon (Pendapatan sebelum pajak)
        double hargaSetelahDiskon = hargaBarang * jumlah * (1 - diskon / 100);
        totalPendapatan += hargaSetelahDiskon; // pendapatan dihitung sebelum pajak

        // pajak 3% setelah proses voucher selesai (pengeluaran setelah pajak)
        double pajak = hargaSetelahDiskon * 0.03;
        double totalHarga = hargaSetelahDiskon + pajak;
        return totalHarga;
    }
    
    // Input kode voucher dari user
    private static String inputVoucher() {
        String inputVoucher = "";
        boolean inputValid = true;

        while (inputValid) { 
            System.out.println("Masukkan kode voucher");
            System.out.println("Jika tidak ada, ketik 'skip'");
            System.out.println("Jika ingin buat, ketik 'generate'");
            System.out.println("================================");
            System.out.print("Kode: ");
            inputVoucher = scanner.next();
            
            // Generate voucher
            if (inputVoucher.equalsIgnoreCase("generate")) { 
                generateVoucher();
                System.out.println("Masukkan kode voucher yang baru saja dibuat: " + kodeVoucher);
            } 
            // skip atau kode voucher sudah sesuai
            else if (inputVoucher.equalsIgnoreCase("skip") || inputVoucher.equals(kodeVoucher)) {
                return inputVoucher;
            } 
            // Kode voucher tidak valid
            else {
                System.out.println("Kode voucher tidak valid!");
            }
        }
        return inputVoucher;
    }

    // Menghitung total harga setelah diskon
    private static double hitungDiskonVoucher(String inputVoucher) {
        // Jika kode voucher valid
        if (!inputVoucher.equalsIgnoreCase("skip") && inputVoucher.equals(kodeVoucher)) {
            return hitungDiskon(kodeVoucher); 
        }
        return 0; // kalau skip diskon
    }

    // Proses pembelian jika saldo mencukupi
    private static boolean cekSaldoDanTransaksi(int jumlah, double totalHarga, double diskon, String inputVoucher) {
        if (saldo >= totalHarga) {
            saldo -= totalHarga; // Saldo dikurang total harga
            stok -= jumlah; // Stok dikurang dgn jumlah yang kita beli
            totalPengeluaran += totalHarga; // Total pengeluaran kita tambahkan total harga yang barusan di purchase
            jumlahTransaksi++; // Jumlah transaksi bertambah
            totalPendapatan += totalHarga; // Total pendapatan kita tambahkan dengan total harga yang barusan di purchase
            totalDiskonDiterima += hargaBarang * jumlah * (diskon / 100); // Total diskon diterima

            if (totalHarga > pendapatanTerbesar) {
                pendapatanTerbesar = totalHarga; // Untuk mendapatkan pendapatan terbesar
            }
            if (totalHarga > pembelianTerbesar) {
                pembelianTerbesar = totalHarga; // Untuk mendapatkan pembelian terbesar
            }
            // 
            if (!inputVoucher.equalsIgnoreCase("skip")) {
                System.out.printf("Voucher berhasil digunakan! Harga setelah diskon: %.2f\n", totalHarga);
            }
            System.out.printf("Pembelian sukses! Saldo saat ini: %.2f\n", saldo);
            return true;
        } 
        // return true;
        return false;
    }

    // Hitung diskon (rekursif)
    public static int hitungDiskon(String kode) {
        if (kode.isEmpty()) {
            return 0; // Kalau kode kosong kembalikan 0 biar tidak error
        }
        if (kode.length() == 1) { // Kalau panjang kode 1, kembalikan saja itu
            return Character.getNumericValue(kode.charAt(0));
        }
        // Ambil digit pertama dan terakhir
        int depan = Character.getNumericValue(kode.charAt(0)); // Ambil digit pertama
        int belakang = Character.getNumericValue(kode.charAt(kode.length() - 1)); // Ambil digit terakhir

        // Hitung hasil perkalian 
        int hasil = depan * belakang;

        int total = hasil + hitungDiskon(kode.substring(1, kode.length() - 1)); // Rekursif
        return cekSeratus(total);
    }

    // Mengecek kondisi kalau dia dibawah 100 atau engga
    public static int cekSeratus(int hasil) {
        if (hasil < 100) {
            return hasil; // Kalau kurang dari 100 return hasilnya
        }
        // Kalau lebih dari 100 atau 3 digit pokoknya
        String hsl = String.valueOf(hasil);
        int depan = Character.getNumericValue(hsl.charAt(0)); // ambil digit pertama
        int belakang = Character.getNumericValue(hsl.charAt(hsl.length() - 1)); // ambil digit terakhir
        int tengah = Character.getNumericValue(hsl.charAt(hsl.length()/2)); // ambil digit tengah terus nanti ditambahin 

        // Hitung hasil perkalian 
        int result = depan * belakang + tengah; // Hasil rekursif kalau 3 digit
        return cekSeratus(result);
    }

    // Laporan pengeluaran
    public static String laporanPengeluaran() {
        String laporan = "==============================\n";
        laporan += "LAPORAN PENGELUARAN\n";
        laporan += "------------------------------\n";
        laporan += String.format("Total Pengeluaran: %.2f\n", totalPengeluaran);
        laporan += String.format("Jumlah Transaksi: %d\n", jumlahTransaksi);
        
        double rataRataPengeluaran;
        if (jumlahTransaksi == 0) {
            rataRataPengeluaran = 0.00; // Jika belum ada transaksi, rata-rata pengeluaran adalah 0
        } else {
            rataRataPengeluaran = totalPengeluaran / jumlahTransaksi;
        }
        laporan += String.format("Rata-rata Pengeluaran: %.2f\n", rataRataPengeluaran);
        laporan += String.format("Total Diskon Diterima: %.2f\n", totalDiskonDiterima);
        laporan += String.format("Pembelian Terbesar: %.2f\n", pembelianTerbesar);
        laporan += "==============================";
        
        return laporan;
    }

    // Lacak barang
    public static String lacakBarang() {
        if (validatorJumlahTransaksi == true && nextDay == false) { // ada jumlah transaksi, belum ganti hari
            return "Status pengiriman barang: Sending"; 
        }
        else if (!validatorJumlahTransaksi || !nextDay) { // Belum ada jumlah transaksi atau belum ganti hari
            return "Tidak ada barang yang sedang dikirim."; 
        }
        return "Status pengiriman barang: Sending";
    }

    // Validasi input integer
    public static int validasiInputInt(String pesan) {
        System.out.print(pesan);
        
        // Validasi input integer atau bukan
        if (!scanner.hasNextInt()) {
            System.out.println("Nominal tidak valid!");
            scanner.next(); 
            return -1;  // kembali ke menu
        }
    
        int input = scanner.nextInt();
        
        // Jika angka negatif atau 0
        if (input <= 0) {
            System.out.println("Nominal tidak valid!");
            return -1; // kembali ke menu
        }
        
        return input;
    } 

    // Validasi input beli barang
    public static int validasiInputBeliBarang(String pesan) {
        System.out.print(pesan);
    
        // Pastikan input adalah angka
        if (!scanner.hasNextInt()) {
            System.out.println("Jumlah barang tidak valid!");
            scanner.next(); 
            return 0; // Kembali ke menu
        }
    
        int jumlah = scanner.nextInt();
    
        // Jika jumlah barang <= 0, anggap tidak valid
        if (jumlah <= 0) {
            System.out.println("Jumlah barang tidak valid!");
            return 0;
        }
    
        return jumlah; // Kembalikan jumlah yang valid
    }
    
    // Validasi jumlah barang yang dibeli
    private static boolean cekJumlahBarang(int jumlah) {
        if (jumlah > stok) { // memastikan stok lebih banyak daripada jumlah yang ingin kita beli
            System.out.println("Stok tidak mencukupi!");
            return false;
        }
        if (jumlah <= 0) { // Memastikan jumlah itu angka valid
            System.out.println("Jumlah barang tidak valid!");
            return false;
        }
        return true;
    }
 
    // Validasi input double
    public static double validasiInputDouble(String pesan) {
        System.out.print(pesan);
    
        // Validasi input double atau bukan
        if (!scanner.hasNextDouble()) {
            System.out.println("Nominal tidak valid!");
            scanner.next(); 
            return -1; // kembali ke menu
        }
    
        double input = scanner.nextDouble();
        
        // Jika angka negatif atau 0
        if (input <= 0) {
            System.out.println("Nominal tidak valid!");
            return -1; // kembali ke menu
        }
        
        return input;
    }
    
}
