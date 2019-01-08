package com.shengsiyuan.study.nio2;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class WatchServiceTest {
	public static void main(String[] args) throws IOException, InterruptedException {
		//监控文件变化
		WatchService watchService= FileSystems.getDefault().newWatchService();
		//注册监听
		Paths.get("").register(watchService, StandardWatchEventKinds.ENTRY_CREATE,StandardWatchEventKinds.ENTRY_MODIFY,StandardWatchEventKinds.ENTRY_DELETE);
		while(true){
			//获取下一个文件变化事件
			WatchKey key=watchService.take();
			for(WatchEvent<?> event:key.pollEvents()){
				System.out.println(event.context()+"文件"+"发生了"+event.kind()+"事件！");
			}
			//重设watckKey
			boolean valid=key.reset();
			if(!valid){
				break;
			}
		}
		
	}
}
