package com.keko.midnightLibConfigs;

import eu.midnightdust.lib.config.MidnightConfig;

public class MidnightConfigCyra extends MidnightConfig {

    public static final String NUMBERS = "numbers";
    public static final int tick = 20;
    @Entry(category = NUMBERS) public static int cooldown_cube_purple = tick * 3;
    @Entry(category = NUMBERS) public static int cooldown_cube_red = tick * 9;
    @Entry(category = NUMBERS) public static int cooldown_cube_orange = tick * 30;
    @Entry(category = NUMBERS) public static int cooldown_cube_green = tick * 60;


    @Entry(category = NUMBERS) public static int cooldown_comp_1 = tick * 15;
    @Entry(category = NUMBERS) public static int cooldown_comp_2 = tick * 6;
    @Entry(category = NUMBERS) public static int cooldown_comp_3 = tick * 6;




}
