package com.amazingpizza.api.exception;

/**
 * Exception for Internal Server errors.
 */
public class InternalServerErrorException extends Exception {

  /**
   * Serial version UID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The error message.
   */
  private final String errorMessage;

  /**
   * Instantiate the exception with a given error message.
   * @param errorMessage the error message.
   */
  public InternalServerErrorException(final String errorMessage) {
    super();
    this.errorMessage = errorMessage;
  }

  /**
   * Instantiate the exception with a default error message.
   */
  public InternalServerErrorException() {
    this.errorMessage = "Something went wrong on the server side.";
  }

  /**
   * Gets the error message.
   * @return the error message.
   */
  public String getErrorMessage() {
    return errorMessage;
  }

}