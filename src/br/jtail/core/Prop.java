package br.jtail.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class Prop
{

	private static Prop instance;
	private Properties properties;

	private static Logger log = Logger.getLogger(Prop.class.getName());

	public static Prop get() throws JTailException
	{
		if (instance == null)
		{
			instance = new Prop();
		}

		return instance;
	}

	private Prop() throws JTailException
	{
		load();
	}

	public Properties getProperties()
	{
		return this.properties;
	}

	/**
	 * Carrega as propriedades de configuracao oriundas do arquivo MCA.properties;
	 *
	 * @return
	 * @throws IOException
	 */
	public void load() throws JTailException
	{
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		InputStream input = null;

		try
		{
			input = cl.getResourceAsStream("conf.properties");
			properties = new Properties();
			properties.load(input);

		}
		catch (Exception e)
		{
			throw new JTailException(e);
		}
		finally
		{
			if (input != null)
			{
				try
				{
					input.close();
				}
				catch (IOException e)
				{
					throw new JTailException(e);
				}
			}
		}
	}

}
