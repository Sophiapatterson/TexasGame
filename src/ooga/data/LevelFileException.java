package ooga.data;

import java.io.IOException;

public class LevelFileException extends RuntimeException{

    /**
     * this constructor is the default for the LevelFileException class
     * @param cause is a concise explanation of what went wrong to be displayed
     */
    public LevelFileException(Throwable cause) {
        super("Your level configuration file couldn't be read", cause);
    }

    /**
     * this constructor allows a LevelFileException class to be built around another Throwable
     * @param message is a concise explanation of what went wrong to be displayed
     * @param cause is another exception that is thrown with the ConfigException for more context
     */
    public LevelFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
