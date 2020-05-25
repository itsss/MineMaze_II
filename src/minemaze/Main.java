package minemaze;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements CommandExecutor{

    @Override
    public void onEnable() {
        System.out.println("플러그인이 활성화되었습니다 ");
        getCommand("test").setExecutor(this);
    }

    public static int left = 60 * 3 + 1;
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if(!(sender instanceof Player)) {
            System.out.println("버킷내에서는 명령어 사용이 불가능합니다.");
            return false;
        }
        else
        {
            if(cmd.getName().equalsIgnoreCase("test"))
            {
                if(0 < args.length)
                {
                    if(args[0].equalsIgnoreCase("start")) {
                        Player player = (Player) sender;
                        World world = player.getWorld();
                        sender.sendMessage(ChatColor.AQUA + "GAME START!!");
                        sender.sendMessage(ChatColor.RED+"======== MineMaze Tutorial ========");
                        sender.sendMessage(ChatColor.AQUA+"No mouse click or keyboard click!");
                        sender.sendMessage(ChatColor.YELLOW+"마우스 클릭과 키보드 사용은 게임 내에서 금지되어 있습니다.");
                        //sender.sendMessage(ChatColor.YELLOW+"マウスクリックやキーボードを使用できません");
                        sender.sendMessage(ChatColor.AQUA + "just focus to move steve front!");
                        //sender.sendMessage(ChatColor.YELLOW+"集中してスティーブを前に動かしてください");
                        sender.sendMessage(ChatColor.YELLOW+"집중을 하는 경우, 뇌파 장비가 이를 인식하여 마인크래프트의 스티브를 앞으로 움직입니다.");

                        player.teleport(new Location(world, 213, 4, -1865));

                        new Runnable()
                        {
                            public int taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(Main.class), this, 0L, 20L);
                            @Override
                            public void run()
                            {
                                double x = player.getLocation().getX();
                                double y = player.getLocation().getY();
                                double z = player.getLocation().getZ();
                                World w = player.getWorld();

                                if((int)x == 207 && (int)y == 4 && (int)z == -1846) //25%
                                {
                                    sender.sendMessage(ChatColor.AQUA + "25% Complete!");
                                    w.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_BLAST, 10, 1);
                                }

                                if((int)x == 203 && (int)y == 4 && (int)z == -1846) //50%
                                {
                                    sender.sendMessage(ChatColor.AQUA + "50% Complete!");
                                    w.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_BLAST, 10, 1);
                                }

                                if((int)x == 200 && (int)y == 4 && (int)z == -1841) //75%
                                {
                                    sender.sendMessage(ChatColor.AQUA + "75% Complete!");
                                    w.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_BLAST, 10, 1);
                                }

                                //sender.sendMessage("x:"+x +" y:"+y + " z:"+z);
                                if ((int)x == 212 && (int)y == 4 && (int)z == -1839)  //100%
                                {
                                    w.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_LAUNCH, 10, 1);
                                    w.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_LARGE_BLAST, 10, 1);
                                    sender.sendMessage(ChatColor.GREEN + "YOU WIN! score:" + left);
                                    sender.sendMessage(ChatColor.GREEN + "Please let your staff know your score.");
                                    //sender.sendMessage(ChatColor.GREEN + "スコアをスタッフにお知らせください。");
                                    player.teleport(new Location(world, 202, 4, -1862));
                                    Bukkit.getScheduler().cancelTask(taskID);
                                }

                                if (left > 0)
                                {
                                    left--;
                                    switch (left)
                                    {
                                        case 180:
                                            sender.sendMessage(ChatColor.AQUA + "There are 3 minutes remaining! 残り3分0秒です！");
                                            break;
                                        case 150:
                                            sender.sendMessage(ChatColor.AQUA + "There are 2 minutes 30 seconds remaining! 残り2分30秒です！");
                                            break;
                                        case 120:
                                            sender.sendMessage(ChatColor.AQUA + "There are 2 minutes remaining! 残り2分0秒です！");
                                            break;
                                        case 90:
                                            sender.sendMessage(ChatColor.AQUA + "There are 1 minutes 30 seconds remaining! 残り1分30秒です！");
                                            break;
                                        case 60:
                                            sender.sendMessage(ChatColor.YELLOW + "There are 1 minute remaining! 残り1分0秒です！");
                                            break;
                                        case 30:
                                            sender.sendMessage(ChatColor.YELLOW + "There are 30 seconds remaining! 残り30秒です！");
                                            break;
                                        case 10:
                                            sender.sendMessage(ChatColor.YELLOW + "There are 10 seconds remaining! 残り10秒です！");
                                            break;
                                        case 5:
                                            sender.sendMessage(ChatColor.RED + "There are 5 seconds remaining! 残り5秒です！");
                                            break;
                                        case 4:
                                            sender.sendMessage(ChatColor.RED + "There are 4 seconds remaining! 残り4秒です！");
                                            break;
                                        case 3:
                                            sender.sendMessage(ChatColor.RED + "There are 3 seconds remaining! 残り3秒です！");
                                            break;
                                        case 2:
                                            sender.sendMessage(ChatColor.RED + "There are 2 seconds remaining! 残り2秒です！");
                                            break;
                                        case 1:
                                            sender.sendMessage(ChatColor.RED + "There are 1 seconds remaning! 残り1秒です！");
                                            break;
                                    }
                                }

                                else
                                {
                                    player.playSound(player.getLocation(), Sound.ENTITY_ENDERMEN_DEATH, 10, 1);
                                    player.teleport(new Location(world, 202, 4, -1862));
                                    Bukkit.getScheduler().cancelTask(taskID);
                                    Bukkit.broadcastMessage(ChatColor.RED + "GAME OVER!!!");
                                }
                            }
                        };
                    }
                    return true;
                }
                sender.sendMessage(ChatColor.RED+"======== MineMaze Tutorial ========");
                sender.sendMessage(ChatColor.AQUA+"No mouse click or keyboard click!");
                sender.sendMessage(ChatColor.YELLOW+"マウスクリックやキーボードを使用できません");
                sender.sendMessage(ChatColor.AQUA + "just focus to move steve front!");
                sender.sendMessage(ChatColor.YELLOW+"集中してスティーブを前に動かしてください");
            }
        }
        return true;
    }
}