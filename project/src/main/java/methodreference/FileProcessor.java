package methodreference;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * FileProcessor
 */
@FunctionalInterface
public interface FileProcessor {

	String process(BufferedReader br) throws IOException;
}