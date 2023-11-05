package org.velezreyes.quiz.question6;
import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;
public class VendingMachineImpl implements VendingMachine{
  private static VendingMachine instanceVendingMachine;
  private Double money;
  private List<Pair<Drink, Double>> drinks;

  private VendingMachineImpl(){
    this.money = 0.;
    this.drinks = new ArrayList<>();
    drinks.add(new Pair<>(new DrinkImpl("ScottCola", true), 0.75));
    drinks.add(new Pair<>(new DrinkImpl("KarenTea", false), 1.));
  }
  public Double getMoney() {
    return money;
  }

  public void setMoney(Double money) {
    this.money = money;
  }

  public static VendingMachine getInstance() {
    if(instanceVendingMachine==null) {
      instanceVendingMachine = new VendingMachineImpl();
    }
    return instanceVendingMachine;
  }

  @Override
  public void insertQuarter() {
    setMoney(getMoney() + 0.25);
  }

  @Override
  public Drink pressButton(String name) throws NotEnoughMoneyException, UnknownDrinkException {
    for (Pair<Drink, Double> producto : drinks){
      if(producto.getKey().getName().equals(name)){
        if(producto.getValue() <= getMoney()){
          setMoney(getMoney() - producto.getValue());
          return producto.getKey();
        }
        throw new NotEnoughMoneyException();
      }
    }
    throw new UnknownDrinkException();
  }
}
