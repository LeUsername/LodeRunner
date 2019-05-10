package error;

public class PreCondError extends ContractError {

	/**
	 * 
	 */
	private static final long serialVersionUID = -107507616849401537L;
	public PreCondError (String message) {
		super("PreCond error "+message);
	}

}
