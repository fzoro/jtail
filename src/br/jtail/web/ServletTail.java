package br.jtail.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.jtail.core.Constants;
import br.jtail.core.ErrorHandler;
import br.jtail.core.JTailException;
import br.jtail.core.Prop;
import br.jtail.core.Scanner;
import br.jtail.core.Scanner.DataFile;

/**
 * Servlet implementation class ServletTail
 */
public class ServletTail extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public ServletTail() {
	// TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {

	response.setContentType(Constants.Conf.CONTENT_TYPE_JSON);

	try {

	    String fileName = request.getParameter("fileName");
	    String group = request.getParameter("group");

	    String filePath = getFilePath(fileName, group);
	    String off = request.getParameter("off");
	    int intOff;
	    try {
		intOff = Integer.valueOf(off);
	    } catch (Exception e) {
		intOff = 0;
	    }

	    DataFile dataFile = Scanner.readToDataFile(fileName, filePath,
		    intOff);

	    PrintWriter out = response.getWriter();
	    out.print(dataFile);
	    out.flush();
	} catch (Exception e) {
	    ErrorHandler.execute(response, e);
	}
	response.setStatus(HttpServletResponse.SC_ACCEPTED);
    }

    /**
     * Returns filepath from filename or filename at group
     *
     * @return
     * @throws JTailException
     */
    private String getFilePath(final String fileName, final String group)
	    throws JTailException {

	String filePath = null;
	if (group != null && group.trim().length() > 0) {
	    final String folderGroup = Prop.get().getProperties()
		    .getProperty(group);
	    // to usefull in all operation system, catch the real path
	    // filePath = folderGroup.concat(fileName);
	    File tmpFile = new File(folderGroup);
	    if (tmpFile != null && tmpFile.isDirectory()) {

		for (File tmpRealFile : tmpFile.listFiles()) {
		    if (tmpRealFile.getName().equals(fileName)) {
			filePath = tmpRealFile.getPath();
		    }

		}
	    }
	}

	else {
	    filePath = Prop.get().getProperties().getProperty(fileName);
	}

	if (filePath == null) {
	    throw new JTailException(Constants.Messages.PATH_NOT_FOUND);
	}
	return filePath;
    }

    public static void main(String[] args) throws JTailException {
	DataFile dataFile = Scanner.readToDataFile("application",
		"c://application.log", 1024);

	System.out.println(dataFile);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	String name = request.getParameter("name");
	String path = request.getParameter("path");
	String strMaxBuffer = request.getParameter("maxBuffer");
	int intMaxBuffer = -1;

	try {
	    intMaxBuffer = Integer.valueOf(strMaxBuffer);
	} catch (Exception e) {
	    // do nothing
	}

	try {
	    Prop.get().getProperties().put(name, path);
	    if (intMaxBuffer > -1) {
		String nameMaxBuffer = name + Constants.Def.MAX_BUFFER_SUFIX;
		Prop.get().getProperties().put(nameMaxBuffer, intMaxBuffer);
	    }
	} catch (JTailException e) {
	    ErrorHandler.execute(response, e);
	}

	response.setStatus(HttpServletResponse.SC_CREATED);
    }
}
