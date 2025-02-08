package com.keko.midnightLibConfigs;

import eu.midnightdust.lib.config.MidnightConfig;

public class MidnightConfigCyra extends MidnightConfig {

    public static final String NUMBERS = "numbers";


    @Comment(category = NUMBERS, centered = true) public static Comment title_cooldowns;
    @Entry(category = NUMBERS) public static int cooldown_cube_purple = 3;
    @Entry(category = NUMBERS) public static int cooldown_cube_red = 9;
    @Entry(category = NUMBERS) public static int cooldown_cube_orange = 30;
    @Entry(category = NUMBERS) public static int cooldown_cube_green = 60;


    @Entry(category = NUMBERS) public static int cooldown_comp = 120;
    @Entry(category = NUMBERS) public static int cooldown_comp_1 = 15;
    @Entry(category = NUMBERS) public static int cooldown_comp_2 = 6;
    @Entry(category = NUMBERS) public static int cooldown_comp_3 = 6;
    @Entry(category = NUMBERS) public static int cooldown_spear = 6;

    @Entry(category = NUMBERS) public static int cooldown_electro = 3;
    @Entry(category = NUMBERS) public static int cooldown_battle_axe = 5;

    @Entry(category = NUMBERS) public static int cooldown_sea_wand = 5;
    @Entry(category = NUMBERS) public static int cooldown_sea_strider = 5;
    @Entry(category = NUMBERS) public static boolean nerf_elytra_boost_spear = false;




}
