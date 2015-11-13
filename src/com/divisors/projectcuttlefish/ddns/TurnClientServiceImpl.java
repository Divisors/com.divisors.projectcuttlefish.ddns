package com.divisors.projectcuttlefish.ddns;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TurnClientServiceImpl implements TurnClientService {

	private List<TurnClient> clients = new ArrayList<>();
	
    public void registerDictionary(TurnClient dictionary) {
    	clients.add(dictionary);
    }
    
    @Override
    public void unregisterDictionary(TurnClient dictionary) {
    	clients.remove(dictionary);
    }
	
    public String[] getServers() {
    	List<String> languages = new ArrayList<>();
    	for (int i = 0; i < clients.size(); i++ ) {
			TurnClient dictionary = (TurnClient) clients.get(i);
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
