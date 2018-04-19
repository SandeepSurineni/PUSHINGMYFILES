package com.learn.temp;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.HashMap;
import java.util.Map;

public class FileWatchService {
	
	
	
    private final WatchService watcher;
    private final Map<WatchKey,Path> keys;
    
    public FileWatchService(Path dir) throws IOException {
        this.watcher = FileSystems.getDefault().newWatchService();
        this.keys = new HashMap<WatchKey,Path>();

        register(dir);
        System.out.println("Registered directory : "+ dir.getFileName());
    }
    
    void processEvents() {
        for (;;) {
            // wait for key to be signaled
            WatchKey key;
            try {
                key = watcher.take();
            } catch (InterruptedException x) {
                return;
            }
 
            Path dir = keys.get(key);
            if (dir == null) {
                System.err.println("WatchKey not recognized!!");
                continue;
            }
 
            for (WatchEvent<?> event: key.pollEvents()) {
                WatchEvent.Kind kind = event.kind();
 
                // TBD - provide example of how OVERFLOW event is handled
                if (kind == OVERFLOW) {
                    continue;
                }
 
                // Context for directory entry event is the file name of entry
                @SuppressWarnings("unchecked")
				WatchEvent<Path> ev = (WatchEvent<Path>)event;
                
                Path name = ev.context();
                Path child = dir.resolve(name);

                // print out event
                if(null != child && (child.getFileName().toString().endsWith("zip") || child.getFileName().toString().endsWith("tar"))) {
                	System.out.println("File Copied : "+ child);
                	// Create a new Thread here and pass this fileName to find it's Checksum
                }
            }
 
            // Reset key 
            boolean valid = key.reset();
            
            // Remove key if reset fails i.e; may be directory itself is longer accessible
            if (!valid) {
                keys.remove(key);
 
                // If there are no directories to monitor, exist the program 
                if (keys.isEmpty()) {
                    break;
                }
            }
        }
    }

    private void register(Path dir) throws IOException {
        WatchKey key = dir.register(watcher, ENTRY_CREATE);
        keys.put(key, dir);
    }

    static void usage() {
        System.err.println("usage: java WatchDir dirFullPath");
        System.exit(-1);
    }
 
    
    /**
     *  To run this class and monitor a directory continuously add "&" at end of run command. i.e;
     *  java FileWatchService <DirecotyToWatch> &
     */  
	public static void main(String[] args) {
		
		// Remove this line and pass as command line argument
		args[0]="C:\\Users\\BhanuChaitanya\\Downloads";
		System.out.println("length "+ args.length);
		if (args.length != 1)
            usage();
		
		try {
			FileWatchService service = new FileWatchService(Paths.get(args[0]));
			service.processEvents();	
		} catch (IOException e) {
			e.printStackTrace();
		}				
	}
}