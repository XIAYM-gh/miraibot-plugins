package xiaym.miraibot;
import java.util.*;
import java.io.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;
import com.windowx.miraibot.utils.*;
import com.windowx.miraibot.plugin.*;
import com.windowx.miraibot.PluginMain;

public class autounload extends Plugin{
	String PluginName = "AutoUnload";

	@Override public void onEnable(){
	loginfo(PluginName+" 插件已加载!");
	CheckFiles();
	}

	@Override public void onDisable(){
	loginfo(PluginName+" 插件已卸载!");
	}

	@Override public boolean onCommand(String cmd){
	String[] cmd_args = cmd.split(" ");
	switch(cmd_args[0]){
		case "autounload":
		case "au":
			if(cmd_args.length>1){
				switch(cmd_args[1]){
					case "about":
						loginfo("Auto Unload v1.0 By XIAYM");
						return false;
					case "reload":
						loginfo("正在重新加载插件..");
						onFinished();
						onEnable();
						return false;
					default:
						loginfo("au reload 重新执行启动动作");
						loginfo("au about查看插件信息");
						return false;
				}
			}else{
				loginfo("au reload 重新执行启动动作");
				loginfo("au about 查看插件信息");
				return false;
			}
		default:
			return true;
	}
	}

	public void loginfo(String logmsg){
		LogUtil.log("["+PluginName+"] "+logmsg);
	}

	public void CheckFiles(){
		File propfile = new File("plugins/"+this.getName()+"/config.ini");
		if(!propfile.exists()){
			try{
			this.getConfig().setProperty("unloadplugins","demo,example");
			this.saveConfig();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		loginfo("文件检查完成，如果加载异常请删除配置文件.");
	}

	@Override public void onFinished(){
		loginfo("开始执行动作..");
		Thread threadunload22 = new Thread(() -> {
		try {
			Thread.sleep(3400);
		   } catch (InterruptedException e) {
		e.printStackTrace(); 
	        }
		try{
		String[] pvs = this.getConfig().getProperty("unloadplugins","demo,example").split(",");
		for(String itm:pvs){
			PluginMain.unloadPlugin(itm);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		loginfo("自动卸载完成.");
		});
		threadunload22.start();
	}
}
