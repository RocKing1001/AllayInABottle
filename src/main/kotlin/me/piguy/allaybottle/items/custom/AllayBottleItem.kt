package me.piguy.allaybottle.items.custom

import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnReason
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemUsageContext
import net.minecraft.item.Items
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World

class AllayBottleItem(settings: Settings?) : Item(settings) {
  override fun useOnBlock(context: ItemUsageContext?): ActionResult {
    if (context != null) {
      val hand = context.hand
      val user = context.player
      val world = context.world
      val pos = context.blockPos


      if (world != null && !world.isClient && user != null) {
        EntityType.ALLAY.spawn(
          world as ServerWorld,
          null,
          null,
          user,
          pos,
          SpawnReason.TRIGGERED,
          true,
          false
        )

        val glassBottle = ItemStack(Items.GLASS_BOTTLE)

        if (!user.isCreative) {
          user.getStackInHand(hand)?.decrement(1)
          user.setStackInHand(hand, glassBottle)
        }

        return ActionResult.SUCCESS

      }

    }


    return super.useOnBlock(context)
  }

}

