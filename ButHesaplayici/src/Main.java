import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main {

  public static String harfnotuHesapla(String isim, String soyisim, int odev, int sinav1, int sinav2) {
    String cikti = "";
    double toplamNot = (odev * 0.16) + (sinav1 * 0.24) + (sinav2 * 0.6);
    if (toplamNot >= 60 && sinav2 >= 50) {

      cikti = isim +" =>  *** TEBRİKLER *** Sinav Durumu= BAŞARİLİ Ortalamanız=" + toplamNot;
    } else if (toplamNot >= 60 && sinav2 < 50) {
      cikti = isim 
          + " =>  *** Sinav Durumu= BAŞARİSİZ(Final notu 50 altinda olmaması gerekiyor)Butten almaniz gereken en dusuk not=50. Ortalama=>"
          + toplamNot;
    } else {
      double but = ((60 - (0.24 * sinav1) - (0.16 * odev)) * 5 / 3);
      cikti = isim + " => Kaldınız.Butten almanız gereken en dusuk not= " + but;
    }
    return cikti;
  }

  public static void main(String[] args) {
    String[] isimler = { "Veli", "Ahmet", "Mehmet", "Abubekir", "Ömer", "Osman", "Ali" };
    String[] soyisimler = { "Ak", "Pak", "Tak", "Hak", "Sak", "Bak" };

    try {
      FileWriter writer = new FileWriter("OgrenciBilgi.txt");
      Random random = new Random();

      for (int i = 0; i < 50; i++) {
        String isim = isimler[random.nextInt(isimler.length)];
        String soyisim = soyisimler[random.nextInt(soyisimler.length)];
        String adSoyad = isim + " " + soyisim;

        int vize, finalNotu, odev;
        if (i < 25) {
          // öğrencilerin yarısına 50 puan ve üzeri not atama
          vize = random.nextInt(51) + 50;
          finalNotu = random.nextInt(51) + 50;
          odev = random.nextInt(51) + 50;
        } else {
          // Yarısına 50 puanın altında not atama
          vize = random.nextInt(51);
          finalNotu = random.nextInt(51);
          odev = random.nextInt(51);
        }

        String ogrenciBilgisi = adSoyad + "," + vize + "," + finalNotu + "," + odev + "\n";
        writer.write(ogrenciBilgisi);
      }

      writer.close();
      System.out.println("Ogrenci bilgileri basariyla olusturuldu");

      Scanner scanner = new Scanner(
          new BufferedReader(new FileReader("OgrenciBilgi.txt")));
      FileWriter writer2 = new FileWriter("ButunlemeDurumu.txt", true);

      while (scanner.hasNextLine()) {
        String OgrenciBilgi = scanner.nextLine();
        String OgrenciDizi[] = OgrenciBilgi.split(",");
        // Doğru sayıda eleman var mı kontrol et
        if (OgrenciDizi.length == 4) {
          int odev = Integer.parseInt(OgrenciDizi[3]);
          int sinav1 = Integer.parseInt(OgrenciDizi[1]);
          int sinav2 = Integer.parseInt(OgrenciDizi[2]);
          String cikti = harfnotuHesapla(OgrenciDizi[0], OgrenciDizi[1], odev, sinav1, sinav2);

          writer2.write(cikti); // Dosyaya çıktıyı yaz
          writer2.write("\n"); // Yeni satıra geç

        }
      }
      writer2.close(); // BufferedWriter'ı kapat
      scanner.close(); // Scanner'ı kapat

    } catch (IOException e) {
      System.out.println("Dosya işlemleri sırasında bir hata oluştu: " + e.getMessage());
    }
  }

  // @Test
  // void addition() {
  // assertEquals(2, 1 + 1);
  // }
}