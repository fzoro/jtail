package br.jtail.core;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public final class ErrorHandler {

    private ErrorHandler() {

    }

    public static void execute(HttpServletResponse response, Exception e)
	    throws IOException {
	PrintWriter out = response.getWriter();
	String message = String
		.format("{ \"error\" : \"%s\"} ", e.getMessage());
	out.print(message);
	out.flush();
    }

}
