package com.example.hello_mod;

import net.minecraft.Util;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.PumpkinBlock;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.commons.lang3.ObjectUtils;

@Mod("hello_mod")//标记这个为模组的主类，指定mod的id
@Mod.EventBusSubscriber//这是一个注解表示这个类可以使用自动注册功能
public class Main {

    @SubscribeEvent
    /*这是一个注解，表示这个方法将使用监听功能（这样才可以调用类似”PlayerEvent.PlayerLoggedInEvent“的类）*/
    //事件的处理方法，事件的类型为 ”监听事件PlayerEvent.PlayerLoggedInEven“ 它发生时赋予给后面的event（名称）这样里面的方法就可以提取出这个事件的各种实例等等如下
    public static void PlayerJoinWorld(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getPlayer();//用于直接获取触发事件的玩家对象 赋予player
        Level level = player.level;
        player.sendMessage(/* 这里的new表示将TextComponent实例化 类似于TextComponent是一个蓝图想要使用就得将他制造出来new就是这种用法*/new TextComponent("Hello" + player.getDisplayName().getString() + ".From" + (level.isClientSide() ? "CLIENT" : "SERVER" + ".")), Util.NIL_UUID);
    }

    @SubscribeEvent
    public static void LeftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
        Player player = event.getPlayer();
        player.sendMessage(new TextComponent("LeftClickBlock was click"), Util.NIL_UUID);
    }

    @SubscribeEvent
    public static void RightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getPlayer();
        player.sendMessage(new TextComponent("RightClickBlock was click"), Util.NIL_UUID);
    }



    //此方法实现了玩家手持钻石剑右键时将玩家传送到指针位置距离最大20格外的方块上
    @SubscribeEvent
    public static void RightClickItem(PlayerInteractEvent.RightClickItem event){//事件类型为右键物品触发的事件
        Player player = event.getPlayer();
        Level level = player.level;
        if (!level.isClientSide()){
            ItemStack itemStack = event.getItemStack();//添加对象（物品堆的属性对象）
            Item item = itemStack.getItem();//获取手上物品堆对象的物品名称
            if(item == Items.DIAMOND_SWORD){//判断手上物品是否为钻石剑
                HitResult hitResult = player.pick(20,0,false);//获取玩家事件中的指针所指的方向命中目标的事件
                Vec3 location = hitResult.getLocation();//通过命中的事件提取出方块的坐标
                player.teleportTo(location.x,location.y,location.z);//传送指令

            }
        }

    }

}