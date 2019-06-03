package io;

import java.io.IOException;

/**
 * Extends the java.io.IOException class
 * only in order to not to be forced to refrence external
 * libraries in the main code
 */
public class MyIOException extends IOException {

    public MyIOException(IOException e) {
        this.setStackTrace(e.getStackTrace());
    }
}
