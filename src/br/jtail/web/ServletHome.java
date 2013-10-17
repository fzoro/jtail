package br.jtail.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONStringer;
import org.json.JSONWriter;

import br.jtail.core.Constants;
import br.jtail.core.ErrorHandler;
import br.jtail.core.JTailException;
import br.jtail.model.FileConf;

/**
 * Servlet implementation class ServletTail
 */
public class ServletHome extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public ServletHome() {
		//
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		final String action = request.getParameter("action");

		String jsonResult = "{}";

		try {

			if (Constants.Actions.GET_FILES.equals(action)) {

				jsonResult = getFiles();
			}

			response.setContentType(Constants.Conf.CONTENT_TYPE_JSON);
			PrintWriter out = response.getWriter();
			out.print(jsonResult);
			out.flush();
		} catch (Exception e) {
			ErrorHandler.execute(response, e);
		}

		response.setStatus(HttpServletResponse.SC_ACCEPTED);
	}

	/**
	 * 
	 * @return
	 * @throws JTailException
	 */
	private String getFiles() throws JTailException {

		String result = null;
		Map<String, FileConf> files = FileConf.getAllFileConfs();

		if (files != null && !files.isEmpty()) {

			JSONWriter str = new JSONStringer();
			str = str.array();

			for (FileConf conf : files.values()) {
				str = str.object();
				str.key("name");
				str.value(conf.getName());
				str.key("path");
				str.value(conf.getPath());
				str.key("maxBuffer");
				str.value(conf.getMaxBuffer());
				str = str.endObject();
			}
			result = str.endArray().toString();
		}
		return result;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
	}
}
