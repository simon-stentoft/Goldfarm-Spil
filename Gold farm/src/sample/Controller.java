package sample;

import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
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
    SimpleDoubleProperty goldCoinSum = new SimpleDoubleProperty(0); //Gold

    long farmer = 0; //Number of farmers
    long monsterEnergyDrinks = 0 ;
    long farmerPCUpgrades = 0;
    long superFarmer = 0;
    double efficiency = 1; //farmer efficiency

    double goldCoins = 0;

    long bots = 0; //Number of bots
    int botProgramUpgrades = 0; //Bot program upgrades

    @FXML
    Label goldSum,goldFarmerLabel,upgFarmerPCLabel,farmerMonsterEnergyLabel,botLabel,botProgramLabel, superFarmerLabel;

    public void goldButtonClick() { //Click gold button (Farm)
        goldSum.textProperty().bind(goldCoinSum.asString("%.01f"));
        goldButton.setTooltip(new Tooltip("Click here to earn gold!")); //Tooltip if you hover over the button
        goldCoinSum.set(goldCoinSum.doubleValue()+1);
       // goldSum.setText(String.valueOf(goldCoinSum));
        System.out.println(goldCoinSum);
        System.out.println(farmer);
        System.out.println(bots);
    }
    public void hireFarmerClick() { //Hire farmer button, 25 gold. Passive 1 gold a second
        if (goldCoinSum.doubleValue() >= 25) {
            goldCoinSum.set(goldCoinSum.doubleValue()-25);
            farmer++;
            goldFarmerLabel.setText(String.valueOf(farmer));
            farmer();
        }
    }
    public void monsterEnergyClick() { //Supply farmers with energy drinks. Cost 200 gold. 10% efficiency to farmer
        if (goldCoinSum.doubleValue() >= 20) {
            goldCoinSum.set(goldCoinSum.doubleValue()-200);
            monsterEnergyDrinks++;
            efficiency += 0.1;
            farmerMonsterEnergyLabel.setText(String.valueOf(monsterEnergyDrinks));
        }
    }
    public void upgradeFarmerPCClick() { //PC Button: Big upgrade for farmers. Make farmers 2x effective.
        if (goldCoinSum.doubleValue() >= 2000) {  // if (goldCoinSum >= 2000)
            goldCoinSum.set(goldCoinSum.doubleValue()-2000);
            farmerPCUpgrades++;
            upgFarmerPCLabel.setText(String.valueOf(farmerPCUpgrades));
        }
    }
    public void buyBot() { //Buy bot button, 150 gold. Passive 5 gold a second
        if (goldCoinSum.doubleValue() >= 150) {
            goldCoinSum.set(goldCoinSum.doubleValue()-150);
            bots++;
            botLabel.setText(String.valueOf(bots));
            bot();
        }
    }
    public void UpgradeBotProgramClick() { //Buy better bot programs to increase bot efficiency by 100%.
        if (goldCoinSum.doubleValue() >= 1500) {
            goldCoinSum.set(goldCoinSum.doubleValue()-1500);
            botProgramUpgrades++;
            botProgramLabel.setText(String.valueOf(botProgramUpgrades));
        }
    }
    public void superFarmerClick() { //Gamer
        if (goldCoinSum.doubleValue() >= 10000) {
            goldCoinSum.set(goldCoinSum.doubleValue()-10000);
            superFarmer++;
            superFarmerLabel.setText(String.valueOf(superFarmer));
            superFarmer();
        }
    }
    final Runnable updateGold = new Runnable() {
        @Override
        public void run() {
            goldCoinSum.set(goldCoins+goldCoinSum.doubleValue());
            goldCoins = 0;
        }
    };
    public void farmer() {
            final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
            final Runnable goldPrSecond = new Runnable() {
                @Override
                public void run() {
                    goldCoins += efficiency;
                  //  goldCoins++;
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
        final ScheduledExecutorService botScheduler = Executors.newScheduledThreadPool(1);
        final Runnable goldPrSecond = new Runnable() {
            @Override
            public void run() {
                goldCoins = goldCoins + 5;
                Platform.runLater(updateGold);
            }
        };
        final ScheduledFuture<?> goldPrSecondHandle = botScheduler.scheduleWithFixedDelay(goldPrSecond,0,1,SECONDS);
    }
    public void botProgram() { //100% increased efficiency of bots

    }
    public void superFarmer() { //330 gold pr second. Gamer.
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        final Runnable goldPrSecond = new Runnable() {
            @Override
            public void run() {
                goldCoins = goldCoins + 330;
                Platform.runLater(updateGold);
            }
        };
        final ScheduledFuture<?> goldPrSecondHandle = scheduler.scheduleWithFixedDelay(goldPrSecond, 0, 1, SECONDS);
    }
}
