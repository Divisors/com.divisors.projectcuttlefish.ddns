package com.divisors.projectcuttlefish.ddns;

import java.util.Arrays;

import org.eclipse.osgi.framework.console.CommandInterpreter;
import org.eclipse.osgi.framework.console.CommandProvider;


// referenced in component.xml
public class ServiceComponent implements CommandProvider {
	
	private TurnClientService turnClient;
	
	public void _turn(CommandInterpreter ci) {
		String arg = ci.nextArgument();
		System.out.println("Foo"+arg);
		if (arg == null)
			ci.println("IP: " + turnClient.getIp());
		else if (arg.equalsIgnoreCase("servers"))
			ci.println(Arrays.toString(turnClient.getServers()));
	}

	public String getHelp() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("---My commands---\n\t");
		buffer.append("turn - get server\t");
		return buffer.toString();
	}
	
	public void setDictionary(TurnClientService d) {
		turnClient = d;
	}
	
	public void unsetDictionary(TurnClientService d) {
		turnClient = null;
	}
	
}