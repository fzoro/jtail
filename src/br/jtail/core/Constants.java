package br.jtail.core;

public interface Constants
{

	interface Conf
	{
		String CONTENT_TYPE_JSON = "application/json";
	}

	interface Actions
	{
		String GET_FILES = "get-files";
	}

	interface Messages
	{

		String CONF_EMPTY = "File conf.properties is empty or doesn't existe. Please, fix it.";

		String FILE_NOT_FOUND = "File {0} not found!";
	}
}
