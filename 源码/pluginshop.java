package xiaym.miraibot;
import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.nio.*;
import java.net.*;
import com.windowx.miraibot.utils.*;
import com.windowx.miraibot.plugin.*;
import com.windowx.miraibot.PluginMain;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.message.data.At;

public class pluginshop extends Plugin{
		String PluginName = "插件商店";

		@Override public void onEnable(){
		this.info(PluginName+" 已加载!");
		File mydir = new File("plugins/"+this.getName()+"/");
		if(!mydir.exists()){
				mydir.mkdirs();
		}
		threadget();
		}

		public void threadget(){
		Thread t3 = new Thread(() -> {
		try{
		Thread.sleep(500);
		this.info("正在获取新内容..");
		Download("https://xiaym-gh.github.io/miraibot-plugins/plugin_files/list.properties","plugins/"+this.getName()+"/","list.properties");
		this.info("下载语句结束.");
		}catch(Exception e){
		e.printStackTrace();
		}});
		t3.start();
		}

		@Override public void onDisable(){
		this.info(PluginName+" 已卸载!");
		}

		@Override public boolean onCommand(String cmd){
			String[] cmds = cmd.split(" ");
			if(cmds[0].equals("pluginshop")){
					if(cmds.length == 1){
					loghelp();
					return false;
					}else{
					if(cmds.length >= 2){
					switch(cmds[1]){
						case "update":
						 threadget();
						 return false;
						case "list":
						 getlist();
						 return false;
						case "download":
						 if(cmds.length >= 3){
							dlplug(cmds[2]);
						 }else{
							this.warn("缺少参数 <插件ID>");
						 }
						 return false;
						case "info":
						 if(cmds.length >= 3){
							viewplug(cmds[2]);
						 }else{
							this.warn("缺少参数 <插件ID>");
						 }return false;
						default:
						 loghelp();
						 return false;
						}
					}
			}
		}
		return true;
		}

		public void Download(String url_string, String FilePath, String FileName){
				try{
				InputStream in = new URL(url_string).openStream();
				Files.copy(in, Paths.get(FilePath+FileName), StandardCopyOption.REPLACE_EXISTING);
				}catch(Exception e){
				this.error("在下载 "+FileName+" 时发生错误! 错误详情:");
				e.printStackTrace();
				}
		}

		public void loghelp(){
			this.info("插件商店命令帮助 ====");
			this.info("pluginshop update");
			this.info(" - 更新插件列表");
			this.info("pluginshop list");
			this.info(" - 获取插件列表");
			this.info("pluginshop download <插件ID>");
			this.info(" - 下载某个插件");
			this.info("pluginshop info <插件ID>");
			this.info(" - 查询插件信息");
		}

		public void getlist(){
			File myf = new File("plugins/"+this.getName()+"/list.properties");
			if(myf.exists()){
				String[] plist = getProperties("plugins").split(",");
				for(String itm : plist){
					String iname = getProperties(itm+".name");
					String iauthor = getProperties(itm+".author");
					String iversion = getProperties(itm+".version");
					String itype = getProperties(itm+".type");
					this.info("ID: "+itm+" | 名称: "+iname+" | 作者: "+iauthor+" | 版本: "+iversion+" | 类别: "+itype);
				}
			}else{
				this.error("列表文件未找到! 请先update一次.");
			}
		}

		public void viewplug(String plugid){
			if(!getProperties(plugid+".does").equals("")){
				String idoes=getProperties(plugid+".does");
				String iauthor=getProperties(plugid+".author");
				String iname=getProperties(plugid+".name");
				this.info("插件信息 =====");
				this.info("插件名称: "+iname);
				this.info("插件作者: "+iauthor);
				this.info("插件描述: "+idoes);
			}else{
				this.warn("插件未找到.");
			}
		}

		public void dlplug(String plugid){
			if(!getProperties(plugid+".download").equals("")){
				this.info("正在下载插件 "+getProperties(plugid+".name")+"...");
				Download(getProperties(plugid+".download"),"plugins/",plugid+".jar");
				this.info("已保存为: "+plugid+".jar 可使用load命令加载.");
			}else{
				this.warn("未找到此插件ID 请确保输入正确.");
			}
		}

		public String getProperties(String proper){
			try{
				BufferedReader in = new BufferedReader(new InputStreamReader(new BufferedInputStream(new FileInputStream(new File("plugins/"+this.getName()+"/list.properties"))),"UTF-8"));
				Properties prop = new Properties();
				prop.load(in);
				return prop.getProperty(proper,"");	
			}catch(Exception e){
				e.printStackTrace();
			}
			return "";
		}

}
