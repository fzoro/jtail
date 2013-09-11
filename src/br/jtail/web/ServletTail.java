package br.jtail.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.jtail.core.Constants;
import br.jtail.core.JTailException;
import br.jtail.core.Prop;
import br.jtail.core.Scanner;
import br.jtail.core.Scanner.DataFile;

/**
 * Servlet implementation class ServletTail
 */
public class ServletTail extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public ServletTail()
	{
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		response.setContentType(Constants.Conf.CONTENT_TYPE_JSON);

		try
		{

			Set<Object> keySet = Prop.get().getProperties().keySet();

			if (keySet == null || keySet.isEmpty())
			{
				throw new JTailException(Constants.Messages.CONF_EMPTY);

			}

			String fileName = request.getParameter("fileName");
			String filePath = request.getParameter("filePath");
			String off = request.getParameter("off");
			int intOff;
			try
			{
				intOff = Integer.valueOf(off);
			}
			catch (Exception e)
			{
				intOff = 0;
			}

			DataFile dataFile = Scanner.readToDataFile(fileName, filePath, intOff);

			PrintWriter out = response.getWriter();
			out.print(dataFile);
			out.flush();
		}
		catch (Exception e)
		{
			errorHandler(response, e);
		}
	}

	private void errorHandler(HttpServletResponse response, Exception e) throws IOException
	{

		PrintWriter out = response.getWriter();
		String message = String.format("{ \"error\" : \"%s\"} ", e.getMessage());
		out.print(message);
		out.flush();

	}


	public static void main(String[] args)
	{
		DataFile dataFile = Scanner.readToDataFile("System", "c://system.log", 0);
		System.out.println(dataFile);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
}
