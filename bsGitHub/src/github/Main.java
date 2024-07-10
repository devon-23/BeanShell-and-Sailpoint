package github;

import bsh.Interpreter;
import java.io.FileReader;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		GitHubService service = new GitHubService();
		UserProcessor processor = new UserProcessor();
		Interpreter interpreter = new Interpreter();
		String scriptPath = "src/scripts/processUsers.bsh";
		
		try {
			List<String> usernames = service.getUsernamesWithRepo("github");
			List<String> usersWithRepos = processor.processUsers(usernames, service);
			
			interpreter.set("usersWithRepos", usersWithRepos);
			interpreter.eval(new FileReader(scriptPath));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
