package sample;

import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.fxml.FXML;

import java.awt.image.renderable.RenderableImage;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.*;

public class Controller {

    public Button goldButton;

    //Different variables
    SimpleIntegerProperty goldCoinSum = new SimpleIntegerProperty(0); //Gold

    long farmer = 0; //Number of farmers
    long monsterEnergyDrinks = 0 ;
    long farmerPCUpgrades = 0;

    int goldCoins = 0;

    long bots = 0; //Number of bots
    long botProgramUpgrades = 0; //Bot program upgrades

    @FXML
    Label goldSum,goldFarmerLabel,upgFarmerPCLabel,farmerMonsterEnergyLabel,botLabel,botProgramLabel;

    public void goldButtonClick() { //Click gold button (Farm)
        goldSum.textProperty().bind(goldCoinSum.asString());
        goldButton.setTooltip(new Tooltip("Click here to earn gold!")); //Tooltip if you hover over the button
        goldCoinSum.set(goldCoinSum.intValue()+1);
       // goldSum.setText(String.valueOf(goldCoinSum));
        System.out.println(goldCoinSum);
        System.out.println(farmer);
        System.out.println(bots);
    }
    public void hireFarmerClick() { //Hire farmer button, 25 gold. Passive 1 gold a second
        if (goldCoinSum.intValue() >= 25) {
            goldCoinSum.set(goldCoinSum.intValue()-25);
            farmer++;
            goldFarmerLabel.setText(String.valueOf(farmer));
            farmer();
        }
    }
    public void monsterEnergyClick() { //Supply farmers with energy drinks. Cost 200 gold. Passive 20 gold a second
        if (goldCoinSum.intValue() >= 200) {
            goldCoinSum.add(-200);
            monsterEnergyDrinks++;
            farmerMonsterEnergyLabel.setText(String.valueOf(monsterEnergyDrinks));
        }
    }
    public void upgradeFarmerPCClick() { //PC Button: Big upgrade for farmers. Make farmers 2x effective.
        if (goldCoinSum.intValue() >= 2000) {  // if (goldCoinSum >= 2000)
            goldCoinSum.add(-2000);
            farmerPCUpgrades++;
            upgFarmerPCLabel.setText(String.valueOf(farmerPCUpgrades));
        }
    }
    public void buyBot() { //Buy bot button, 150 gold. Passive 15 gold a second
        if (goldCoinSum.intValue() >= 150) {
            goldCoinSum.add(-150);
            bots++;
            botLabel.setText(String.valueOf(bots));
        }
    }
    public void UpgradeBotProgramClick() { //Buy better bot programs to increase bot efficiency by 50%.
        if (goldCoinSum.intValue() >= 600) {
            goldCoinSum.add(-600);
            botProgramUpgrades++;
            botProgramLabel.setText(String.valueOf(botProgramUpgrades));
        }
    }
    final Runnable updateGold = new Runnable() {
        @Override
        public void run() {
            goldCoinSum.set(goldCoins+goldCoinSum.intValue());
            goldCoins = 0;
        }
    };
    public void farmer() {
            final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
            final Runnable goldPrSecond = new Runnable() {
                @Override
                public void run() {
                    goldCoins++;
                    Platform.runLater(updateGold);
                }
            };
            final ScheduledFuture<?> goldPrSecondHandle = scheduler.scheduleWithFixedDelay(goldPrSecond, 0, 1, SECONDS);

    }
    public void farmerPC() { //Increases amount farmed by farmer

    }
    public void monsterEnergyDrinks() { //Increases speed of farming by farmers
        
    }
    public void bot() { //Buy bots to increase farming of gold

    }
    public void botProgram() { //50% increased efficiency of bots

    }
}
