import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class cobaTP01Test {
    @Before
    public void setUp() {
        // Reset semua variabel statis sebelum setiap test
        cobaTP01.stok = 10;
        cobaTP01.hargaBarang = 5000;
        cobaTP01.saldo = 20000;
        cobaTP01.kodeVoucher = "";
        cobaTP01.totalPengeluaran = 0;
        cobaTP01.jumlahTransaksi = 0;
        cobaTP01.totalPendapatan = 0;
        cobaTP01.totalDiskonDiterima = 0;
        cobaTP01.pendapatanTerbesar = 0;
        cobaTP01.pembelianTerbesar = 0;
        cobaTP01.validatorJumlahTransaksi = false;
        cobaTP01.nextDay = false;
    }

    @Test
    public void testBeliBarang() {
        cobaTP01.saldo = 10000;
        cobaTP01.stok = 5;
        assertTrue(cobaTP01.beliBarang());
    }

    @Test
    public void testCekHargaBarang() {
    assertEquals("==============================\nHarga barang saat ini: 5000.0\n==============================", cobaTP01.cekHargaBarang());
    }

    @Test
    public void testCekSaldo() {
    assertEquals("==============================\nSaldo saat ini: 20000.00\n==============================", cobaTP01.cekSaldo());
    }

    @Test
    public void testCekStok() {
    assertEquals("==============================\nStok saat ini: 10\n==============================", cobaTP01.cekStok());
    }
    
    @Test
    public void testGenerateVoucher() {
    String voucher = cobaTP01.generateVoucher();
    assertTrue(voucher.startsWith("Voucher berhasil dibuat:"));
    }

    @Test
    public void testGetKodeAngka() {
    assertEquals(10, cobaTP01.getKodeAngka('A'));
    assertEquals(35, cobaTP01.getKodeAngka('Z'));
    }

    @Test
    public void testHariSelanjutnya() {
    String result = cobaTP01.hariSelanjutnya();
    assertTrue(result.contains("Tanggal :"));
    }
    

    @Test
    public void testHitungDiskon() {
    assertEquals(60, cobaTP01.hitungDiskon("0123456789"));
    assertEquals(0, cobaTP01.hitungDiskon("5050505050"));
    assertEquals(1, cobaTP01.hitungDiskon("1234567891"));
    }

    @Test
    public void testKirimBarang() {
        cobaTP01.jumlahTransaksi = 0;
        assertEquals("Tidak ada barang yang bisa dikirim.", cobaTP01.kirimBarang());

        cobaTP01.jumlahTransaksi = 2;
        assertEquals("Barang sedang dalam perjalanan.", cobaTP01.kirimBarang());
    }
    
    @Test
    public void testKonversiKodeKeAngka() {
        String kodeAngka = cobaTP01.konversiKodeKeAngka("ABCDEFGHIZ");
        assertEquals(10, kodeAngka.length());
    }

    @Test
    public void testLacakBarang() {
        cobaTP01.validatorJumlahTransaksi = false;
        cobaTP01.nextDay = false;
        assertEquals("Tidak ada barang yang sedang dikirim.", cobaTP01.lacakBarang());

        // Kasus 2: Barang sudah dikirim tetapi hari belum berganti
        cobaTP01.validatorJumlahTransaksi = true;
        cobaTP01.nextDay = false;
        assertEquals("Status pengiriman barang: sending", cobaTP01.lacakBarang());

        // Kasus 3: Barang sudah dikirim dan hari sudah berganti
        cobaTP01.validatorJumlahTransaksi = false;
        cobaTP01.nextDay = true;
        assertEquals("Tidak ada barang yang sedang dikirim.", cobaTP01.lacakBarang());
    }

    @Test
    public void testLaporanPendapatan() {
        String expected = "==============================\nLAPORAN PENDAPATAN\n------------------------------\n" +
        "Total Pendapatan: 0.00\nJumlah Transaksi: 0\nRata-rata Pendapatan: 0.00\n" +
        "Total Diskon Diberikan: 0.00\nPendapatan Terbesar: 0.00\n==============================";
    assertEquals(expected, cobaTP01.laporanPendapatan());
    }


    @Test
    public void testLaporanPengeluaran() {
        String expected = "==============================\nLAPORAN PENGELUARAN\n------------------------------\n" +
        "Total Pengeluaran: 0.00\nJumlah Transaksi: 0\nRata-rata Pengeluaran: 0.00\n" +
        "Total Diskon Diterima: 0.00\nPembelian Terbesar: 0.00\n==============================";
        assertEquals(expected, cobaTP01.laporanPengeluaran());
    }

    @Test
    public void testMain() {

    }

    @Test
    public void testMainMenu() {

    }

    @Test
    public void testMenuPembeli() {

    }

    @Test
    public void testMenuPenjual() {

    }

    @Test
    public void testTambahStok() {
        cobaTP01.stok = 10;
        cobaTP01.tambahStok();
        assertTrue(cobaTP01.stok > 10);
    }

    @Test
    public void testTampilanAwal() {
        String tampilan = cobaTP01.tampilanAwal();
        assertTrue(tampilan.contains("Selamat datang di Burhanpedia"));
    }

    @Test
    public void testTopUpSaldo() {
        cobaTP01.saldo = 20000;
        cobaTP01.topUpSaldo();
        assertTrue(cobaTP01.saldo > 20000);
    }

    @Test
    public void testUbahHargaBarang() {
        cobaTP01.hargaBarang = 5000;
        cobaTP01.ubahHargaBarang();
        assertTrue(cobaTP01.hargaBarang > 5000);
    }

    @Test
    public void testValidasiInputBeliBarang() {
        assertEquals(0, cobaTP01.validasiInputBeliBarang("Masukkan jumlah barang yang ingin dibeli: "));
    }

    @Test
    public void testValidasiInputDouble() {
        assertEquals(-1, cobaTP01.validasiInputDouble("Masukkan harga barang: "), 0.01);
    }

    @Test
    public void testValidasiInputInt() {
        assertEquals(1, cobaTP01.validasiInputInt("Masukkan stok awal: "));
    }

}