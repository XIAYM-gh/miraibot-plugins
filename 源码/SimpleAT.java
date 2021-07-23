package xiaym.miraibot;
import com.windowx.miraibot.plugin.Plugin;
import com.windowx.miraibot.utils.LogUtil;
import com.windowx.miraibot.PluginMain;
import net.mamoe.mirai.message.data.At;
import net.mamoe.mirai.message.code.MiraiCode;
public class SimpleAT extends Plugin {
				public String PluginName = "SimpleAT";
				@Override public void onEnable(){
				loginfo(PluginName+" 插件已加载!");
				}

				@Override public void onDisable(){
				loginfo("正在卸载 "+PluginName+" ...");
				}

				@Override public boolean onCommand(String cmd){
				String[] cmd_args = cmd.split(" ");

				switch (cmd_args[0]) {
								case "simpleat":
								case "sa":
												if(cmd_args.length>1){
												switch (cmd_args[1]){
																case "status":
																				loginfo("插件一切正常.");
																				loginfo("SimpleAT v1.0 (UNSTABLE) By XIAYM");
																				return false;
																case "at":
																				if(cmd_args.length>2){
																								PluginMain.group.sendMessage(MiraiCode.deserializeMiraiCode("[mirai:at:"+cmd_args[2]+"]"));
																								return false;
																				}else{
																								loginfo("用法: simpleat at <qq号>");
																								return false;
																				}
																case "idat":
																				if(cmd_args.length>2){
																								PluginMain.group.sendMessage(new At(PluginMain.getMessageById(Integer.parseInt(String.valueOf(cmd_args[2]))).getFromId()));
																								return false;
																				}else{
																								loginfo("用法: simpleat idat <消息id>");
																								return false;
																				}
																default:
																				loghelp();
																				return false;
												}
												}else{
																loghelp();
																return false;
												}
												
								default:
												return true;	
				}

				}

				public void loginfo(String logmsg){
								LogUtil.log("["+PluginName+"] "+logmsg);
				}

				public void loghelp(){
								loginfo("== SimpleAt帮助 ==");
								loginfo("查看插件状态:");
								loginfo("simpleat status");
								loginfo("根据qq号AT 某人 :");
								loginfo("simpleat at <qq号>");
								loginfo("根据消息id AT某人 :");
								loginfo("simpleat idat <消息ID>");
								loginfo("* simpleat可以简写为sa *");
				}
}
