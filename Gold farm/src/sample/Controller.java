package sample;

import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.fxml.FXML;

public class Controller {

    public Button goldButton;

    //Different variables
    long goldCoinSum = 0; //Gold

    long farmer = 0; //Number of farmers
    long monsterEnergyDrinks = 0 ;
    long farmerPCUpgrades = 0;

    long bots = 0; //Number of bots
    long botProgramUpgrades = 0; //Bot program upgrades

    public void goldButtonClick() { //Click gold button (Farm)
        goldButton.setTooltip(new Tooltip("Click here to earn gold!")); //Tooltip if you hover over the button
        goldCoinSum++;
        System.out.println(goldCoinSum);
        System.out.println(farmer);
        System.out.println(bots);
    }
    public void hireFarmerClick() { //Hire farmer button, 50 gold. Passive 3 gold a second
        if (goldCoinSum >= 50) {
            goldCoinSum = goldCoinSum - 50;
            farmer++;
        }
    }
    public void monsterEnergyClick() { //Supply farmers with energy drinks. Cost 200 gold. Passive 20 gold a second
        if (goldCoinSum >= 200) {
            goldCoinSum = goldCoinSum - 200;
            monsterEnergyDrinks++;
        }
    }
    public void upgradeFarmerPCClick() { //PC Button: Big upgrade for farmers. Make farmers 2x effective.
        if (goldCoinSum >= 2000) {
            goldCoinSum = goldCoinSum - 2000;
            farmerPCUpgrades++;
        }
    }
    public void buyBot() { //Buy bot button, 150 gold. Passive 15 gold a second
        if (goldCoinSum >= 150) {
            goldCoinSum = goldCoinSum - 150;
            bots++;
        }
    }
    public void UpgradeBotProgramClick() { //Buy better bot programs to increase bot efficiency by 50%.
        if (goldCoinSum >= 600) {
            goldCoinSum = goldCoinSum - 600;
            botProgramUpgrades++;
        }
    }
    public void farmer() {

    }
    public void farmerPC() { //Increases amount farmed by farmers

    }
    public void monsterEnergyDrinks() { //Increases speed of farming by farmers
        
    }
    public void bot() { //Buy bots to increase farming of gold

    }
    public void botProgram() { //50% increased efficiency of bots

    }
}
