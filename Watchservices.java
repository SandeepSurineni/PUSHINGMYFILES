import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.WatchService;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.util.HashMap;
import java.util.Map;

public class Watchservices {
	
	public static void main(String args[]) throws IOException {
		
		try (WatchService service = FileSystems.getDefault().newWatchService()){
			Map<WatchKey, Path> keyMap = new HashMap<>();
			Path path = Paths.get("C:\\Users\\ssurineni\\Downloads\\codevalidation");
			keyMap.put(path.register(service, 
					StandardWatchEventKinds.ENTRY_CREATE,
					StandardWatchEventKinds.ENTRY_DELETE,
					StandardWatchEventKinds.ENTRY_MODIFY),
					path);
			
			WatchKey watchKey;
			do {
				watchKey = service.take();
				Path eventDir = keyMap.get(watchKey);
				
				for( WatchEvent<?> event : watchKey.pollEvents()) {
					WatchEvent.Kind<?> kind = event.kind();
					Path eventPath = (Path)event.context();
					System.out.println(eventDir + ": " + kind + ":" + eventPath);
					
					
				}
                                                      System.exit(0);
			}while(watchKey.reset());
			
		}catch (Exception e) {
			
		}
		
		
	}

}
