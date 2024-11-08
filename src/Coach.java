public class Coach extends Participant {
    public Coach(String name) {
        super(name);
    }

    @Override
    public void performAction() {
        System.out.println("Coach " + getName() + " asked for pause");
    }
}