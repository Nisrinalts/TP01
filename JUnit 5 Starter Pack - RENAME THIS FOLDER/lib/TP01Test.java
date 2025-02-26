import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TP01Test {

    @Before
    public void setUp() {
        TP01.stok = 10;
        TP01.hargaBarang = 1000.0;
        TP01.saldo = 5000.0;
        TP01.totalPendapatan = 0;
        TP01.totalPengeluaran = 0;
        TP01.totalDiskonDiterima = 0;
        TP01.jumlahTransaksi = 0;
        TP01.pendapatanTerbesar = 0;
        TP01.pembelianTerbesar = 0;
        TP01.validatorJumlahTransaksi = false;
        TP01.nextDay = false;
    }

    @Test
    public void testCekHargaBarang() {
        assertEquals("==============================\nHarga barang saat ini: 1000.0\n==============================", TP01.cekHargaBarang());
    }

    @Test
    public void testCekSaldo() {
        assertEquals("==============================\nSaldo saat ini: 5000.00\n==============================", TP01.cekSaldo());
    }

    @Test
    public void testCekSeratus() {
        assertEquals(5, TP01.cekSeratus(123));
        assertEquals(72, TP01.cekSeratus(888));
    }

    @Test
    public void testCekStok() {
        assertEquals("==============================\nStok saat ini: 10\n==============================", TP01.cekStok());
    }

    @Test
    public void testGenerateVoucher() {
        String voucher = TP01.generateVoucher();
        assertTrue(voucher.startsWith("Voucher berhasil dibuat: "));
        assertEquals(10, TP01.kodeVoucher.length());
    }

    @Test
    public void testGetKodeAngka() {
        assertEquals(10, TP01.getKodeAngka('A'));
        assertEquals(35, TP01.getKodeAngka('Z'));
        assertEquals(0, TP01.getKodeAngka('1')); // Tes dengan angka (tidak valid)
    }

    @Test
    public void testHariSelanjutnya() {
        String result = TP01.hariSelanjutnya();
        assertTrue(result.contains("Tanggal"));
    }

    @Test
    public void testHitungDiskon() {
        assertEquals(60, TP01.hitungDiskon("0123456789"));
        assertEquals(0, TP01.hitungDiskon("5050505050"));
        assertEquals(1, TP01.hitungDiskon("1234567891"));
    }

    @Test
    public void testKirimBarang() {
        TP01.jumlahTransaksi = 1;
        assertEquals("Barang sedang dalam perjalanan.", TP01.kirimBarang());

        TP01.jumlahTransaksi = 0;
        assertEquals("Tidak ada barang yang bisa dikirim.", TP01.kirimBarang());
    }

    @Test
    public void testKonversiKodeKeAngka() {
        String kodeAngka = TP01.konversiKodeKeAngka("ABCDEFGHIZ");
        assertEquals(10, kodeAngka.length());
    }

    @Test
    public void testLacakBarang() {
        TP01.validatorJumlahTransaksi = false;
        TP01.nextDay = false;
        assertEquals("Tidak ada barang yang sedang dikirim.", TP01.lacakBarang());

        TP01.validatorJumlahTransaksi = true;
        TP01.nextDay = false;
        assertEquals("Status pengiriman barang: sending", TP01.lacakBarang());

        TP01.validatorJumlahTransaksi = true;
        TP01.nextDay = true;
        assertEquals("Status pengiriman barang: sending", TP01.lacakBarang());
    }

    @Test
    public void testLaporanPendapatan() {
        TP01.totalPendapatan = 10000;
        TP01.jumlahTransaksi = 2;
        TP01.totalDiskonDiterima = 500;
        TP01.pendapatanTerbesar = 7000;
        
        String result = TP01.laporanPendapatan();
        assertTrue(result.contains("Total Pendapatan: 10000.00"));
        assertTrue(result.contains("Jumlah Transaksi: 2"));
        assertTrue(result.contains("Rata-rata Pendapatan: 5000.00"));
        assertTrue(result.contains("Total Diskon Diberikan: 500.00"));
        assertTrue(result.contains("Pendapatan Terbesar: 7000.00"));
    }

    @Test
    public void testLaporanPengeluaran() {
        TP01.totalPengeluaran = 5000;
        TP01.jumlahTransaksi = 2;
        TP01.totalDiskonDiterima = 400;
        TP01.pembelianTerbesar = 3000;
        
        String result = TP01.laporanPengeluaran();
        assertTrue(result.contains("Total Pengeluaran: 5000.00"));
        assertTrue(result.contains("Jumlah Transaksi: 2"));
        assertTrue(result.contains("Rata-rata Pengeluaran: 2500.00"));
        assertTrue(result.contains("Total Diskon Diterima: 400.00"));
        assertTrue(result.contains("Pembelian Terbesar: 3000.00"));
    }

    @Test
    public void testTambahStok() {
        TP01.stok = 5;
        TP01.validasiInputInt("5");
        assertEquals("Stok berhasil ditambah! Stok saat ini: 10", TP01.tambahStok());
    }

    @Test
    public void testTampilanAwal() {
        assertTrue(TP01.tampilanAwal().contains("Selamat datang di Burhanpedia!"));
    }

    @Test
    public void testTopUpSaldo() {
        TP01.validasiInputDouble("500");
        assertEquals("Saldo berhasil ditambah! Saldo saat ini: 5500.00", TP01.topUpSaldo());
    }

    @Test
    public void testUbahHargaBarang() {
        TP01.validasiInputDouble("2000");
        assertEquals("Harga barang diperbarui: 2000.00 ", TP01.ubahHargaBarang());
    }
}
