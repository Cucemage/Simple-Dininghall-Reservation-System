import java.util.ArrayList;
import java.util.Date;

public class DiningHallReservation {
    private Date reservationDate; //Date classında oluşturulan obje
    private final ArrayList<Student> students = new ArrayList<>(); // o saate kayıtlı öğrenciler için arraylist

    public DiningHallReservation(Date reservationDate) { // reservation dateyi atmak için metod
        this.reservationDate = reservationDate;
    }

    public Date getReservationDate(){
        return reservationDate;
    } // reservation date için getter setter metod

    public void setReservationDate(Date reservationDate){
        this.reservationDate = reservationDate;
    }

    public void addStudent(Student student) { // öğrenci ekleme metodu
        if (this.getAvailableSeatCount() <= 0) { // yer olup olmadığının kontrolü
            System.out.println("Yeterli yer yok");
            return;
        }
        students.add(student); // öğrenciyi ekler
    }

    public ArrayList<Student> getStudents(){
        return this.students;
    }

    public int getAvailableSeatCount(){ // yer sayısından kayıtlı öğrenci sayısını çıkararak yer olup olmadığının öğrenilmesi
        int maxSeatCount = 10;
        return maxSeatCount - this.students.size();
    }

    public Boolean hasAnyAvailableSeat(){
        return this.getAvailableSeatCount() > 0;
    } //yer varlığının boolean olarak alınması
}
