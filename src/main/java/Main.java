public class Main {
    public static void main(String[] args) {
        System.out.println(new PhoneDAO().findAll());
        System.out.println(new PhoneDAO().findEntityById(2));
        System.out.println(new PhoneDAO().delete(3));
        System.out.println(new PhoneDAO().create(new Phone(6,"Masha", "80294451543")));
        System.out.println(new PhoneDAO().findAll());
        System.out.println(new PhoneDAO().update(new Phone(6,"Masha", "80294457543")));
        System.out.println(new PhoneDAO().findAll());
        System.out.println(new PhoneDAO().delete(new Phone(6,"Masha", "80294457543")));
        System.out.println(new PhoneDAO().findAll());
    }
}
