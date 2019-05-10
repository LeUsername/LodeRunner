package error;

public class PostCondError extends ContractError {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4220939273832585355L;
	public PostCondError (String message) {
		super("PostCond error "+message);
	}
}
