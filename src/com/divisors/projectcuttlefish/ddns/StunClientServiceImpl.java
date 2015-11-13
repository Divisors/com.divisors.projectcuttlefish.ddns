package com.divisors.projectcuttlefish.ddns;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StunClientServiceImpl implements StunClientService {

	private List<StunClient> clients = new ArrayList<>();
	
    public void registerDictionary(StunClient dictionary) {
    	clients.add(dictionary);
    }
    
    @Override
    public void unregisterDictionary(StunClient dictionary) {
    	clients.remove(dictionary);
    }
	
    public String[] getServers() {
    	List<String> languages = new ArrayList<>();
    	for (int i = 0; i < clients.size(); i++ ) {
			StunClient dictionary = (StunClient) clients.get(i);
			languages.add(dictionary.getServer());
		}
    	return (String[]) languages.toArray(new String[clients.size()]);
    }

	@Override
	public String getIp() throws IOException {
		Set<String> ips = clients.stream()
			.map((client)->{
				try {
					return client.getIp();
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			}).collect(Collectors.toSet());
		return ips.toString();
	}

}
