package com.divisors.projectcuttlefish.ddns;

import java.io.IOException;

public interface StunClient {
	
    public String getServer();
    
    public String getIp() throws IOException;


}
