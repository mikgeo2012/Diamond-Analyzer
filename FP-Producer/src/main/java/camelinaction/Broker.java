package camelinaction;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.StandardWatchEventKinds;
import java.util.ArrayList;
import java.util.List;

public class Broker {
	private List<AbstractObserver> observers = new ArrayList<AbstractObserver>();
	
	public void attach(AbstractObserver observer){
	      observers.add(observer);		
	   }

	public void notifyAllObservers(){
	      for (AbstractObserver observer : observers) {
	         observer.update();
	      }
	   }
	
	public void watchFolder() {
		System.out.println("Watching for new data...");
		System.out.println(new java.io.File("").getAbsolutePath());
		Path myDir = Paths.get(new java.io.File("").getAbsolutePath() + "/data");       

        try {
           WatchService watcher = myDir.getFileSystem().newWatchService();
           myDir.register(watcher, StandardWatchEventKinds.ENTRY_CREATE);

           WatchKey watckKey = watcher.take();

           List<WatchEvent<?>> events = watckKey.pollEvents();
           for (WatchEvent event : events) {
        	   notifyAllObservers();
            }
           
           watchFolder();
           
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
	}
	
	public static void main(String args[]) throws Exception {
		
		Broker b = new Broker();
		
		
		DataProducerEndpoint prod = new DataProducerEndpoint(b);
		InstitueRouter router = new InstitueRouter(b);
		
		prod.run();
		router.run();
		b.watchFolder();
	
	}

}
