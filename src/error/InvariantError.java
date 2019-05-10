package error;

public class InvariantError extends ContractError {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6542869642599432481L;
	public InvariantError (String message) {
		super("Invariant error "+message);
	}

}
