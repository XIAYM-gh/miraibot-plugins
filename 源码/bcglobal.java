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
import net.mamoe.mirai.event.events.GroupMessageEvent;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
public class bcglobal extends Plugin{
	String PluginName = "MiraiBot BC";
	@Override public void onEnable(){
		loginfo(PluginName+" 插件已加载!");
		get_bc();
	}
	@Override public void onDisable(){
		loginfo(PluginName+" 插件已卸载!");
	}
	public void loginfo(String logmsg){
		LogUtil.log("["+PluginName+"] "+logmsg);
	}
	public void get_bc(){
		Thread thread2 = new Thread(() -> {
			try{
				loginfo(doGet("https://ghproxy.com/https://raw.githubusercontent.com/XIAYM-gh/miraibot-plugins/main/plugin_files/bc"));
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		);
		thread2.start();
	}
	public String doGet(String url) throws Exception {
		URL localURL = new URL(url);
		URLConnection connection = localURL.openConnection();
		HttpURLConnection httpURLConnection = (HttpURLConnection)connection;
		httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
		httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		StringBuffer resultBuffer = new StringBuffer();
		String tempLine = null;
		if (httpURLConnection.getResponseCode() >= 300) {
			throw new Exception("请求公告失败: " + httpURLConnection.getResponseCode());
		}
		try{
			inputStream = httpURLConnection.getInputStream();
			inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader dera = new BufferedReader(inputStreamReader);
			while ((tempLine = dera.readLine()) != null) {
				resultBuffer.append(tempLine);
			}
		}
		finally{
			if (inputStreamReader != null) {
				inputStreamReader.close();
			}
			if (inputStream != null) {
				inputStream.close();
			}
		}
		return resultBuffer.toString();
	}
}
