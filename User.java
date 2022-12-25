public abstract class User { // abstract class kullanımı için oluşturduk.
    public String name;
    public String surname;
    public String no;

    abstract String getName();
    abstract String getSurname();
    abstract void setName(String name);
    abstract void setSurname(String surname);
    abstract String getNo();
    abstract void setNo(String no);
}
