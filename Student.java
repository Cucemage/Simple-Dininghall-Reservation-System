public class Student extends User {

    public Student(String name, String surname, String no) { // constructor metot
        this.name = name;
        this.surname = surname;
        this.no = no;
    }

    public String getName() {
        return name;
    }  // getter setter metodlar

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }
}
