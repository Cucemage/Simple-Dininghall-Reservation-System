import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        var registeredStudents = new ArrayList<Student>();//Kayıtlı öğrencilerin listesi
        var studentNo= new ArrayList<String>();//Kayıtlı öğrencilerin numaralarının tutulduğu arraylist
        registeredStudents.add(new Student("Hüseyin", "Yıldırım", "123"));
        var diningHallReservationList = createDiningHallReservations();
        var sdf = new SimpleDateFormat("HH:mm");// simple date formatın oluşturulması
        String isim,soyisim,numara;
        var returnAvailableSeat = false; // yeterli yer döngüsü
        var returnBeginning = false;// ana menü döngüsü
        do {
            System.out.println("1 - Giriş Yap");
            System.out.println("2 - Kaydol");
            System.out.println("3 - Çıkış Yap");

            Scanner sc = new Scanner(System.in);
            var inpt = sc.next();
            switch (inpt) {
                case "1" -> { // Giriş Yap
                    System.out.println("Numaranızı giriniz");
                    var inputStudentNo = sc.next();
                    if (registeredStudents.stream().anyMatch(x -> x.getNo().equals(inputStudentNo))) { // kayıtlı öğrencilerde girilen numara varsa devam etmesi için
                        System.out.println("Yemek Saati seçiniz");
                        int index = 1; // saatin yanındaki sıranın 1den başlaması için
                        for (var diningHallReservation : diningHallReservationList) { // saatleri sıralı olarak gösteren kod bloğu
                            System.out.println(index + " - " + sdf.format(diningHallReservation.getReservationDate()));
                            index++;
                        }

                        var inputDiningDateIndex = sc.nextInt(); // girilen saati alır.
                        while (inputDiningDateIndex > diningHallReservationList.size()) {// seçimin doğruluğunu kontrol eder
                            System.out.println("Lütfen geçerli bir saat seçiniz.");
                            inputDiningDateIndex = sc.nextInt();
                        }

                        var selectedReservation = diningHallReservationList.get(inputDiningDateIndex - 1);
                        do {
                            if (!selectedReservation.hasAnyAvailableSeat()) { // yeterli yerin olup olmadığını kontrol eder
                                System.out.println("Seçtiğiniz saat için yeterli yer yok.");
                                returnAvailableSeat = true; // koltuk döngüsünü başa alır
                            }
                        } while (returnAvailableSeat);

                        var student = registeredStudents
                                .stream()
                                .filter(x -> x.getNo().equals(inputStudentNo))// seçim yapan öğrencinin numarasını alır
                                .findFirst()
                                .get();
                        diningHallReservationList.get(inputDiningDateIndex - 1).addStudent(student); // o saatin listesine ekler
                        System.out.println(sdf.format(selectedReservation.getReservationDate()) + " saati için rezervasyonunuz başarılıyla kaydedilmiştir.");
                        returnBeginning = true;// seçimi ekrana yazdırır ve başlangıca döndürür.
                    } else {
                        System.out.println("Böyle bir öğrenci yok, lütfen kayıt olunuz.");
                        returnBeginning = true;// öğrenci yoksa kayıtsız
                    }
                }
                case "2" -> {// kayıt ol
                    System.out.println("Adınız: ");
                    isim = sc.next();
                    System.out.println("Soyadınız: ");
                    soyisim = sc.next();
                    System.out.println("Numaranız: "); // öğrencinin bilgilerini alır
                    numara = sc.next();
                    if (studentNo.contains(numara)) { // numara arrayinde karşılaştırma yaparak eğer varsa tekrar kaydı önler.
                        System.out.println("öğrenci zaten kayıtlı");
                        returnBeginning = true;
                    } else {
                        registeredStudents.add(new Student(isim, soyisim, numara));// öğrenci arrayine yeni öğrenciyi ekler
                        studentNo.add(numara);// numara arrayine yeni öğrenci numarasını ekler
                        System.out.println("kayıt Başarılı");
                        returnBeginning = true; // ana menüye döner
                    }
                }
                case "3" -> returnBeginning = false; // çıkış yapmak için oluşturulan case
                default -> { // istenen farklı bir değer girilmesini önler
                    System.out.println("Yanlış girdiniz.");
                    returnBeginning = true;
                }
            }
        } while (returnBeginning); // ana menü döngüsünün bitişi


    }


    private static ArrayList<DiningHallReservation> createDiningHallReservations(){ // saatleri oluşturan döngü
        var startDate = Calendar.getInstance(); // runtime environmentten gelen bilgileri alır
        startDate.set(Calendar.HOUR_OF_DAY, 12); // başlangıç saatini 24 saat biçiminde alır
        startDate.set(Calendar.MINUTE, 0);
        startDate.set(Calendar.SECOND, 0);

        var endDate = Calendar.getInstance(); // bitiş saatini 24 saat biçiminde alır
        endDate.set(Calendar.HOUR_OF_DAY, 13);
        endDate.set(Calendar.MINUTE, 30);
        endDate.set(Calendar.SECOND, 0);

        var diningHallReservationList = new ArrayList<DiningHallReservation>(); // saatleri tutan arraylist

        for(; startDate.before(endDate); startDate.add(Calendar.MINUTE, 15)){ //15 dakikalık parçalara bölen for döngüsü
            diningHallReservationList.add(new DiningHallReservation(startDate.getTime())); // saatleri ekleme
        }

        return diningHallReservationList;
    }
}