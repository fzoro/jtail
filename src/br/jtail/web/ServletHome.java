package br.jtail.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.jtail.core.Constants;
import br.jtail.core.ErrorHandler;
import br.jtail.core.JTailException;
import br.jtail.core.Prop;

/**
 * Servlet implementation class ServletTail
 */
public class ServletHome extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public ServletHome()
	{
		//
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		final String action = request.getParameter("action");

		String jsonResult = "{}";

		try
		{

			if (Constants.Actions.GET_FILES.equals(action))
			{

				jsonResult = getFiles();
			}

			response.setContentType(Constants.Conf.CONTENT_TYPE_JSON);
			PrintWriter out = response.getWriter();
			out.print(jsonResult);
			out.flush();
		}
		catch (Exception e)
		{
			ErrorHandler.execute(response, e);
		}
	}

	private String getFiles() throws JTailException
	{
		Set<Object> keys = Prop.get().getProperties().keySet();

		StringBuffer buff = new StringBuffer();

		buff.append("[");
		for (Object key : keys)
		{
			buff.append("{");
			buff.append("\"name\":");
			buff.append("\"");
			buff.append(key);
			buff.append("\"");
			buff.append("}");
			buff.append(",");
		}

		buff.delete(buff.length() - 1, buff.length());
		buff.append("]");
		return buff.toString();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
}
