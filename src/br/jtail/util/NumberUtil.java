package br.jtail.util;

public final class NumberUtil
{

	private NumberUtil()
	{

	}

	/**
	 * return null if error occur
	 *
	 * @param s
	 * @return
	 *
	 * @author fabio.zoroastro - <code>fabio.zoroastro@sysmap.com.br</code>
	 */
	public static Integer toInteger(String s)
	{

		Integer i = null;

		try
		{
			i = Integer.valueOf(s);
		}
		catch (Exception e)
		{
			// do nothing
		}
		return i;
	}
}
