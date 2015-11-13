package com.divisors.projectcuttlefish.ddns;

import java.io.IOException;

public interface TurnClientService {
	
    /**
     * Register a turn client
     * 
     * @param dictionary the dictionary to be added.
     */
    public void registerDictionary(TurnClient dictionary);
    
    /**
     * Remove a turn client
     * 
     * @param dictionary the dictionary to be removed.
     */
    public void unregisterDictionary(TurnClient dictionary);
	
    public String getIp() throws IOException;
    
    /**
     * Return the list of languages supported
     * 
     * @return the list of languages in the dictionary
     */
    public String[] getServers();


}
