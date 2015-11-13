package com.divisors.projectcuttlefish.ddns;

import java.io.IOException;
import java.util.Arrays;

import org.eclipse.osgi.framework.console.CommandInterpreter;
import org.eclipse.osgi.framework.console.CommandProvider;


// referenced in component.xml
public class ServiceComponent implements CommandProvider {
	
	private StunClientService stunClient;
	
	public void _turn(CommandInterpreter ci) throws IOException {
		String arg = ci.nextArgument();
		System.out.println("Foo"+arg);
		if (arg == null)
			ci.println("IP: " + stunClient.getIp());
		else if (arg.equalsIgnoreCase("servers"))
			ci.println(Arrays.toString(stunClient.getServers()));
	}

	public String getHelp() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("---My commands---\n\t");
		buffer.append("turn - get server\t");
		return buffer.toString();
	}
	
	public void setDictionary(StunClientService d) {
		stunClient = d;
	}
	
	public void unsetDictionary(StunClientService d) {
		stunClient = null;
	}
	
}