package xiaym.miraibot;
import java.util.Random;
import com.windowx.miraibot.plugin.Plugin;
import com.windowx.miraibot.utils.LogUtil;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.message.data.At;

public class TouZi extends Plugin {
				String PluginName = "TouZi";
				@Override public void onEnable(){
				loginfo("骰子插件已加载!");
				}

				@Override public void onDisable(){
				loginfo("好家伙我直接正道的光");
				loginfo("拒绝赌博从我做起");
				loginfo("骰子插件爬了");
				}

				@Override public void onGroupMessage(GroupMessageEvent event){
				String msg = event.getMessage().contentToString();
				if(msg.equals("#qwq touzi")){
																				Random r = new Random();
																				int randomint = r.nextInt(6) + 1;
																				event.getGroup().sendMessage(new At(event.getSender().getId()) .plus("宁摇到了: "+randomint));
				}
				}

				public void loginfo(String logmsg){
								LogUtil.log("["+PluginName+"] "+logmsg);
				}
}
