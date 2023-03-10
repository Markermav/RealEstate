import controller.Controller;
import db.AccountingSystem;
import view.View;

public class App {
    public static void main(String[] args) throws Exception {
		AccountingSystem model = new AccountingSystem();
    	View view = new View();
		model.addObserver(view);
        Controller controller = new Controller(model,view);
        controller.run();
    }
}
