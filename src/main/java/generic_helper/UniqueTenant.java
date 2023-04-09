package generic_helper;

public class UniqueTenant implements Identifiable {
    private int id;
    private String name;

    public UniqueTenant(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }


    public static void main(String[] args) {
        Set<UniqueTenant> personSet = new Set<>();

        UniqueTenant p1 = new UniqueTenant(1, "Alice");
        UniqueTenant p2 = new UniqueTenant(2, "Bob");
        UniqueTenant p3 = new UniqueTenant(1, "Charlie");

        personSet.addElement(p1);
        personSet.addElement(p2);
        personSet.addElement(p3);

        Set<UniqueTenant> personSetSecond = new Set<>();

        UniqueTenant psecond1 = new UniqueTenant(1, "Alice");
        UniqueTenant psecond2 = new UniqueTenant(2, "Bob");
        UniqueTenant psecond3 = new UniqueTenant(3, "Charlie");
        UniqueTenant psecond4 = new UniqueTenant(3, "CharlieDupe");


        personSetSecond.addElement(psecond1);
        personSetSecond.addElement(psecond2);
        personSetSecond.addElement(psecond3);
        personSetSecond.addElement(psecond4);
        personSetSecond.removeElement(1);


        System.out.println("-----IMPLEMENTATION OF GENERIC SET CLASS TO INTAKE ONLY DISTINCT IDs-----\n");
        System.out.println("Size Of set personSet A : " + personSet.size());
        System.out.println();
        System.out.println("Printing elements in set personSet A: ");
        personSet.display();
        System.out.println("Size Of set  personSet B  : " + personSetSecond.size());
        System.out.println();
        System.out.println("Printing elements in set personSet B: ");
        personSetSecond.display();
        System.out.println("Are both Sets matching : " + personSet.equals(personSetSecond));

    }
}
