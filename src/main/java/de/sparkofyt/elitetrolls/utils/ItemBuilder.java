package de.sparkofyt.elitetrolls.utils;

import com.google.common.collect.Multimap;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.*;
import org.bukkit.potion.PotionEffect;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ItemBuilder {

    /* Variables */
    ItemStack is;

    /* Constructor */
    public ItemBuilder(Material material, int amount) {
        is = new ItemStack(material, amount);
    }

    public ItemBuilder(Material material) {
        is = new ItemStack(material);
    }

    public ItemBuilder(ItemStack itemStack) {
        is = itemStack;
    }

    /* Methods */
    public ItemBuilder setDisplayName(String displayName) {
        ItemMeta im = is.getItemMeta();
        if(im != null ) im.setDisplayName(displayName);
        is.setItemMeta(im);
        return this;
    }

    public ItemBuilder setAmount(int amount) {
        is.setAmount(amount);
        return this;
    }

    public ItemBuilder setItemMeta(ItemMeta im) {
        is.setItemMeta(im);
        return this;
    }

    public ItemBuilder setType(Material type) {
        is.setType(type);
        return this;
    }

    public ItemBuilder addItemFlags(ItemFlag... itemFlags) {
        ItemMeta im = is.getItemMeta();
        if(im != null ) im.addItemFlags(itemFlags);
        is.setItemMeta(im);
        return this;
    }

    public ItemBuilder removeItemFlags(ItemFlag... itemFlags) {
        ItemMeta im = is.getItemMeta();
        if(im != null ) im.removeItemFlags(itemFlags);
        is.setItemMeta(im);
        return this;
    }

    public ItemBuilder setAttributeModifiers(Multimap<Attribute, AttributeModifier > attributes) {
        ItemMeta im = is.getItemMeta();
        if(im != null ) im.setAttributeModifiers(attributes);
        is.setItemMeta(im);
        return this;
    }

    public ItemBuilder addAttributeModifier(Attribute attribute, AttributeModifier modifier) {
        ItemMeta im = is.getItemMeta();
        if(im != null ) im.addAttributeModifier(attribute, modifier);
        is.setItemMeta(im);
        return this;
    }

    public ItemBuilder removeAttributeModifier(Attribute attribute, AttributeModifier modifier) {
        ItemMeta im = is.getItemMeta();
        if(im != null ) im.removeAttributeModifier(attribute, modifier);
        is.setItemMeta(im);
        return this;
    }

    public ItemBuilder removeAttributeModifier(Attribute attribute) {
        ItemMeta im = is.getItemMeta();
        if(im != null ) im.removeAttributeModifier(attribute);
        is.setItemMeta(im);
        return this;
    }

    public ItemBuilder removeAttributeModifier(EquipmentSlot equipmentSlot) {
        ItemMeta im = is.getItemMeta();
        if(im != null ) im.removeAttributeModifier(equipmentSlot);
        is.setItemMeta(im);
        return this;
    }

    public ItemBuilder setUnbreakable(boolean unbreakable) {
        ItemMeta im = is.getItemMeta();
        if(im != null ) im.setUnbreakable(unbreakable);
        is.setItemMeta(im);
        return this;
    }

    public ItemBuilder addEnchant(Enchantment enchantment, int level, boolean ignoreLevelRestriction) {
        ItemMeta im = is.getItemMeta();
        if(im != null ) im.addEnchant(enchantment, level, ignoreLevelRestriction);
        is.setItemMeta(im);
        return this;
    }

    public ItemBuilder removeEnchant(Enchantment enchantment) {
        ItemMeta im = is.getItemMeta();
        if(im != null ) im.removeEnchant(enchantment);
        is.setItemMeta(im);
        return this;
    }

    public ItemBuilder setLocalizedName(String localizedName) {
        ItemMeta im = is.getItemMeta();
        if(im != null ) im.setLocalizedName(localizedName);
        is.setItemMeta(im);
        return this;
    }

    public ItemBuilder setCustomModelData(int data) {
        ItemMeta im = is.getItemMeta();
        if(im != null ) im.setCustomModelData(data);
        is.setItemMeta(im);
        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
        ItemMeta im = is.getItemMeta();
        if(im != null ) im.setLore(lore);
        is.setItemMeta(im);
        return this;
    }

    public ItemBuilder setLore(String... lore) {
        ItemMeta im = is.getItemMeta();
        if(im != null ) im.setLore(Arrays.asList(lore));
        is.setItemMeta(im);
        return this;
    }

    public ItemBuilder setLore(String lore) {
        ItemMeta im = is.getItemMeta();
        if(im != null ) im.setLore(Collections.singletonList(lore));
        is.setItemMeta(im);
        return this;
    }

    public ItemBuilder setLeatherArmorColor(Color color) {
        if(is.getType() == Material.LEATHER_BOOTS || is.getType() == Material.LEATHER_LEGGINGS || is.getType() == Material.LEATHER_CHESTPLATE || is.getType() == Material.LEATHER_HELMET) {
            LeatherArmorMeta im = (LeatherArmorMeta) is.getItemMeta();
            if(im != null ) im.setColor(color);
            is.setItemMeta(im);
        }
        return this;
    }

    public ItemBuilder addCustomPotionEffect(PotionEffect effect, boolean overwrite) {
        if(is.getType() == Material.POTION || is.getType() == Material.SPLASH_POTION || is.getType() == Material.LINGERING_POTION) {
            PotionMeta im = (PotionMeta) is.getItemMeta();
            if(im != null ) im.addCustomEffect(effect, overwrite);
            is.setItemMeta(im);
        }
        return this;
    }

    public ItemBuilder addCustomFireworkEffect(FireworkEffect effect) {
        if(is.getType() == Material.FIREWORK_ROCKET) {
            FireworkMeta im = (FireworkMeta) is.getItemMeta();
            if(im != null ) im.addEffect(effect);
            is.setItemMeta(im);
        }
        return this;
    }

    public ItemBuilder addCustomFireworkEffect(FireworkEffect... effect) {
        if(is.getType() == Material.FIREWORK_ROCKET) {
            FireworkMeta im = (FireworkMeta) is.getItemMeta();
            if(im != null ) im.addEffects(effect);
            is.setItemMeta(im);
        }
        return this;
    }

    public ItemBuilder clearFireworkEffects() {
        if(is.getType() == Material.FIREWORK_ROCKET) {
            FireworkMeta im = (FireworkMeta) is.getItemMeta();
            if(im != null ) im.clearEffects();
            is.setItemMeta(im);
        }
        return this;
    }

    public ItemBuilder addBookPage(String... page) {
        if(is.getType() == Material.BOOK) {
            BookMeta im = (BookMeta) is.getItemMeta();
            if(im != null ) im.addPage(page);
            is.setItemMeta(im);
        }
        return this;
    }

    public ItemBuilder addKnowledgeBookRecipe(NamespacedKey... recipe) {
        if(is.getType() == Material.KNOWLEDGE_BOOK) {
            KnowledgeBookMeta im = (KnowledgeBookMeta) is.getItemMeta();
            if(im != null ) im.addRecipe(recipe);
            is.setItemMeta(im);
        }
        return this;
    }

    public ItemBuilder addKnowledgeBookRecipe(List<NamespacedKey> recipe) {
        if(is.getType() == Material.KNOWLEDGE_BOOK) {
            KnowledgeBookMeta im = (KnowledgeBookMeta) is.getItemMeta();
            if(im != null ) im.setRecipes(recipe);
            is.setItemMeta(im);
        }
        return this;
    }

    /* Getters */
    public ItemStack build() {
        return is;
    }
}
