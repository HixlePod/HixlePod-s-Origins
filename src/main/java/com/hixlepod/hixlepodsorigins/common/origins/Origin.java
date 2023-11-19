package com.hixlepod.hixlepodsorigins.common.origins;

public class Origin {

    String NAME;

    String Ability1Name;
    int Ability1Cooldown;

    String Ability2Name;
    int Ability2Cooldown;

    public Origin(String NAME, String Ability1Name, int Ability1Cooldown, String Ability2Name, int Ability2Cooldown) {
        this.NAME = NAME;

        this.Ability1Name = Ability1Name;
        this.Ability1Cooldown = Ability1Cooldown;

        this.Ability2Name = Ability2Name;
        this.Ability2Cooldown = Ability2Cooldown;
    }

}
