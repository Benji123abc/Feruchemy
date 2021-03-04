package com.legobmw99.feruchemy.items;

import com.legobmw99.feruchemy.Feruchemy;
import com.legobmw99.feruchemy.util.Registry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RefinedMetal extends Item {
    public RefinedMetal(String name) {
        setNoRepair();
        setMaxDamage(-1);
        setMaxStackSize(64);
        setCreativeTab(Registry.tabFeruchemy);
        setRegistryName(new ResourceLocation(Feruchemy.MODID, name));
        setUnlocalizedName(name);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(this, 0,
                new ModelResourceLocation(this.getRegistryName(), "inventory"));
    }
}