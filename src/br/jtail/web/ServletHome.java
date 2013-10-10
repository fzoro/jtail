package br.jtail.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.jtail.core.Constants;
import br.jtail.core.ErrorHandler;
import br.jtail.core.JTailException;
import br.jtail.core.Prop;
import br.jtail.model.FileConf;
import br.jtail.util.NumberUtil;

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

		response.setStatus(HttpServletResponse.SC_ACCEPTED);
	}

	private String getFiles() throws JTailException
	{

		// return new JSONStringer().object().key("fileName").value(this.fileName).key("off").value(this.bytes).key("value").value(this.value).endObject().toString();
		Collection<FileConf> files = FileConf.getAllFileConfs();
		return null;
		// return json files with: name, path, maxbuffer
		// for (Object key : keys)
		// {
		//
		// if (!key.toString().endsWith(Constants.Def.MAX_BUFFER_SUFIX))
		// {
		// buff.append("{");
		// buff.append("\"name\":");
		// buff.append("\"");
		// buff.append(key);
		// buff.append("\"");
		// buff.append("}");
		// buff.append(",");
		// }
		// }
		//
		// buff.delete(buff.length() - 1, buff.length());
		// buff.append("]");
		// return buff.toString();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
	}
}
