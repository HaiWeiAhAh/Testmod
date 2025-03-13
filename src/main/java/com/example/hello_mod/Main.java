package com.example.hello_mod;

import net.minecraft.Util;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod("hello_mod")//标记这个为模组的主类，指定mod的id
@Mod.EventBusSubscriber//这是一个注解表示这个类可以使用自动注册功能
public class Main {

    @SubscribeEvent
    /*这是一个注解，表示这个方法将使用监听功能（这样才可以调用类似”PlayerEvent.PlayerLoggedInEvent“的类）*/

    //事件的处理方法：监听事件”PlayerEvent.PlayerLoggedInEvent“，当它发生时所做的处理在大括号里面
    public static void PlayerJoinWorld(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getPlayer();
        Level level = player.level;

        player.sendMessage(/* 这里的new表示将TextComponent实例化 类似于TextComponent是一个蓝图想要使用就得将他制造出来new就是这种用法*/new TextComponent("Hello"+player.getDisplayName().getString()+".From"+(level.isClientSide()?"CLIENT":"SERVER"+".")), Util.NIL_UUID);
}


}
