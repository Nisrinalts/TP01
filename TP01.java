import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class TP01 {
    public static void main(String[] args) {
        //Tampilan awal
        Scanner scanner = new Scanner(System.in);
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

        System.out.print("\nMasukkan stok awal: ");
        int stok = scanner.nextInt();
        System.out.print("Masukkan harga barang: ");
        double hargaBarang = scanner.nextDouble();
        System.out.print("Masukkan saldo awal: ");
        double saldo = scanner.nextDouble();

        while (true){
            System.out.println("\nPilih menu\n" + //
                            "1. Penjual\n" + //
                            "2. Pembeli\n" + //
                            "3. Hari Selanjutnya\n" + //
                            "4. Keluar");
            System.out.print("Perintah : ");
            int perintah = scanner.nextInt();
            
            if (perintah == 4) {
                System.out.println("\n===========================================");
                System.out.println("Terima kasih telah menggunakan Burhanpedia!");
                System.out.println("===========================================\n");
                break;
            }

            if (perintah == 1){
                while (true) {
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

                    if (menuPenjual == 1) {
                        System.out.println ("==============================");
                        System.out.println("Stok saat ini: " + stok);
                        System.out.println("==============================");
                    }
                    else if(menuPenjual == 2) {
                        System.out.println("==============================");
                        System.out.println("Harga barang saat ini : " + hargaBarang);
                        System.out.println("==============================");
                    }
                    else if(menuPenjual == 3) {
                        System.out.print("Masukkan jumlah stok yang ingin ditambah: ");
                        int stokTambah = scanner.nextInt();
                        stok += stokTambah;
                        System.out.println("Stok berhasil ditambah! Stok saat ini: " + stok);
                    }
                    else if(menuPenjual == 4) {
                        System.out.print("Masukkan harga barang yang baru: ");
                        double hargaBaru = scanner.nextDouble();
                        hargaBarang = hargaBaru;
                        System.out.println("Harga barang diperbarui: " + hargaBarang);
                    }
                    else if(menuPenjual == 8){
                        break ;
                    }
                }
            }
            else if(perintah==2) {
                while (true){
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

                    if (menuPembeli == 1){
                        System.out.println("==============================");
                        System.out.println("Saldo saat ini:" + saldo);
                        System.out.println("==============================");
                    }
                    else if(menuPembeli == 2){
                        System.out.print("Masukkan jumlah saldo yang ingin ditambah: ");
                        double saldoTambah = scanner.nextDouble();
                        saldo += saldoTambah;
                        System.out.println("Saldo berhasil ditambah! Saldo saat ini: " + saldo);
                    }
                    else if(menuPembeli == 3){
                        System.out.println("==============================");
                        System.out.println("Harga barang saat ini: " + hargaBarang);
                        System.out.println("==============================");
                    }
                    else if(menuPembeli == 8){
                        break;
                    }
                }
            System.out.println("Update nih");
            }
        }
        scanner.close();
    }
}



