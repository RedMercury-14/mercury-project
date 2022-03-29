package by.myproject.main.controller.command;

public class CommandFactory {
	private static volatile CommandFactory INSTANCE = null;

	private final DispositionCommand dispositionServise = new DispositionCommand();
	private final WorkerHasTruckCommand workerHasTruckServise = new WorkerHasTruckCommand();
	private final TruckCommand truckServise = new TruckCommand();
	private final OrderCommand orderServise = new OrderCommand();
	private final CommentDispositionCommand commentDispositionCommand = new CommentDispositionCommand();
	private final ControlCommand controlCommand = new ControlCommand();

	public static CommandFactory getCommandFactory() {
		CommandFactory factory = INSTANCE;
		if (factory == null) {
			synchronized (CommandFactory.class) {
				factory = INSTANCE;
				if (factory == null) {
					INSTANCE = factory = new CommandFactory();
				}
			}
		}
		return factory;
	}

	public DispositionCommand getDispositionServise() {
		return dispositionServise;
	}

	public WorkerHasTruckCommand getWorkerHasTruckServise() {
		return workerHasTruckServise;
	}

	public TruckCommand getTruckServise() {
		return truckServise;
	}

	public OrderCommand getOrderServise() {
		return orderServise;
	}

	public CommentDispositionCommand getCommentDispositionCommand() {
		return commentDispositionCommand;
	}

	public ControlCommand getControlCommand() {
		return controlCommand;
	}

}
