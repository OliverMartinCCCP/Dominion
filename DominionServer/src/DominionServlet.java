import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
//import Model.*;


public class DominionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private WriteToMySql wtms = new WriteToMySql();
	
    public DominionServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		
		Writer writer = response.getWriter();
		
		String operation;
		
		operation = request.getParameter("operation");
		
		switch(operation)
		{
		case "initialize":
			String playerName1 = request.getParameter("name1");
			String playerName2 = request.getParameter("name2");
			writer.append("{\"status\":\"OK\", \"name1\":\"");
			writer.append(playerName1);
			writer.append("\",");
			writer.append(" \"name2\":\"");
			writer.append(playerName2);
			writer.append("\"}");
			
			/*
			wtms.postPlayerName(playerName1);
			wtms.postPlayerName(playerName2);
			*/
			
			break;
		
		case "drawCard":
			int cardNumber = Integer.parseInt(request.getParameter("cardNumber"));
			writer.append("{\"status\":\"OK\",\"cardNumber:\"");
			writer.append(Integer.toString(cardNumber));
			writer.append("\"}");
			break;
		default:
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}