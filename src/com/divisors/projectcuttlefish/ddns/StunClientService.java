package com.divisors.projectcuttlefish.ddns;

import java.io.IOException;

public interface StunClientService {
	
    /**
     * Register a turn client
     * 
     * @param dictionary the dictionary to be added.
     */
    public void registerDictionary(StunClient dictionary);
    
    /**
     * Remove a turn client
     * 
     * @param dictionary the dictionary to be removed.
     */
    public void unregisterDictionary(StunClient dictionary);
	
    public String getIp() throws IOException;
    
    /**
     * Return the list of languages supported
     * 
     * @return the list of languages in the dictionary
     */
    public String[] getServers();


}
