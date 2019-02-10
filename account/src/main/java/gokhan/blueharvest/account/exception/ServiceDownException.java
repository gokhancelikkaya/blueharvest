package gokhan.blueharvest.account.exception;

import gokhan.blueharvest.account.constant.ErrorMessage;

public class ServiceDownException  extends Exception {

	private static final long serialVersionUID = -6289949300369454672L;

	private final ErrorMessage errorMessage;

	public ServiceDownException(ErrorMessage errorMessage) {
		this.errorMessage = errorMessage;
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}
}