package com.divisors.projectcuttlefish.ddns;

import java.util.Hashtable;

import org.eclipse.osgi.framework.console.CommandProvider;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

public class Activator implements BundleActivator, ServiceListener {

	private TurnClientService service;
	private ServiceComponent cmp;
	private ServiceTracker<?, ?> dictionaryServiceTracker;
	private BundleContext fContext;
	protected static String[][] servers = new String[][]{
		new String[]{"turn:numb.viagenie.ca","muazkh","webrtc@live.com"},
		new String[]{"turn:192.158.29.39:3478?transport=udp","JZEOEt2V3Qb0y27GRntt2u2PAYA=","28224511:1379330808"},
		new String[]{"turn:192.158.29.39:3478?transport=tcp","JZEOEt2V3Qb0y27GRntt2u2PAYA=","28224511:1379330808"}
	};
	
	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		fContext = context;
		service = new TurnClientServiceImpl();
		for (String[] server : servers)
			service.registerDictionary(new TurnClientImpl(server[0],server[2],server[1]));
		
		Hashtable<String, ?> props = new Hashtable<String, Object>();
		// register the service
		context.registerService(TurnClientService.class.getName(), service, props);

		// create a tracker and track the service
		dictionaryServiceTracker = new ServiceTracker<Object, Object>(context, TurnClientService.class.getName(), null);
		dictionaryServiceTracker.open();

		// have a service listener to implement the whiteboard pattern
	    fContext.addServiceListener(this, "(objectclass=" + TurnClient.class.getName() + ")");
		
		// grab the service
		service = (TurnClientService) dictionaryServiceTracker.getService();
		cmp = new ServiceComponent();
		context.registerService(CommandProvider.class.getName(), cmp, null);
		cmp.setDictionary(service);
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		// close the service tracker
		dictionaryServiceTracker.close();
		dictionaryServiceTracker = null;
		cmp = null;
		service = null;
		fContext = null;
	}

	public void serviceChanged(ServiceEvent ev) {
		ServiceReference<?> sr = ev.getServiceReference();
		switch(ev.getType()) {
			case ServiceEvent.REGISTERED:
			{
				TurnClient dictionary = (TurnClient) fContext.getService(sr);
				service.registerDictionary(dictionary);
			}
			break;
			case ServiceEvent.UNREGISTERING:
			{
				TurnClient dictionary = (TurnClient) fContext.getService(sr);
				service.unregisterDictionary(dictionary);
			}
			break;
		}
	}


}
