import java.io.*;

public class Main {
  static public void main(String argv[]) {    
     try {
      
	parser p;

	if(argv.length!= 0)//load input from file
	{
		p = new parser(new Lexer(new FileReader(argv[0])));
	}
	else //load input from interactive command line
	{
		p = new parser(new Lexer(new BufferedReader(new InputStreamReader(System.in))));
	}

	p.parse();

	      
    } catch (Exception e) {
          e.printStackTrace();
    }
  }
}


