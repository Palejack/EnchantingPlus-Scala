package com.aesireanempire.eplus.network

import com.aesireanempire.eplus.ContainerAdvEnchantment

import io.netty.buffer.ByteBuf
import net.minecraft.enchantment.Enchantment
import net.minecraft.entity.player.EntityPlayer

class EnchantPacket(enchantments: Map[Enchantment, Int], cost: Int) extends EplusPacket {

    var m_enchantments = collection.mutable.Map.empty[Integer, Integer]
    var m_cost = cost

    def this() = this(Map.empty[Enchantment, Int], 0)

    override def readData(buf: ByteBuf): Unit = {
        val length = buf.readInt()

        for (i <- 0 until length) {
            val enchantment = buf.readInt().asInstanceOf[Integer]
            val level = buf.readInt().asInstanceOf[Integer]

            m_enchantments = m_enchantments.++=(Map(enchantment -> level))
        }
        m_cost = buf.readInt()
    }

    override def writeData(buf: ByteBuf): Unit = {
        val length = enchantments.size

        buf.writeInt(length)
        for (enchantment <- enchantments) {
            buf.writeInt(enchantment._1.effectId)
            buf.writeInt(enchantment._2)
        }
        buf.writeInt(cost)
    }

    override def execute(player: EntityPlayer): Unit = {
        player.openContainer.asInstanceOf[ContainerAdvEnchantment].tryEnchantItem(player, m_enchantments, m_cost)
        player.openContainer.detectAndSendChanges()
    }
}
