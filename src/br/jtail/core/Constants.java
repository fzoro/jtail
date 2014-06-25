package br.jtail.core;

/**
 *
 * Constants
 *
 * @author fabiozoroastro@gmail.com
 */
public interface Constants {

    interface Def {

	String MAX_BUFFER_SUFIX = ".maxbuffer";
	Integer NEGATIVE_INDEX = -1;
	Integer K1 = 1024; // bytes
    }

    interface Conf {
	String CONTENT_TYPE_JSON = "application/json";
    }

    interface Actions {
	String GET_FILES = "get-files";
    }

    interface Messages {

	String CONF_EMPTY = "File conf.properties is empty or doesn't existe. Please, fix it.";
	String FILE_NOT_FOUND = "File {0} not found!";
	String PATH_NOT_FOUND = "Path not found!";
    }

    interface Admin {

	String SESSION_USER = "session-user";
    }
}
