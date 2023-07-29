package com.hixlepod.hixlepodsorigins.common.PotionRecipes;

import com.hixlepod.hixlepodsorigins.core.init.ItemInit;
import com.hixlepod.hixlepodsorigins.core.init.PotionInit;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.brewing.BrewingRecipe;

public class ChaosPotionRecipe extends BrewingRecipe {


    public ChaosPotionRecipe(Ingredient input, Ingredient ingredient, ItemStack output) {
        super(input, ingredient, output);
    }

    @Override
    public boolean isInput(ItemStack input) {
        return (PotionUtils.getPotion(input) == PotionInit.AMBERGON_POTION.get());
    }

    @Override
    public boolean isIngredient(ItemStack ingredient) {
        return (ingredient.getItem().asItem() == Items.BEDROCK);
    }

    @Override
    public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
        return isInput(input) && isIngredient(ingredient) ? getOutput().copy() : ItemStack.EMPTY;

    }

    public ItemStack getOutput() {
        return PotionUtils.setPotion(new ItemStack(Items.POTION), PotionInit.CHAOS_POTION.get());

    }
}
