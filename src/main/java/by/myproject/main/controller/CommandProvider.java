package by.myproject.main.controller;

import java.util.HashMap;
import java.util.Map;

import by.myproject.main.controller.command.CommandFactory;
import by.myproject.main.controller.command.ControlCommand;
import by.myproject.main.controller.command.Logination;
import by.myproject.main.controller.command.Registraion;
import by.myproject.main.controller.command.Setting;
import by.myproject.main.controller.impl.Exit;
import by.myproject.main.controller.impl.GoToAddCarPage;
import by.myproject.main.controller.impl.GoToAddCrewPage;
import by.myproject.main.controller.impl.GoToAddOrderPage;
import by.myproject.main.controller.impl.GoToControlPage;
import by.myproject.main.controller.impl.GoToDescriptionPage;
import by.myproject.main.controller.impl.GoToDispositionPage;
import by.myproject.main.controller.impl.GoToEditCarPage;
import by.myproject.main.controller.impl.GoToEditControlPage;
import by.myproject.main.controller.impl.GoToEditCrewPage;
import by.myproject.main.controller.impl.GoToEditOrderPage;
import by.myproject.main.controller.impl.GoToErrorPage;
import by.myproject.main.controller.impl.GoToLoginationPage;
import by.myproject.main.controller.impl.GoToMainPage;
import by.myproject.main.controller.impl.GoToOrderListPage;
import by.myproject.main.controller.impl.GoToRegistrationPage;
import by.myproject.main.controller.impl.GoToSettingPage;
import by.myproject.main.controller.impl.GoToTruckListPage;
import by.myproject.main.controller.impl.GoToUserPage;
import by.myproject.main.controller.impl.GoToUserPageProof;
import by.myproject.main.controller.impl.GoToWayBillPage;
import by.myproject.main.controller.impl.GoToWelcomePage;
import by.myproject.main.controller.impl.LocalCommand;



public class CommandProvider {
	
	private final Map<String, Command> commands = new HashMap<String, Command>();
	private CommandFactory commandFactory = CommandFactory.getCommandFactory();

	public CommandProvider() {		
		commands.put("GO_TO_MAIN_PAGE", new GoToMainPage());
		commands.put("GO_TO_LOGINATION_PAGE", new GoToLoginationPage());
		commands.put("GO_TO_USER_PAGE", new GoToUserPage());
		commands.put("GO_TO_REGISTRATION_PAGE", new GoToRegistrationPage());
		commands.put("GO_TO_WELCOME_PAGE", new GoToWelcomePage());
		commands.put("GO_TO_USER_PAGE_PROOF", new GoToUserPageProof());
		commands.put("GO_TO_SETTING_PAGE", new GoToSettingPage());
		commands.put("GO_TO_TRUCKLIST_PAGE", new GoToTruckListPage());
		commands.put("GO_TO_ORDERLIST_PAGE", new GoToOrderListPage());
		commands.put("GO_TO_WAYBILL_PAGE", new GoToWayBillPage());
		commands.put("GO_TO_ADDORDER_PAGE", new GoToAddOrderPage());		
		commands.put("GO_TO_EDITORDER_PAGE", new GoToEditOrderPage());
		commands.put("GO_TO_ADDTRUCK_PAGE", new GoToAddCarPage());
		commands.put("GO_TO_EDITTRUCK_PAGE", new GoToEditCarPage());
		commands.put("GO_TO_NEWCREW_PAGE", new GoToAddCrewPage());
		commands.put("GO_TO_EDITCREW_PAGE", new GoToEditCrewPage());
		commands.put("GO_TO_ERROR_PAGE", new GoToErrorPage());
		commands.put("GO_TO_DISPOSITION_PAGE", new GoToDispositionPage());
		commands.put("GO_TO_CONTROL_PAGE", new GoToControlPage());
		commands.put("GO_TO_EDITCONTROLL_PAGE", new GoToEditControlPage()); 
		
		
		commands.put("EXIT", new Exit());	
		commands.put("local", new LocalCommand());		
		
		commands.put("logination", new Logination());
		commands.put("registration", new Registraion());
		commands.put("setting", new Setting());	
		commands.put("addTruck", commandFactory.getTruckServise());
		
		
		commands.put("editTruck", commandFactory.getTruckServise());
		commands.put("delTruck", commandFactory.getTruckServise());
		commands.put("addOrder", commandFactory.getOrderServise()); 
		commands.put("proof", commandFactory.getOrderServise());
		commands.put("editOrder", commandFactory.getOrderServise());
		commands.put("editCarInOrder", commandFactory.getOrderServise());
		commands.put("delOrder", commandFactory.getOrderServise());
		commands.put("DISPOSITION_COMMAND", commandFactory.getDispositionServise());
		commands.put("newCrew", commandFactory.getWorkerHasTruckServise());
		commands.put("editCrew", commandFactory.getWorkerHasTruckServise());
		commands.put("delCrew", commandFactory.getWorkerHasTruckServise());	
		commands.put("addComment", commandFactory.getCommentDispositionCommand());
		commands.put("editControll", commandFactory.getControlCommand()); 
		commands.put("addControll", commandFactory.getControlCommand()); 
		commands.put("delControll", commandFactory.getControlCommand());
		commands.put("GO_TO_ADDCONTROLL_PAGE", commandFactory.getControlCommand()); 
		
		
		
		
	}
	
	public final Command getCommand(String commandName) {
		Command command = commands.get(commandName);
		return command;
	}

}
